package eu.virac.dlut.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Projekts kā finansējuma avots, manto nosaukumu un kodu. Ir savs id, kā arī projekta nosaukums, numurs, 
 * grāmatvedības akronīms, līguma numurs(tikai informatīvi), projekta raksturs (viens pret daudzi saite), 
 * projekta kategorija (viens pret daudzi saite), projekta ilgums mēnešos, uzsākšanas datums un beigu datums.
 * 
 * Tiek izveidota attiecīgā tabula DB.
 */

@Table
@Entity(name = "Project")
@AttributeOverride(name = "idFinSource", column = @Column(name = "idProject"))
@Getter @Setter @NoArgsConstructor @ToString
public class Project extends FinanceSource{
	
//	@NotNull
//	@Column(name = "ProjTitle")
//	private String projTitle;
	
//	@NotNull
//	@Column(name = "Number")
//	private String number;
	
	//@NotNull
	@Column(name = "AccountingAcronym")
	private String accountingAcronym;
	
	//@NotNull
	@Column(name = "ContractNo")
	private String contractNo;
	
	@ManyToOne
	@JoinColumn(name = "idProjChar")
	private ProjectCharacter projChar;
	
	@ManyToOne
	@JoinColumn(name = "idProjCat")
	private ProjectCategory projCat;
	
	@Column(name = "ProjLenghtMonths")
	private int projLenghtMonths;
	
	@NotNull
	@Column(name = "Start")
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private LocalDate start;
	
	@Column(name = "End")
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private LocalDate end;
	
	@Column
	private boolean isActive;
	
	//saite ar komandējuma Rīkojumu
	@OneToMany(mappedBy = "project")
	@ToString.Exclude
	private Collection<OrderMission> orderMissions;
	
	//saite ar finansējuma sasaisti ar darba rīkojumu
	@OneToMany(mappedBy = "project")
	@ToString.Exclude
	private Collection<FinanceOrderWork> finOrderWorkCol = new ArrayList<>();
	
	//saite ar finansējuma sasaisti ar atvaļinājuma rīkojumu
	@OneToMany(mappedBy = "project")
	@ToString.Exclude
	private Collection<FinanceOrderVacation> finOrderVacCol = new ArrayList<>();
	
	//saite ar finansējuma plānojumu
	@OneToMany(mappedBy = "project")
	@ToString.Exclude
	private Collection<FinancePlanning> finPlanningCol = new ArrayList<>();
	
	public Project(String title, String code,/* String projTitle, String number,*/ String accountingAcronym, String contractNo, ProjectCharacter projChar,
			ProjectCategory projCat, int projLenghtMonths, LocalDate start, LocalDate end) {
		super(title, code);
//		this.projTitle = projTitle;
//		this.number = number;
		this.accountingAcronym = accountingAcronym;
		this.contractNo = contractNo;
		this.projChar = projChar;
		this.projCat = projCat;
		this.projLenghtMonths = projLenghtMonths;
		this.start = start;
		this.end = end;
	}
	
	//pievieno un izņem finansējuma plānojumu
	public void addFinancePlanningToProject(FinancePlanning fp) {
		finPlanningCol.add(fp);
	}
			
	public void removeFinancePlanningFromProject(FinancePlanning fp) {
		finPlanningCol.remove(fp);
	}
	
	//pievieno un izņem finansējuma sasaisti ar atvaļinājuma rīkojumu
	public void addFinanceOrderVacationToProject(FinanceOrderVacation fov) {
		finOrderVacCol.add(fov);
	}
					
	public void removeFinanceOrderVacationFromProject(FinanceOrderVacation fov) {
		finOrderVacCol.remove(fov);
	}
	
	//pievieno un izņem finansējuma sasaisti ar darba rīkojumu
	public void addFinanceOrderWorkToProject(FinanceOrderWork fow) {
		finOrderWorkCol.add(fow);
	}
					
	public void removeFinanceOrderWorkFromProject(FinanceOrderWork fow) {
		finOrderWorkCol.remove(fow);
	}
	
}
