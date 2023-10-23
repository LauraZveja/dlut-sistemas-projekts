package eu.virac.dlut.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.Project;

public interface IProjectRepo extends CrudRepository<Project, Integer> {

	
	@Query(value = "SELECT p.id_fin_source, p.code, p.title, p.accounting_acronym, p.contract_no, p.end, p.proj_lenght_months, p.start, p.id_proj_cat, p.id_proj_char, p.is_active FROM project p "
			+ "JOIN finance_order_work fow ON p.id_fin_source=fow.id_project "
			+ "JOIN employee_time_sheet ets ON fow.id_fin_ord_work=ets.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) "
			+ "AND emp.id_employee = ?3 GROUP BY p.title ", nativeQuery = true)
	ArrayList<Project> getProjectsForEmployeeForSpecificDate(int year, int month, int employeeId);

	ArrayList<Project> findByIsActiveTrue();
}


