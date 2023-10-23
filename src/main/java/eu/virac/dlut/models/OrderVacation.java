package eu.virac.dlut.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Atvaļinājuma rīkojums, klase manto no rīkojuma. 
 * Ir savs id, kā arī tā ir saistīta ar finansējuma sasaisti ar atvaļinājuma rīkojumu
 * (viens(atvaļinājuma rīkojums) pret daudzi(finansējuma sasaiste ar atvaļinājuma rīkojumu) saite)
 * un saistīta ar darbinieka stundām konkrētā mēnesī konkrētā finansējuma avotā
 * (viens(atvaļinājuma rīkojums) pret daudzi(darbinieka stundas konkrētā mēneši konkrētā fin. avotā) saite)
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "OrderVacation")
@AttributeOverride(name = "idOder", column = @Column(name = "idOrdVacation"))
@Getter @Setter @NoArgsConstructor @ToString
public class OrderVacation extends Order {
	
	//saite ar finansējuma sasaisti ar atvaļinājuma rīkojumu
	@OneToMany(mappedBy = "ordVacation")
	@ToString.Exclude
	private Collection<FinanceOrderVacation> finOrdVacCol = new ArrayList<>();
	
	//saite ar darbinieka stundām
	@OneToMany(mappedBy = "ordVacation")
	@ToString.Exclude
	private Collection<EmployeeTimeSheet> emplTimeSheets = new ArrayList<>();

	public OrderVacation(String orderNo, LocalDate date, LocalDate startDate,
			LocalDate endDate, Employee respectiveEmployee) {
		super(orderNo, date, startDate, endDate, respectiveEmployee);
	}
	
	//pievieno un izņem darbinieka stundas
	public void addEmployeeTimeSheetToOrderVacation(EmployeeTimeSheet ets) {
		emplTimeSheets.add(ets);
	}
		
	public void removeEmployeeTimeSheetFromOrderVacation(EmployeeTimeSheet ets) {
		emplTimeSheets.remove(ets);
	}
	
	//pievieno un izņem finansējuma sasaisti ar atvaļinājuma rīkojumu
	public void addFinanceOrderVacationToOrderVacation(FinanceOrderVacation fov) {
		finOrdVacCol.add(fov);
	}
						
	public void removeFinanceOrderFromOrderVacation(FinanceOrderVacation fov) {
		finOrdVacCol.remove(fov);
	}

}
