package eu.virac.dlut.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Nodaļa - atbilst VSRC nodaļām, sastāv no nodaļas id, tās nosaukuma un saīsinājuma. Saistīta ar darbinieku -
 * viens (nodaļa) pret daudzi (darbinieki)
 *  
 *  Tiek izveidota atbilstoša tabula DB. Viens pret daudzi saite ar darbinieku
 */
@Table
@Entity(name = "Department")
@Getter @Setter @NoArgsConstructor @ToString
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idDepartment")
	public int idDepartment;
	
	@NotNull
	@Column(name = "Title")
	public String title;
	
	@Column(name = "Abbreviation")
	public String abbreviation;
	
	//saite ar darbinieku
	@OneToMany(mappedBy="department")
	@ToString.Exclude
    private Collection<Employee> employees = new ArrayList<>();

	public Department(String title, String abbreviation) {
		this.title = title;
		this.abbreviation = abbreviation;
	}
	
	//pievieno un izņem darbinieku
	public void addEmployeeToDepartment(Employee e) {
		employees.add(e);
	}
					
	public void removeEmployeeFromDepartment(Employee e) {
		employees.remove(e);
	}
	
}
