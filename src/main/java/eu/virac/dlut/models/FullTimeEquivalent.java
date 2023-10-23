package eu.virac.dlut.models;


import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@JoinColumn(name = "idEmployee"/*, nullable = false*/)
	private Employee employee;
	

	public FullTimeEquivalent(int year, int month, Employee employee, FinanceSource financeSource) {
		this.year = year;
		this.month = month;
		this.employee = employee;
		this.financeSource = financeSource;
	}
	

}
