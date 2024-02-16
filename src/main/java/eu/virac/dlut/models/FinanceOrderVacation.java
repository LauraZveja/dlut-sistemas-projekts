package eu.virac.dlut.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Finansējuma sasaiste ar atvaļinājuma rīkojumu. 
 * Atbilstošais finansējums (var būt viens no četriem - projekts, bāze, VeA netiešās, akadēmiskā slodze), 
 * atbilstoši ir arī četri konstruktori.
 * Atbilstošais atvaļinājums rīkojums (viens (atvaļinājuma rīkojums) 
 * pret daudzi (finansējuma sasaiste ar atvaļinājuma rīkojumu) saite).
 * 
 * Tiek izveidota attiecīgā tabula DB.
 */

@Table
@Entity(name = "FinanceOrderVacation")
@Getter @Setter @NoArgsConstructor @ToString
public class FinanceOrderVacation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idFinOrdVac")
	private int idFinOrdVac;
	
	//saite ar projektu
	@ManyToOne
	@JoinColumn(name = "idProject")
	private Project project;
		
	//saite ar akadēmisko slodzi
	@ManyToOne
	@JoinColumn(name = "idAcWorkLoad")
	private AcademicWorkLoad acWorkLoad;
		
	//saite ar bāzi
	@ManyToOne
	@JoinColumn(name = "idBaseFin")
	private BaseFin baseFin;
		
	//saite ar netiešajām VeA
	@ManyToOne
	@JoinColumn(name = "idIndirectVUAS")
	private IndirectVUAS indVUAS;
	
	//saite ar atvaļinājuma rīkojumu
	@ManyToOne
	@JoinColumn(name = "idOrdVacation")
	private OrderVacation ordVacation;

	//ar projektu
	public FinanceOrderVacation(Project project, OrderVacation ordVacation) {
		this.project = project;
		this.ordVacation = ordVacation;
	}
	
	//ar akad slodzi
	public FinanceOrderVacation(AcademicWorkLoad acWorkLoad, OrderVacation ordVacation) {
		this.acWorkLoad = acWorkLoad;
		this.ordVacation = ordVacation;
	}
	
	//ar bāzi
	public FinanceOrderVacation(BaseFin baseFin, OrderVacation ordVacation) {
		this.baseFin = baseFin;
		this.ordVacation = ordVacation;
	}
	
	//ar VeA netiešām
	public FinanceOrderVacation(IndirectVUAS indVUAS, OrderVacation ordVacation) {
		this.indVUAS = indVUAS;
		this.ordVacation = ordVacation;
	}

	
}
