package eu.virac.dlut.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
	@JoinColumn(name = "idDayStatusHoliday", nullable = false, referencedColumnName = "idDayStatus")
	private DayStatus dayStatus;

	public HolidayPreholidayTransferedHoliday(LocalDate date, DayStatus dayStatus) {
		this.date = date;
		this.dayStatus = dayStatus;
	}
	

}
