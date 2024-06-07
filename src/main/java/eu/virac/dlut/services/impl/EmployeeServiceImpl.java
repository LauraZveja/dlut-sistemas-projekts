package eu.virac.dlut.services.impl;

import java.util.ArrayList;

import eu.virac.dlut.models.Department;
import eu.virac.dlut.models.helpers.EmployeeDTO;
import eu.virac.dlut.repos.IDepartmentRepo;
import jakarta.persistence.EntityNotFoundException;
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
		ArrayList<Employee> allEmployees = (ArrayList<Employee>) employeeRepo.findAll();
		ArrayList<Employee> activeEmployees = new ArrayList<>();
		for (Employee employee : allEmployees) {
			if (!employee.isDeleted()) {
				activeEmployees.add(employee);
			}
		}
		return activeEmployees;
	}

	@Override
	public ArrayList<Employee> selectAllEmployeesInOneProject(int projectId) {
		ArrayList<Employee> res =  employeeRepo.getAllEmployeesInOneProject(projectId);
		ArrayList<Employee> activeEmployees = new ArrayList<>();
		for (Employee employee : res) {
			if (!employee.isDeleted()) {
				activeEmployees.add(employee);
			}
		}
		return activeEmployees;
	}

	@Override
	public Employee selectOneEmployeeById(int employeeId) throws Exception {
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new Exception("Employee not found"));
		if (employee.isDeleted()) {
			throw new Exception("Employee is deleted.");
		}
		return employee;
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
		Employee existingEmployee = employeeRepo.findByWorkContractNoDate(employeeDTO.getWorkContractNoDate());

		if (department != null && existingEmployee == null) {
			Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSurname(), employeeDTO.getPosition(), employeeDTO.isElected(), employeeDTO.getWorkContractNoDate(), department);
			employeeRepo.save(employee);
			employeeDTO.setIdEmployee(employee.getIdEmployee());
			return employeeDTO;
		} else {
			throw new Exception("Department not found or employee with this work contract number already exists.");
		}
	}

	@Override
	public ArrayList<EmployeeDTO> retrieveAllDataForEmployees() {
		ArrayList<EmployeeDTO> result = new ArrayList<>();
		ArrayList<Employee> allEmployees = (ArrayList<Employee>) employeeRepo.findAll();

		for (Employee temp : allEmployees){
			if (!temp.isDeleted()) {
				result.add(new EmployeeDTO(temp.getIdEmployee(), temp.getName(), temp.getSurname(), temp.getPosition(), temp.isElected(), temp.getWorkContractNoDate(), temp.getDepartment().getTitle()));
			}
		}
		return result;
	}

	@Override
	public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO) throws Exception {
		Employee employee = selectOneEmployeeById(employeeDTO.getIdEmployee());

		boolean isUpdated = false;

		if (employeeDTO.getName() != null && !employeeDTO.getName().equals(employee.getName())) {
			employee.setName(employeeDTO.getName());
			isUpdated = true;
		}
		if (employeeDTO.getSurname() != null && !employeeDTO.getSurname().equals(employee.getSurname())) {
			employee.setSurname(employeeDTO.getSurname());
			isUpdated = true;
		}
		if (employeeDTO.getPosition() != null && !employeeDTO.getPosition().equals(employee.getPosition())) {
			employee.setPosition(employeeDTO.getPosition());
			isUpdated = true;
		}
		if (employeeDTO.getWorkContractNoDate() != null && !employeeDTO.getWorkContractNoDate().equals(employee.getWorkContractNoDate())) {
			employee.setWorkContractNoDate(employeeDTO.getWorkContractNoDate());
			isUpdated = true;
		}
		if (employeeDTO.isElected() != employee.isElected()) {
			employee.setElected(employeeDTO.isElected());
			isUpdated = true;
		}

		if (isUpdated) {
			employeeRepo.save(employee);
		}

		return new EmployeeDTO(employee.getIdEmployee(), employee.getName(), employee.getSurname(), employee.getPosition(), employee.isElected(), employee.getWorkContractNoDate(), employee.getDepartment().getTitle());
	}


	@Override
	public void deleteEmployeeById(EmployeeDTO employeeDTO) {
		Employee employee = employeeRepo.findById(employeeDTO.getIdEmployee())
				.orElseThrow(() -> new EntityNotFoundException("Employee not found."));
		employee.setDeleted(true);
		employeeRepo.save(employee);
	}

}
