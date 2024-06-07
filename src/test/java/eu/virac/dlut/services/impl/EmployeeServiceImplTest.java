package eu.virac.dlut.services.impl;
import eu.virac.dlut.models.Department;
import eu.virac.dlut.models.Employee;
import eu.virac.dlut.models.helpers.EmployeeDTO;
import eu.virac.dlut.repos.IDepartmentRepo;
import eu.virac.dlut.repos.IEmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {


    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private IEmployeeRepo employeeRepo;

    @Mock
    private IDepartmentRepo departmentRepo;

    private Employee employee;
    private Department department;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        department = new Department("Departmentaments", "Abreviatūra");
        employee = new Employee("Džons", "Stirna", "Pētnieks", true, "P77/1, 2024.11.12", department);

        employeeDTO = new EmployeeDTO(employee.getIdEmployee(), "Džons", "Stirna", "Pētnieks", true, "P77/1, 2024.11.12", "Departmentaments");
    }

    @Test
    void selectAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeRepo.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.selectAllEmployees();

        assertEquals(1, result.size());
        verify(employeeRepo, times(1)).findAll();
    }

    @Test
    void selectAllEmployeesInOneProject() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeRepo.getAllEmployeesInOneProject(anyInt())).thenReturn(employees);

        List<Employee> result = employeeService.selectAllEmployeesInOneProject(1);

        assertEquals(1, result.size());
        verify(employeeRepo, times(1)).getAllEmployeesInOneProject(anyInt());
    }

    @Test
    void selectOneEmployeeById() throws Exception {
        when(employeeRepo.findById(anyInt())).thenReturn(Optional.of(employee));

        Employee result = employeeService.selectOneEmployeeById(1);

        assertEquals(employee, result);
        verify(employeeRepo, times(1)).findById(anyInt());
    }

    @Test
    void selectOneEmployeeByIdThrowsExceptionWhenNotFound() {
        when(employeeRepo.findById(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            employeeService.selectOneEmployeeById(1);
        });

        assertEquals("Employee not found", exception.getMessage());
        verify(employeeRepo, times(1)).findById(anyInt());
    }

    @Test
    void selectAllEmployeesInBaseFin() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeRepo.getAllEmployeesInBaseFin(anyInt())).thenReturn(employees);

        List<Employee> result = employeeService.selectAllEmployeesInBaseFin(1);

        assertEquals(1, result.size());
        verify(employeeRepo, times(1)).getAllEmployeesInBaseFin(anyInt());
    }

    @Test
    void selectAllEmployeesInAcademicWorkLoad() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeRepo.getAllEmployeesInAcademicWorkLoad(anyInt())).thenReturn(employees);

        List<Employee> result = employeeService.selectAllEmployeesInAcademicWorkLoad(1);

        assertEquals(1, result.size());
        verify(employeeRepo, times(1)).getAllEmployeesInAcademicWorkLoad(anyInt());
    }

    @Test
    void selectAllEmployeesInIndirectVuas() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeRepo.getAllEmployeesInIndirectVuas(anyInt())).thenReturn(employees);

        List<Employee> result = employeeService.selectAllEmployeesInIndirectVuas(1);

        assertEquals(1, result.size());
        verify(employeeRepo, times(1)).getAllEmployeesInIndirectVuas(anyInt());
    }

    @Test
    void insertEmployee() throws Exception {
        when(departmentRepo.findByTitle(anyString())).thenReturn(department);
        when(employeeRepo.findByWorkContractNoDate(anyString())).thenReturn(null);
        when(employeeRepo.save(any(Employee.class))).thenReturn(employee);

        EmployeeDTO result = employeeService.insertEmployee(employeeDTO);

        assertEquals(employeeDTO, result);
        verify(departmentRepo, times(1)).findByTitle(anyString());
        verify(employeeRepo, times(1)).findByWorkContractNoDate(anyString());
        verify(employeeRepo, times(1)).save(any(Employee.class));
    }

    @Test
    void insertEmployeeThrowsException() {
        when(departmentRepo.findByTitle(anyString())).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> {
            employeeService.insertEmployee(employeeDTO);
        });

        assertEquals("Department not found or employee with this work contract number already exists.", exception.getMessage());
        verify(departmentRepo, times(1)).findByTitle(anyString());
        verify(employeeRepo, times(1)).findByWorkContractNoDate(anyString());
    }

    @Test
    void retrieveAllDataForEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeRepo.findAll()).thenReturn(employees);

        List<EmployeeDTO> result = employeeService.retrieveAllDataForEmployees();

        assertEquals(1, result.size());
        verify(employeeRepo, times(1)).findAll();
    }

    @Test
    void deleteEmployeeById() {
        when(employeeRepo.findById(anyInt())).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeById(employeeDTO);

        assertTrue(employee.isDeleted());
        verify(employeeRepo, times(1)).findById(anyInt());
        verify(employeeRepo, times(1)).save(any(Employee.class));
    }

    @Test
    void deleteEmployeeByIdThrowsException() {
        when(employeeRepo.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            employeeService.deleteEmployeeById(employeeDTO);
        });

        verify(employeeRepo, times(1)).findById(anyInt());
    }
}
