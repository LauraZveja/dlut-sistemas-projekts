package eu.virac.dlut.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.FullTimeEquivalent;

public interface IFullTimeEquivalentRepo extends CrudRepository<FullTimeEquivalent, Integer>{

	//paartaisiits, izmantojot atsleegvaardus metozu nosaukumos
//	@Query(value = "SELECT fte.vacation_hours FROM full_time_equivalent fte JOIN finance_order_work fow ON ets.id_fin_ord_work_employee=fow.id_fin_ord_work "
//			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
//			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
//			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND emp.id_employee = ?3 "
//			+ "AND (fow.id_project = ?4 OR fow.id_base_fin = ?4 OR fow.id_ac_work_load = ?4 OR id_indirectvuas = ?4 ) ",
//			nativeQuery = true)
//	double findVacationHoursByDateAndEmployeeAndFinSource(int year, int month, int idEmployee, int finSourceId);

	FullTimeEquivalent findByYearAndMonthAndEmployeeIdEmployeeAndFinanceSourceIdFinSource(int year, int month, int employeeId,
			int finSourceId);

}
