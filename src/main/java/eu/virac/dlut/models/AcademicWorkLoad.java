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
 * Akademiska slodze, klase manto no finansejuma avota, manto nosaukumu un kodu, ka arī ir savs id.
 * Šī klase ir sasitīta ar finansējuma sasaisti ar darba rīkojumu, 
 * kā arī ar finansējuma sasaisti ar atvaļinājuma rīkojumu un finansējuma plānojumu.
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "AcademicWorkLoad")
@AttributeOverride(name = "idFinSource", column = @Column(name = "idAcWorkLoad"))
@Getter @Setter @NoArgsConstructor @ToString
public class AcademicWorkLoad extends FinanceSource{
	
	//saite ar finansējuma sasaisti ar darba rīkojumu
	@OneToMany(mappedBy = "acWorkLoad")
	@ToString.Exclude
	private Collection<FinanceOrderWork> finOrderWorkCol = new ArrayList<>();
	
	//saite ar finansejuma sasaisti ar atvaļinājuma rikojumu
	@OneToMany(mappedBy = "acWorkLoad")
	@ToString.Exclude
	private Collection<FinanceOrderVacation> finOrderVacCol = new ArrayList<>();
	
	//saite ar finansejuma planojumu
	@OneToMany(mappedBy = "acWorkLoad")
	@ToString.Exclude
	private Collection<FinancePlanning> finPlanningCol = new ArrayList<>();

	public AcademicWorkLoad(String title, String code) {
		super(title, code);
	}
	
	//pievieno un izņem finansejuma planojumu
	public void addFinancePlanningToAcademicWorkLoad(FinancePlanning fp) {
		finPlanningCol.add(fp);
	}
				
	public void removeFinancePlanningFromAcademicWorkLoad(FinancePlanning fp) {
		finPlanningCol.remove(fp);
	}
	
	//pievieno un izņem finansējuma sasasiti ar atvaļinājuma rīkojumu
	public void addFinanceOrderVacationToAcademicWorkLoad(FinanceOrderVacation fov) {
		finOrderVacCol.add(fov);
	}
					
	public void removeFinanceOrderVacationFromAcademicWorkLoad(FinanceOrderVacation fov) {
		finOrderVacCol.remove(fov);
	}
	
	//pievieno un izņem finansējuma sasaisti ar darba rīkojumu
	public void addFinanceOrderWorkToAcademicWorkLoad(FinanceOrderWork fow) {
		finOrderWorkCol.add(fow);
	}
					
	public void removeFinanceOrderWorkFromAcademicWorkLoad(FinanceOrderWork fow) {
		finOrderWorkCol.remove(fow);
	}

}
