package eu.virac.dlut.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.HolidayPreholidayTransferedHoliday;
import org.springframework.data.repository.query.Param;

public interface IHolidayPreholidayTransferedHolidayRepo extends CrudRepository<HolidayPreholidayTransferedHoliday, Integer>{

	
	//search and join looking also for title "Sveetki"
	@Query(value = "CALL FindByDateAndHolidayTitle(:day, :month, :year);", nativeQuery = true)
	HolidayPreholidayTransferedHoliday findByDateAndHolidayTitle(@Param("day") int day,
																 @Param("month") int month,
																 @Param("year") int year);



	//search and join looking also for title "Pārcelta-D"
	@Query(value = "CALL FindByDateAndTransferredWorkDayRemark(:day, :month, :year);", nativeQuery = true)
	HolidayPreholidayTransferedHoliday findByDateAndTransferedWorkDayRemark(@Param("day") int day,
																			@Param("month") int month,
																			@Param("year") int year);

	//search and join looking also for title "Pārcelta-B"
	@Query(value = "CALL FindByDateAndTransferredHolidayRemark(:day, :month, :year);", nativeQuery = true)
	HolidayPreholidayTransferedHoliday findByDateAndTransferedHolidayRemark(@Param("day") int day,
																			@Param("month") int month,
																			@Param("year") int year);

	//search and join looking also for title "Pārcelta-B"
	@Query(value = "CALL FindByDateAndPreHoliday(:day, :month, :year);", nativeQuery = true)
	HolidayPreholidayTransferedHoliday findByDateAndPreHolidya(@Param("day") int day,
															   @Param("month") int month,
															   @Param("year") int year);

}
