package eu.virac.dlut.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.HolidayPreholidayTransferedHoliday;

public interface IHolidayPreholidayTransferedHolidayRepo extends CrudRepository<HolidayPreholidayTransferedHoliday, Integer>{

	
	//search and join looking also for title "Sveetki"
	@Query(value = "SELECT * FROM holiday_preholiday_transfered_holiday h "
			+ "JOIN day_status d ON h.id_day_status = d.id_day_status "
			+ "WHERE (EXTRACT(DAY FROM h.date) = ?1 AND EXTRACT(MONTH FROM h.date) = ?2 AND EXTRACT(YEAR FROM h.date) = ?3 ) AND d.day_title LIKE 'Sv%'",
			nativeQuery = true)
	HolidayPreholidayTransferedHoliday findByDateAndHolidayTitle(int day, int month, int year);

	
	//search and join looking also for title "Pārcelta-D"
	@Query(value = "SELECT * FROM holiday_preholiday_transfered_holiday h "
			+ "JOIN day_status d ON h.id_day_status = d.id_day_status "
			+ "WHERE (EXTRACT(DAY FROM h.date) = ?1 AND EXTRACT(MONTH FROM h.date) = ?2 AND EXTRACT(YEAR FROM h.date) = ?3 ) AND d.day_title='Pārcelta-D'",
			nativeQuery = true)
	HolidayPreholidayTransferedHoliday findByDateAndTransferedWorkDayRemark(int day, int month, int year);
	
	//search and join looking also for title "Pārcelta-B"
	@Query(value = "SELECT * FROM holiday_preholiday_transfered_holiday h "
			+ "JOIN day_status d ON h.id_day_status = d.id_day_status "
			+ "WHERE (EXTRACT(DAY FROM h.date) = ?1 AND EXTRACT(MONTH FROM h.date) = ?2 AND EXTRACT(YEAR FROM h.date) = ?3 ) AND d.day_title='Pārcelta-B'",
			nativeQuery = true)
	HolidayPreholidayTransferedHoliday findByDateAndTransferedHolidayRemark(int day, int month, int year);
	
	//search and join looking also for title "Pārcelta-B"
	@Query(value = "SELECT * FROM holiday_preholiday_transfered_holiday h "
			+ "JOIN day_status d ON h.id_day_status = d.id_day_status "
			+ "WHERE (EXTRACT(DAY FROM h.date) = ?1 AND EXTRACT(MONTH FROM h.date) = ?2 AND EXTRACT(YEAR FROM h.date) = ?3 ) AND d.day_title='Pirmssvētku'",
			nativeQuery = true)
	HolidayPreholidayTransferedHoliday findByDateAndPreHolidya(int day, int month, int year);
}
