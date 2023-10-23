package eu.virac.dlut.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@Entity(name = "HoursInMonth")
@Getter @Setter @NoArgsConstructor @ToString
public class HoursInMonth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idHoursInMonth")
	private int idHoursInMonth;
	
	
	@Column(name = "Year")
	private int year;
	
	@Column(name= "Month")
	private int month;
	
	@Column(name = "HoursInMonth") // izmanto, lai apreekinaatu ple (nostraadaataas h + atvalinaajuma h)/darba h meenesii
	private double hoursInMonth;

	public HoursInMonth(int year, int month, double hoursInMonth) {
		this.year = year;
		this.month = month;
		this.hoursInMonth = hoursInMonth;
	}
	
	

}
