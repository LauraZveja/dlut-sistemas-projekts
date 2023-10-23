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
 * Cits rīkojums, klase, kas manto no rīkojuma. 
 * Ir savs id un piezīmes (piezīme var būt, piemēram, vai saglabāsies darba samaksa),
 * kā arī ir šī klase ir saistīta ar darbinieka stundām konkrētā mēnesī konkrētā finansējuma avotā
 * (viens(cits rīkojums) pret daudzi(darbinieka stundas konkrētā mēneši konkrētā fin. avotā) saite)
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "OrderOther")
@AttributeOverride(name = "idOder", column = @Column(name = "idOrdOther"))
@Getter @Setter @NoArgsConstructor @ToString
public class OrderOther extends Order{
	
	@Column(name = "Remarks")
	private String remarks;
	
	//saite ar darbinieka stundām
	@OneToMany(mappedBy = "ordOther")
	@ToString.Exclude
	private Collection<EmployeeTimeSheet> emplTimeSheets = new ArrayList<>();

	public OrderOther(String orderNo, LocalDate date, LocalDate startDate,
			LocalDate endDate, Employee respectiveEmployee, String remarks) {
		super(orderNo, date, startDate, endDate, respectiveEmployee);
		this.remarks = remarks;
	}
	
	//pievieno un izņem ar darbinieka stundas
	public void addEmployeeTimeSheetToOrderOther(EmployeeTimeSheet ets) {
		emplTimeSheets.add(ets);
	}
		
	public void removeEmployeeTimeSheetFromOrderOther(EmployeeTimeSheet ets) {
		emplTimeSheets.remove(ets);
	}

}
