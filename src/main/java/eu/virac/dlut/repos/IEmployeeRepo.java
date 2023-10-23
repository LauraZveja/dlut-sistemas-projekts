package eu.virac.dlut.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.Employee;

public interface IEmployeeRepo extends CrudRepository<Employee, Integer>{

//	@Query(value = "SELECT e.id_employee, e.is_elected, e.name, e.surname, e.position, e.is_elected, e.work_contract_no_date, e.id_department, e.id_employee FROM employee e "
//			+ "JOIN order_work ow ON e.id_employee=ow.id_employee "
//			+ "JOIN finance_order_work fow ON ow.id_order=fow.id_ord_work "
//			+ "JOIN project p ON fow.id_project=p.id_project WHERE p.id_project = ?1 ",
//			nativeQuery = true)
//	ArrayList<Employee> getAllEmployeesInOneProject(int projId);
	
	//ielikts select everything
	@Query(value = "SELECT * FROM employee e "
			+ "JOIN order_work ow ON e.id_employee=ow.id_employee "
			+ "JOIN finance_order_work fow ON ow.id_order=fow.id_ord_work "
			+ "JOIN project p ON fow.id_project=p.id_fin_source WHERE p.id_fin_source = ?1 ",
			nativeQuery = true)
	ArrayList<Employee> getAllEmployeesInOneProject(int projId);

	@Query(value = "SELECT * FROM employee e "
			+ "JOIN order_work ow ON e.id_employee=ow.id_employee "
			+ "JOIN finance_order_work fow ON ow.id_order=fow.id_ord_work "
			+ "JOIN base_fin b ON fow.id_base_fin=b.id_fin_source WHERE b.id_fin_source = ?1 ",
			nativeQuery = true)
	ArrayList<Employee> getAllEmployeesInBaseFin(int baseFinId);

	@Query(value = "SELECT e.id_employee, e.is_elected, e.name, e.surname, e.position, e.is_elected, e.work_contract_no_date, e.id_department, e.id_employee FROM employee e "
			+ "JOIN order_work ow ON e.id_employee=ow.id_employee "
			+ "JOIN finance_order_work fow ON ow.id_order=fow.id_ord_work "
			+ "JOIN academic_work_load awl ON fow.id_ac_work_load=awl.id_fin_source WHERE awl.id_fin_source = ?1 ",
			nativeQuery = true)
	ArrayList<Employee> getAllEmployeesInAcademicWorkLoad(int academicWorkLoadId);

	@Query(value = "SELECT * FROM employee e "
			+ "JOIN order_work ow ON e.id_employee=ow.id_employee "
			+ "JOIN finance_order_work fow ON ow.id_order=fow.id_ord_work "
			+ "JOIN indirect_vuas i ON fow.id_indirectvuas=i.id_fin_source WHERE i.id_fin_source = ?1 ",
			nativeQuery = true)
	ArrayList<Employee> getAllEmployeesInIndirectVuas(int indirectVuasId);

}
