package eu.virac.dlut.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Finansējuma sasaiste ar darba rīkojumu. 
 * Atbilstošais finansējums (var būt viens no četriem - projekts, bāze, VeA netiešās, akadēmiskā slodze),
 * atbilstošais darba rīkojums (viens (darba rīkojums) pret daudzi (finansējuma sasaiste ar darba rīkojumu) saite),
 * līgumā norādītais beigu laiks projektā, maksimālais stundu skaits, slodze, stundu likme, 
 * atbilstošais finansējuma avota veids (viens (finans avota veids) pret daudzi (finansējuma sasaiste ar darba rīkojumu) saite),
 * amats projektā.
 * Ir četri konstruktori.
 * 
 * Tiek izveidota attiecīgā tabula DB.
 */

@Table
@Entity(name = "FinanceOrderWork")
@Getter @Setter @NoArgsConstructor @ToString
public class FinanceOrderWork {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idFinOrdWork")
	private int idFinOrdWork;
	
	//saite ar projektu
	@ManyToOne
	@JoinColumn(name = "idProject")
	private Project project;
	
	//saite ar akadēmisko slodzi
	@ManyToOne
	@JoinColumn(name = "idAcWorkLoad")
	private AcademicWorkLoad acWorkLoad;
	
	//saite ar bāzi
	@ManyToOne
	@JoinColumn(name = "idBaseFin")
	private BaseFin baseFin;
	
	//saite ar netiešajām VeA
	@ManyToOne
	@JoinColumn(name = "idIndirectVUAS")
	private IndirectVUAS indVUAS;
	
	//saite ar darba rīkojumu
	@ManyToOne
	@JoinColumn(name = "idOrdWork")
	private OrderWork ordWork;
	
	@Column(name = "EndDate")
	private LocalDate endDateProjAsPerContract;
	
	@Column(name = "MaxHours")
	private int maxHours;
	
	@Column(name = "WorkLoad")
	private double workLoad; //slodze
	
	@Column(name = "PayPerHour")
	private double payPerHour; //stundu likme
	
	//saite ar finanšu avota veidu
	@ManyToOne
	@JoinColumn(name = "idFinSourceType")
	private FinanceSourceType finSourceType;
	
	@Column(name = "PositionInProject")
	private String positionInProject;
	
	//saite ar pamatdarbībām
	@OneToMany(mappedBy = "finOrdWork")
	@ToString.Exclude
	private Collection<BasicActivities> basicArtivitiesCol = new ArrayList<>();
	
	//saite ar finansējuma sasaisti ar darba rīkojumu
	@OneToMany(mappedBy = "finOrdWork")
	@ToString.Exclude
	private Collection<EmployeeTimeSheet> emplTimeSheets = new ArrayList<>();
	

	//ar projektu
	public FinanceOrderWork(Project project, OrderWork ordWork, LocalDate endDateProjAsPerContract,
			int maxHours, double workLoad, double payPerHour, FinanceSourceType finSourceType,
			String positionInProject) {
		this.project = project;
		this.ordWork = ordWork;
		this.endDateProjAsPerContract = endDateProjAsPerContract;
		this.maxHours = maxHours;
		this.workLoad = workLoad;
		this.payPerHour = payPerHour;
		this.finSourceType = finSourceType;
		this.positionInProject = positionInProject;
	}
	
	//ar akadēmisko slodzi
	public FinanceOrderWork(AcademicWorkLoad acWorkLoad, OrderWork ordWork, LocalDate endDateProjAsPerContract,
			int maxHours, double workLoad, double payPerHour, FinanceSourceType finSourceType,
			String positionInProject) {
		this.acWorkLoad = acWorkLoad;
		this.ordWork = ordWork;
		this.endDateProjAsPerContract = endDateProjAsPerContract;
		this.maxHours = maxHours;
		this.workLoad = workLoad;
		this.payPerHour = payPerHour;
		this.finSourceType = finSourceType;
		this.positionInProject = positionInProject;
	}
	
	//ar bāzi
	public FinanceOrderWork(BaseFin baseFin, OrderWork ordWork, LocalDate endDateProjAsPerContract,
			int maxHours, double workLoad, double payPerHour, FinanceSourceType finSourceType,
			String positionInProject) {
		this.baseFin = baseFin;
		this.ordWork = ordWork;
		this.endDateProjAsPerContract = endDateProjAsPerContract;
		this.maxHours = maxHours;
		this.workLoad = workLoad;
		this.payPerHour = payPerHour;
		this.finSourceType = finSourceType;
		this.positionInProject = positionInProject;
	}
	
	//ar VeA netiešajām
	public FinanceOrderWork(IndirectVUAS indVUAS, OrderWork ordWork, LocalDate endDateProjAsPerContract,
			int maxHours, double workLoad, double payPerHour, FinanceSourceType finSourceType,
			String positionInProject) {
		this.indVUAS = indVUAS;
		this.ordWork = ordWork;
		this.endDateProjAsPerContract = endDateProjAsPerContract;
		this.maxHours = maxHours;
		this.workLoad = workLoad;
		this.payPerHour = payPerHour;
		this.finSourceType = finSourceType;
		this.positionInProject = positionInProject;
	}

	
	//pievieno un izņem pamatdarbības
	public void addBasicActivitiesToFinanceOrderWork(BasicActivities ba) {
		basicArtivitiesCol.add(ba);
	}
				
	public void removeBasicActivitiesFromFinanceOrderWork(BasicActivities ba) {
		basicArtivitiesCol.remove(ba);
	}
	
	//pievieno un izņem darba stundas
	public void addEmployeeTimeSheetToFinanceOrderWork(EmployeeTimeSheet ets) {
		emplTimeSheets.add(ets);
	}
				
	public void removeEmployeeTimeSheetsFromFinanceOrderWork(EmployeeTimeSheet ets) {
		emplTimeSheets.remove(ets);
	}
	
}
