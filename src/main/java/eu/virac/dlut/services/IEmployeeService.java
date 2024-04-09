package eu.virac.dlut.services;

import java.util.ArrayList;

import eu.virac.dlut.models.Employee;
import eu.virac.dlut.models.helpers.EmployeeDTO;

public interface IEmployeeService {

	ArrayList<Employee> selectAllEmployees();
	
	ArrayList<Employee> selectAllEmployeesInOneProject(int projectId);
	
	Employee selectOneEmployeeById(int employeeId) throws Exception;

	ArrayList<Employee> selectAllEmployeesInBaseFin(int baseFinId);

	ArrayList<Employee> selectAllEmployeesInAcademicWorkLoad(int academicWorkLoadId);

	ArrayList<Employee> selectAllEmployeesInIndirectVuas(int indirectVuasId);

	EmployeeDTO insertEmployee(EmployeeDTO employeeDTO) throws Exception;

	ArrayList<EmployeeDTO> retrieveAllDataForEmployees();

	EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO);

	void deleteEmployeeById(EmployeeDTO employeeDTO);

}
