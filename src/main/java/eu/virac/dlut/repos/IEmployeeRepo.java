package eu.virac.dlut.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.Employee;
import org.springframework.data.repository.query.Param;

public interface IEmployeeRepo extends CrudRepository<Employee, Integer>{

//	@Query(value = "SELECT e.id_employee, e.is_elected, e.name, e.surname, e.position, e.is_elected, e.work_contract_no_date, e.id_department, e.id_employee FROM employee e "
//			+ "JOIN order_work ow ON e.id_employee=ow.id_employee "
//			+ "JOIN finance_order_work fow ON ow.id_order=fow.id_ord_work "
//			+ "JOIN project p ON fow.id_project=p.id_project WHERE p.id_project = ?1 ",
//			nativeQuery = true)
//	ArrayList<Employee> getAllEmployeesInOneProject(int projId);
	
	//ielikts select everything
	@Query(value = "CALL GetAllEmployeesInOneProject(:projId);", nativeQuery = true)
	ArrayList<Employee> getAllEmployeesInOneProject(@Param("projId") int projId);

	@Query(value = "CALL GetAllEmployeesInBaseFin(:baseFinId);", nativeQuery = true)
	ArrayList<Employee> getAllEmployeesInBaseFin(@Param("baseFinId") int baseFinId);

	@Query(value = "CALL GetAllEmployeesInAcademicWorkLoad(:academicWorkLoadId);", nativeQuery = true)
	ArrayList<Employee> getAllEmployeesInAcademicWorkLoad(@Param("academicWorkLoadId") int academicWorkLoadId);

	@Query(value = "CALL GetAllEmployeesInIndirectVuas(:indirectVuasId);", nativeQuery = true)
	ArrayList<Employee> getAllEmployeesInIndirectVuas(@Param("indirectVuasId") int indirectVuasId);

	Employee findByWorkContractNoDate(String workContractNoDate);

}
