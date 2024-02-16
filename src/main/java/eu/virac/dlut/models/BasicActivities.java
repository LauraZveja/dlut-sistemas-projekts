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
 * Pamatdarbības, sastāv no id, finansējuma sasaistes ar darba rīkojumu 
 * (viens (fin sasaiste ar darba rīkojumu) pret daudzi (pamatdarbības) saite),
 * pamatdarbību uzskaitījuma (viens (pamatdarbības elements) pret daudzi (pamatdarbības) saite)
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "BasicActivities")
@Getter @Setter @NoArgsConstructor @ToString
public class BasicActivities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idBaseAct")
	private int idBaseAct;
	
	//saite ar finsējuma sasaisti ar darba rīkojumu
	@ManyToOne
	@JoinColumn(name = "idFinOrdWork", nullable = false)
	private FinanceOrderWork finOrdWork;
	
	//saite ar pamatdarbību uzskaitījumu
	@ManyToOne
	@JoinColumn(name = "idBasActItems", nullable = false)
	private BasicActivitiesItems basActItem;

	public BasicActivities(FinanceOrderWork finOrdWork, BasicActivitiesItems basActItem) {
		this.finOrdWork = finOrdWork;
		this.basActItem = basActItem;
	}
	
	

}
