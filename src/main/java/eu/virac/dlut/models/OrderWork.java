package eu.virac.dlut.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Darba rīkojums, klase manto no rīkojuma. 
 * Ir savs id un tas, vai ir aktīvs (statuss - aktīvs, neaktīvs).
 * Ir saistīta ar finansējuma sasaisti ar darba rīkojumu 
 * (viens(darba rīkojums) pret daudzi(finansējuma sasaiste ar darba rīkojumu) saite).
 * Ir divi argumentu konstruktori - bez beigu datuma un ar to.
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "OrderWork")
@AttributeOverride(name = "idOder", column = @Column(name = "idOrdWork"))
@Getter @Setter @NoArgsConstructor @ToString
public class OrderWork extends Order {
	
	@NotNull
	@Column(name = "IsActive")
	private boolean isActive;
	
	//saite ar finansējuma sasaisti ar darba rīkojumu
	@OneToMany(mappedBy = "ordWork")
	@ToString.Exclude
	private Collection<FinanceOrderWork> finOrdWorkCol = new ArrayList<>();

	//bez beigu datuma
	public OrderWork(String orderNo, LocalDate date, LocalDate startDate, Employee employee, 
			boolean isActive) {
		super(orderNo, date, startDate, employee);
		this.isActive = isActive;
	}
	
	//ar beigu datumu
	public OrderWork(String orderNo, LocalDate date, LocalDate startDate, LocalDate endDate, Employee employee,
			boolean isActive) {
		super(orderNo, date, startDate, endDate, employee);
		this.isActive = isActive;
	}
	
	//pievieno un izņem finansējuma sasaisti ar darba rīkojumu
	public void addFinanceOrderWorkToOrderWork(FinanceOrderWork fow) {
		finOrdWorkCol.add(fow);
	}
					
	public void removeFinanceOrderWorkFromOrderWork(FinanceOrderWork fow) {
		finOrdWorkCol.remove(fow);
	}
	

}
