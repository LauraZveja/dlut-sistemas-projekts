package eu.virac.dlut.services;

import java.util.ArrayList;

import eu.virac.dlut.models.Employee;

public interface IEmployeeService {

	ArrayList<Employee> selectAllEmployees();
	
	ArrayList<Employee> selectAllEmployeesInOneProject(int projectId);
	
	Employee selectOneEmployeeById(int employeeId) throws Exception;

	ArrayList<Employee> selectAllEmployeesInBaseFin(int baseFinId);

	ArrayList<Employee> selectAllEmployeesInAcademicWorkLoad(int academicWorkLoadId);

	ArrayList<Employee> selectAllEmployeesInIndirectVuas(int indirectVuasId);

	
}
