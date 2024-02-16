package eu.virac.dlut.models;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

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
