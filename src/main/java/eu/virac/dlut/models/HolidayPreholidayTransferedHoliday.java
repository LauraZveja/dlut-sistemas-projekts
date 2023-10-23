package eu.virac.dlut.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Svētku/pirmssvētku/pārceltās dienas. Sastāv no id, datuma un dienas statusa 
 * (viens (statuss) pret daudzi (diena) saite)
 * 
 * Tiek izveidota attiecīgā tabula DB.
 */

@Table
@Entity(name = "HolidayPreholidayTransferedHoliday")
@Getter @Setter @NoArgsConstructor @ToString
public class HolidayPreholidayTransferedHoliday {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idDay")
	private int idDay;
	
	@Column(name = "Date")
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private LocalDate date;
	
	//saite ar dienas statusu
	@ManyToOne
	@JoinColumn(name = "idDayStatus", nullable = false)
	private DayStatus dayStatus;

	public HolidayPreholidayTransferedHoliday(LocalDate date, DayStatus dayStatus) {
		this.date = date;
		this.dayStatus = dayStatus;
	}
	

}
