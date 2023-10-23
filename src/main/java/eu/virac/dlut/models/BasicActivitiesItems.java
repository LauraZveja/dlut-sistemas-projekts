package eu.virac.dlut.models;

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
 * Pamatdarbību uzskaitījums, sastāv no id un nosaukuma. Ir saistīts ar pamatdarbībām 
 * (saite viens (pamatdarbības uzskaitījuma elements) pret daudzi (pamatdarbības))
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "BasicActivitiesItems")
@Getter @Setter @NoArgsConstructor @ToString
public class BasicActivitiesItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idBasActItems")
	private int idBasActItems;
	
	@NotNull
	@Column(name = "Title")
	private String title;
	
	//saite ar pamatdarbībām
	@OneToMany(mappedBy="basActItem")
	@ToString.Exclude
    private Collection<BasicActivities> basicActivitiesCol;

	public BasicActivitiesItems(String title) {
		this.title = title;
	}
	
	
}
