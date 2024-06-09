package eu.virac.dlut.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.Employee;
import org.springframework.data.repository.query.Param;

public interface IEmployeeRepo extends CrudRepository<Employee, Integer>{

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
