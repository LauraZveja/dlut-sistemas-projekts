package eu.virac.dlut.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.OrderVacation;

public interface IOrderVacationRepo extends CrudRepository<OrderVacation, Integer>{

	@Query(value = "SELECT * FROM order_vacation ov "
			+ "JOIN employee_time_sheet ets ON ov.id_order = ets.id_ord_vacation "
			+ "WHERE (EXTRACT(DAY FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 AND EXTRACT(YEAR FROM ets.year_month_day) = ?3 ) AND ov.designation = 'AI' "
			+ "AND ov.id_employee = ?4 ",
			nativeQuery = true)
	OrderVacation findByDateAndOrderVacationIDAndDesignation(int day, int month, int year, int employeeId);

}
