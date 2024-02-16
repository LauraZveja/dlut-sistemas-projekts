package eu.virac.dlut.models;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Bāze, klase manto no finansējuma avota, manto nosaukumu un kodu, ir savs id.
 * Šī klase ir sasitīta ar finansējuma sasaisti ar darba rīkojumu, 
 * kā arī ar finansējuma sasaisti ar atvaļinājuma rīkojumu un finansējuma plānojumu.
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "BaseFin")
@AttributeOverride(name = "idFinSource", column = @Column(name = "idBaseFin"))
@Getter @Setter @NoArgsConstructor @ToString
public class BaseFin extends FinanceSource{
	
	//saite ar finansējuma sasaisti ar darba rīkojumu
	@OneToMany(mappedBy = "baseFin")
	@ToString.Exclude
	private Collection<FinanceOrderWork> finOrderWorkCol = new ArrayList<>();

	//saite ar finansējuma sasaisti ar atvaļinājuma rīkojumu
	@OneToMany(mappedBy = "baseFin")
	@ToString.Exclude
	private Collection<FinanceOrderVacation> finOrderVacCol = new ArrayList<>();
	
	//saite ar finansējuma plānojumu
	@OneToMany(mappedBy = "baseFin")
	@ToString.Exclude
	private Collection<FinancePlanning> finPlanningCol = new ArrayList<>();
	
	public BaseFin(String title, String code) {
		super(title, code);
	}
	
	//pievieno un izņem finansējuma plānojumu
	public void addFinancePlanningToBaseFin(FinancePlanning fp) {
		finPlanningCol.add(fp);
	}
			
	public void removeFinancePlanningFromBaseFin(FinancePlanning fp) {
		finPlanningCol.remove(fp);
	}
	
	//pievieno un izņem finansējuma sasaisti ar atvaļinājuma rīkojumu
	public void addFinanceOrderVacationToBaseFin(FinanceOrderVacation fov) {
		finOrderVacCol.add(fov);
	}
					
	public void removeFinanceOrderVacationFromBaseFin(FinanceOrderVacation fov) {
		finOrderVacCol.remove(fov);
	}
	
	//pievieno un izņem finansējuma sasaisti ar darba rīkojumu
	public void addFinanceOrderWorkToBaseFin(FinanceOrderWork fow) {
		finOrderWorkCol.add(fow);
	}
					
	public void removeFinanceOrderWorkFromBaseFin(FinanceOrderWork fow) {
		finOrderWorkCol.remove(fow);
	}

}
