package eu.virac.dlut.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.OrderVacation;
import org.springframework.data.repository.query.Param;

public interface IOrderVacationRepo extends CrudRepository<OrderVacation, Integer>{

	@Query(value = "CALL FindByDateAndOrderVacationIDAndDesignation(:day, :month, :year, :employeeId);", nativeQuery = true)
	OrderVacation findByDateAndOrderVacationIDAndDesignation(@Param("day") int day,
															 @Param("month") int month,
															 @Param("year") int year,
															 @Param("employeeId") int employeeId);


}
