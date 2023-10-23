package eu.virac.dlut.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.FinanceOrderWork;

public interface IFinanceOrderWorkRepo extends CrudRepository<FinanceOrderWork, Integer>{

	@Query(value = "SELECT fow.position_in_project FROM finance_order_work fow "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE emp.id_employee = ?1 AND fow.id_project = ?2 ", nativeQuery = true)
	String getPositionInProjectForEmployee(int idEmployee, int idFinSource);

	FinanceOrderWork findByOrdWorkIdOrder(int ordWorkId);

	@Query(value = "SELECT fow.pay_per_hour FROM finance_order_work fow "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE emp.id_employee = ?1 AND fow.id_project = ?2 ", nativeQuery = true)
	double getPayPerHourByEmployeeAndProject(int employeeId, int projectId);
	
	@Query(value = "SELECT fow.pay_per_hour FROM finance_order_work fow "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE emp.id_employee = ?1 AND (fow.id_project = ?2 OR fow.id_base_fin = ?2 OR fow.id_ac_work_load = ?2 OR fow.id_indirectvuas = ?2 )", nativeQuery = true)
	double getPayPerHourByEmployeeInFinSource(int employeeId, int finSourceId);

	//ArrayList<FinanceOrderWork> findByIdBaseFinNotNull();

	@Query(value = "SELECT * FROM finance_order_work fow "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE emp.id_employee = ?1 AND (fow.id_project = ?2 OR fow.id_base_fin = ?2 OR fow.id_ac_work_load = ?2 OR fow.id_indirectvuas = ?2 ) AND fow.position_in_project = ?3 ", nativeQuery = true)
	FinanceOrderWork getFinanceOrderWorkByEmployeeIdAndFinSourceTitleAndPosition(int idEmployee, int financeSourceId, String Position);

	@Query(value = "SELECT * FROM finance_order_work fow "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE emp.id_employee = ?1 "
			+ "AND (fow.id_project = ?2 OR fow.id_base_fin = ?2 OR fow.id_ac_work_load = ?2 OR fow.id_indirectvuas = ?2 ) ", nativeQuery = true)
	FinanceOrderWork getByEmployeeIdAndFinanceSourceTitle(int employeeId, int financeSourceId);
}
