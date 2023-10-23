package eu.virac.dlut.models;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Darbinieka klase. Sastāv no id, vārda, uzvārda, amata, 
 * no tā, vai ir ievēlēts, darba līguma numura un datuma,
 * atbilstošās nodaļas (viens pret daudzi saite). Daudzi pret viens saite ar nodaļu
 * 
 * Tiek izveidota atbilstošā tabula DB. 
 */
@Table
@Entity(name = "Employee")
@Getter @Setter @NoArgsConstructor @ToString
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idEmployee")
	private int idEmployee;
	
	@NotNull
	//viens vārds, divi vārdi ar defisi, divi vārdi ar atstarpi
	@Pattern(regexp = "^\\p{Lu}{1}\\p{Ll}+( \\p{Lu}{1}\\p{Ll}+|-\\p{Lu}{1}\\p{Ll}+)?$")
	@Column(name = "Name")
	private String name;
	
	@NotNull
	//viens vārds, divi vārdi ar defisi, divi vārdi ar atstarpi
	@Pattern(regexp = "^\\p{Lu}{1}\\p{Ll}+( \\p{Lu}{1}\\p{Ll}+|-\\p{Lu}{1}\\p{Ll}+)?$")
	@Column(name = "Surname")
	private String surname;
	
	@NotNull
	//viens vārds, divi vārdi ar defisi, divi vārdi ar atstarpi
	//@Pattern(regexp = "^\\p{Lu}{1}\\p{Ll}+( \\p{Ll}+|-\\p{Ll}+)?$")
	@Column(name = "Position")
	private String position;
	
	@NotNull
	@Column(name = "isElected")
	private boolean isElected;
	
	@NotNull
	@Column(name = "WorkContractNoDate")
	private String workContractNoAndDate;
	
	//saite ar nodaļu
	@ManyToOne
	@JoinColumn(name="idDepartment", nullable = false)
	private Department department;
	
	//saite ar rīkojumu
	@OneToMany(mappedBy = "employee")
	@ToString.Exclude
	private Collection<Order> orders;
	
	//saite ar finansējuma plānojumu
	@OneToMany(mappedBy = "employee")
	@ToString.Exclude
	private Collection<FinancePlanning> finPlanningCol = new ArrayList<>();
	
	//saite ar PLE
	@OneToMany(mappedBy = "employee")
	@ToString.Exclude
	private Collection<FullTimeEquivalent> fullTimeEquivalents;

	public Employee(String name, String surname, String position, boolean isElected, String workContractNoAndDate,
			Department department) {
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.isElected = isElected;
		this.workContractNoAndDate = workContractNoAndDate;
		this.department = department;
	}
	
	//pievieno un izņem finansējuma plānojumu
	public void addFinancePlanningToEmployee(FinancePlanning fp) {
		finPlanningCol.add(fp);
	}
			
	public void removeFinancePlanningFromEmployee(FinancePlanning fp) {
		finPlanningCol.remove(fp);
	}
	
}
