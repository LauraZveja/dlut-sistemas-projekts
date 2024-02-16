package eu.virac.dlut.models;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Dienas statuss. Sastāv no id un nosaukuma (svētku, pirmssvētku, pārceltās-B, pārceltās-D). 
 * Saistīts ar svētku/pirmssvētku/pārceltajām dienām (viens (statuss) pret daudzi (dienas))
 * 
 * Tiek izveidota attiecīgā tabula DB.
 */

@Table
@Entity(name = "DayStatus")
@Getter @Setter @NoArgsConstructor @ToString
public class DayStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idDayStatus")
	private int idDayStatus;
	
	@NotNull
	@Column(name = "DayTitle")
	private String dayTitle;
	
	//saite ar svētku/pirmssvētku/pārceltajām dienām
	@OneToMany(mappedBy = "dayStatus")
	@ToString.Exclude
	private Collection<HolidayPreholidayTransferedHoliday> holidays = new ArrayList<>();

	public DayStatus(String dayTitle) {
		this.dayTitle = dayTitle;
	}
	
	//pievieno un izņem svētku/pirmssvētku/pārcelto dienu
	public void addHolidayPreholidayTransferedHolidayToDayStatus(HolidayPreholidayTransferedHoliday h) {
		holidays.add(h);
	}
					
	public void removeHolidayPreholidayTransferedHolidayFromDayStatus(HolidayPreholidayTransferedHoliday h) {
		holidays.remove(h);
	}
}
