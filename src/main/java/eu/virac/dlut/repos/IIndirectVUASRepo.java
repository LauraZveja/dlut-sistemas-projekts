package eu.virac.dlut.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.IndirectVUAS;

public interface IIndirectVUASRepo extends CrudRepository<IndirectVUAS, Integer> {

	@Query(value = "SELECT iv.id_fin_source, iv.code, iv.title FROM indirect_vuas iv "
			+ "JOIN finance_order_work fow ON iv.id_fin_source=fow.id_indirectvuas "
			+ "JOIN employee_time_sheet ets ON fow.id_fin_ord_work=ets.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) "
			+ "AND emp.id_employee = ?3 GROUP BY iv.id_fin_source", nativeQuery = true)
	ArrayList<IndirectVUAS> findByDateAndEmployee(int year, int month, int employeeId);

}
