package eu.virac.dlut.models;

import java.util.ArrayList;
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
 * Finansējuma avota veids - pētnieciskais, akadēmiskais, tehniskais atbilsts, administratīvais.
 * Šī klase ir sasitīta ar finansējuma sasaisti ar darba rīkojumu
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "FinSourceType")
@Getter @Setter @NoArgsConstructor @ToString
public class FinanceSourceType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idFinSourceType")
	private short idFinSourceType;
	
	@NotNull
	@Column(name = "Type")
	private String type;
	
	//saite ar finansējuma sasaisti ar darba rīkojumu
	@OneToMany(mappedBy = "finSourceType")
	@ToString.Exclude
	private Collection<FinanceOrderWork> finOrderWorkCol = new ArrayList<>();

	public FinanceSourceType(String type) {
		this.type = type;
	}
	
	//pievieno un izņem finansējuma sasaisti ar darba rīkojumu
	public void addFinanceOrderWorkToFinanceSourceType(FinanceOrderWork fow) {
		finOrderWorkCol.add(fow);
	}
						
	public void removeFinanceOrderWorkFromFinanceSourceType(FinanceOrderWork fow) {
		finOrderWorkCol.remove(fow);
	}

}
