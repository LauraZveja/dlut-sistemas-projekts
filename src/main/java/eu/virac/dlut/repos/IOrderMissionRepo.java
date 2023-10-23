package eu.virac.dlut.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.OrderMission;


public interface IOrderMissionRepo extends CrudRepository<OrderMission, Integer> {

	@Query(value = "SELECT * FROM order_mission om "
			+ "JOIN employee_time_sheet ets ON om.id_order = ets.id_ord_mission "
			+ "WHERE (EXTRACT(DAY FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 AND EXTRACT(YEAR FROM ets.year_month_day) = ?3 ) AND om.designation = 'KD' "
			+ "AND om.id_employee = ?4 ",
			nativeQuery = true)
	OrderMission findByDateAndOrderWorkMissionIDAndDesignation(int day, int month, int year, int employeeId);

	

}
