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
 * VeA netiešās, klase manto no finansējuma avota. Manto nosaukumu un kodu, kā arī ir savs id.
 * Šī klase sasitīta ar finansējuma sasaisti ar darba rīkojumu, 
 * kā arī ar finansējuma sasaisti ar atvaļinājuma rīkojumu un finansējuma plānojumu.
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "IndirectVuas")
@AttributeOverride(name = "idFinSource", column = @Column(name = "idIndirectVUAS"))
@Getter @Setter @NoArgsConstructor @ToString
public class IndirectVUAS extends FinanceSource{
	
	//saite ar finansējuma sasaisti ar darba rīkojumu
	@OneToMany(mappedBy = "indVUAS")
	@ToString.Exclude
	private Collection<FinanceOrderWork> finOrderWorkCol = new ArrayList<>();
	
	//saite ar finansējuma sasaisti ar atvaļinājuma rīkojumu
	@OneToMany(mappedBy = "indVUAS")
	@ToString.Exclude
	private Collection<FinanceOrderVacation> finOrderVacCol = new ArrayList<>();

	//saite ar finansējuma plānojumu
	@OneToMany(mappedBy = "indVUAS")
	@ToString.Exclude
	private Collection<FinancePlanning> finPlanningCol = new ArrayList<>();
	
	public IndirectVUAS(String title, String code) {
		super(title, code);
	}
	
	//pievieno un izņem finanšu plānošanu
	public void addFinancePlanningToIndirectVUAS(FinancePlanning fp) {
		finPlanningCol.add(fp);
	}
				
	public void removeFinancePlanningFromIndirectVUAS(FinancePlanning fp) {
		finPlanningCol.remove(fp);
	}
	
	//pievieno un izņem finansējuma sasaisti ar atvaļinājuma rīkojumu
	public void addFinanceOrderVacationToIndirectVUAS(FinanceOrderVacation fov) {
		finOrderVacCol.add(fov);
	}
					
	public void removeFinanceOrderVacationFromIndirectVUAS(FinanceOrderVacation fov) {
		finOrderVacCol.remove(fov);
	}
	
	//pievieno un izņem finansējuma sasaisti ar darba rīkojumu
	public void addFinanceOrderWorkToIndirectVUAS(FinanceOrderWork fow) {
		finOrderWorkCol.add(fow);
	}
					
	public void removeFinanceOrderWorkFromIndirectVUAS(FinanceOrderWork fow) {
		finOrderWorkCol.remove(fow);
	}
	
}
