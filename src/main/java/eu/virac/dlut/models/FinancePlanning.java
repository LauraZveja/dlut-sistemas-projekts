package eu.virac.dlut.models;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Finansējuma plānojums. Sastāv no atbilstošā finansējuma (var būt viens no četriem - 
 * projekts, bāze, VeA netiešās, akadēmiskā slodze),
 * atbilstošā darbinieka, gada un mēneša, stundu skaita konkrētā mēnesī, slodzes konkrētā menesī, 
 * stundu likmes.
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "FinancePlanning")
@Getter @Setter @NoArgsConstructor @ToString
public class FinancePlanning {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idFinPlanning")
	private int idFinPlanning;
	
	@ManyToOne
	@JoinColumn(name = "idProject")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "idAcWorkLoad")
	private AcademicWorkLoad acWorkLoad;
	
	@ManyToOne
	@JoinColumn(name = "idBaseFin")
	private BaseFin baseFin;
	
	@ManyToOne
	@JoinColumn(name = "idIndirectVUAS")
	private IndirectVUAS indVUAS;
	
	@ManyToOne
	@JoinColumn(name = "idEmployeeFinancePlanning", referencedColumnName = "idEmployee"/*, nullable = false*/)
	private Employee employee;
	
	@Column(name = "FinYearMonth")
	//@DateTimeFormat(pattern = "MM.yyyy.") //
	//private YearMonth yearMonth;
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private LocalDate yearMonth;
	
	@Column(name = "HoursCertainMonth")
	private double hoursCertainMonth;
	
	@Column(name = "WorkLoadCertainMonth")
	private double workLoadCertainMonth;
	
	@Column(name = "PerHour")
	private double perHour;

	//ar projektu
	public FinancePlanning(Project project, Employee employee, /*YearMonth*/ LocalDate yearMonth, double hoursCertainMonth,
			double workLoadCertainMonth, double perHour) {
		this.project = project;
		this.employee = employee;
		this.yearMonth = yearMonth;
		this.hoursCertainMonth = hoursCertainMonth;
		this.workLoadCertainMonth = workLoadCertainMonth;
		this.perHour = perHour;
	}
	
	//ar akadēmisko slodzi
	public FinancePlanning(AcademicWorkLoad acWorkLoad, Employee employee, /*YearMonth*/ LocalDate yearMonth, double hoursCertainMonth,
			double workLoadCertainMonth, double perHour) {
		this.acWorkLoad = acWorkLoad;
		this.employee = employee;
		this.yearMonth = yearMonth;
		this.hoursCertainMonth = hoursCertainMonth;
		this.workLoadCertainMonth = workLoadCertainMonth;
		this.perHour = perHour;
	}
	
	//ar bāzi
	public FinancePlanning(BaseFin baseFin, Employee employee, /*YearMonth*/ LocalDate yearMonth, double hoursCertainMonth,
			double workLoadCertainMonth, double perHour) {
		this.baseFin = baseFin;
		this.employee = employee;
		this.yearMonth = yearMonth;
		this.hoursCertainMonth = hoursCertainMonth;
		this.workLoadCertainMonth = workLoadCertainMonth;
		this.perHour = perHour;
	}
	
	//ar VeA netiešajām
	public FinancePlanning(IndirectVUAS indVUAS, Employee employee, /*YearMonth*/ LocalDate yearMonth, double hoursCertainMonth,
			double workLoadCertainMonth, double perHour) {
		this.indVUAS = indVUAS;
		this.employee = employee;
		this.yearMonth = yearMonth;
		this.hoursCertainMonth = hoursCertainMonth;
		this.workLoadCertainMonth = workLoadCertainMonth;
		this.perHour = perHour;
	}
	
	public FinancePlanning(Employee employee, /*YearMonth*/ LocalDate yearMonth, double hoursCertainMonth,
			double workLoadCertainMonth, double perHour) {
		this.employee = employee;
		this.yearMonth = yearMonth;
		this.hoursCertainMonth = hoursCertainMonth;
		this.workLoadCertainMonth = workLoadCertainMonth;
		this.perHour = perHour;
	}
	
}
