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

@Table
@Entity(name = "FullTimeEquivalent")
@Getter @Setter @NoArgsConstructor @ToString
public class FullTimeEquivalent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idFullTimeEquivalent")
	private int idFullTimeEquivalent;
	
	
	@Column(name = "Year")
	private int year;
	
	@Column(name= "Month")
	private int month;
	
//	@Column(name = "HoursInMonth") // izmanto, lai apreekinaatu ple (nostraadaataas h + atvalinaajuma h)/darba h meenesii
//	private double hoursInMonth;
	
	@Column(name="vacationHours")
	private double vacationHours;
	
	//saite ar finanseejuma avotu
	@ManyToOne
	@JoinColumn(name="idFinSource")
	private FinanceSource financeSource;
		
	//saite ar darbinieku
	@ManyToOne
	@JoinColumn(name = "idEmployeeFullTimeEquivalent", referencedColumnName = "idEmployee"/*, nullable = false*/)
	private Employee employee;
	

	public FullTimeEquivalent(int year, int month, Employee employee, FinanceSource financeSource) {
		this.year = year;
		this.month = month;
		this.employee = employee;
		this.financeSource = financeSource;
	}
	

}
