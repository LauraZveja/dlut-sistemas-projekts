package eu.virac.dlut.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Finansējuma avoti, mātes klase
 * 
 */

//@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table
@Entity(name = "FinanceSource")
@Getter @Setter @NoArgsConstructor @ToString
public class FinanceSource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idFinSource")
	private int idFinSource;
	
	@NotNull
	@Column(name = "Title")
	private String title;
	
	//@NotNull
	@Column(name = "Code")
	private String code;
	
	//saite ar full time equivalent
	@OneToMany(mappedBy = "financeSource")
	@ToString.Exclude
	private Collection<FullTimeEquivalent> fullTimeEquivalent;
	
//	saite ar finansējuma sasaisti ar darba rīkojumu? 
//	@OneToMany(mappedBy = "finOrderWork")
//	@ToString.Exclude
//	private Collection<FinanceOrderWork> finOrderWorkCol = new ArrayList<>();

	public FinanceSource(String title, String code) {
		this.title = title;
		this.code = code;
	}

}
