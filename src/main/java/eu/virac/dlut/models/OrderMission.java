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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Komandējuma rīkojums, klase, kas manto no rīkojuma. 
 * Ir savs id, pārcelto dienu skaits, 
 * kā arī šī klase ir saistīta ar projektu (saite viens(projekts) pret daudzi(komand. rīkojumi))
 * un saistīta ar darbinieka stundām konkrētā mēnesī konkrētā finansējuma avotā
 * (viens(komandējuma rīkojums) pret daudzi(darbinieka stundas konkrētā mēneši konkrētā fin. avotā) saite)
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "OrderMission")
@AttributeOverride(name = "idOder", column = @Column(name = "idOrdMission"))
@Getter @Setter @NoArgsConstructor @ToString
public class OrderMission extends Order {
	
	@Column(name = "TransferedDays")
	private short transferedDays;

	
	//saite ar projektu
	@ManyToOne
	@JoinColumn(name = "idProject", nullable = false)
	private Project project;
	
	//saite ar darbinieka stundām
	@OneToMany(mappedBy = "ordMission")
	@ToString.Exclude
	private Collection<EmployeeTimeSheet> emplTimeSheets = new ArrayList<>();

	public OrderMission(String orderNo, LocalDate date, Project project, LocalDate startDate,
			LocalDate endDate, Employee respectiveEmployee, short transferedDays) {
		super(orderNo, date, startDate, endDate, respectiveEmployee);
		this.project = project;
		this.transferedDays = transferedDays;
	} 
	
	//pievieno un izņem ar darbinieka stundas
	public void addEmployeeTimeSheetToOrderMission(EmployeeTimeSheet ets) {
		emplTimeSheets.add(ets);
	}
		
	public void removeEmployeeTimeSheetFromOrderMission(EmployeeTimeSheet ets) {
		emplTimeSheets.remove(ets);
	}
	
}
