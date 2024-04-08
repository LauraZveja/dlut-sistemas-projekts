package eu.virac.dlut.services.impl;

import java.util.ArrayList;

import eu.virac.dlut.models.Department;
import eu.virac.dlut.models.helpers.EmployeeDTO;
import eu.virac.dlut.repos.IDepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.Employee;
import eu.virac.dlut.repos.IEmployeeRepo;
import eu.virac.dlut.services.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	
	@Autowired
	IEmployeeRepo employeeRepo;

	@Autowired
	IDepartmentRepo departmentRepo;

	@Override
	public ArrayList<Employee> selectAllEmployees() {
		return (ArrayList<Employee>) employeeRepo.findAll();
	}

	@Override
	public ArrayList<Employee> selectAllEmployeesInOneProject(int projectId) {
		ArrayList<Employee> res =  employeeRepo.getAllEmployeesInOneProject(projectId);
		return res;
	}

	@Override
	public Employee selectOneEmployeeById(int employeeId) throws Exception{
			if (employeeRepo.existsById(employeeId))
				return employeeRepo.findById(employeeId).get();
			else
				throw new Exception("Darbinieka id nav pareizs");
		}

	@Override
	public ArrayList<Employee> selectAllEmployeesInBaseFin(int baseFinId) {
		ArrayList<Employee> res =  employeeRepo.getAllEmployeesInBaseFin(baseFinId);
		return res;
	}

	@Override
	public ArrayList<Employee> selectAllEmployeesInAcademicWorkLoad(int academicWorkLoadId) {
		ArrayList<Employee> res =  employeeRepo.getAllEmployeesInAcademicWorkLoad(academicWorkLoadId);
		return res;
	}

	@Override
	public ArrayList<Employee> selectAllEmployeesInIndirectVuas(int indirectVuasId) {
		ArrayList<Employee> res =  employeeRepo.getAllEmployeesInIndirectVuas(indirectVuasId);
		return res;
	}

	@Override
	public EmployeeDTO insertEmployee(EmployeeDTO employeeDTO) throws Exception {
		Department department = departmentRepo.findByTitle(employeeDTO.getDepartmentName());

		if (department != null) {
			Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSurname(), employeeDTO.getPosition(), employeeDTO.isElected(), employeeDTO.getWorkContractNoAndDate(), department);
			employeeRepo.save(employee);
			return employeeDTO;
		} else {
			throw new Exception("Nepareizs departamenta nosaukums");
		}
	}

}
