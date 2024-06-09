package eu.virac.dlut.models.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDTOTest {

    String name = "Jānis";
    String surname = "Bērziņš";
    String position = "Vadītājs";
    boolean isElected = true;
    String workContractNoDate = "2023-01-01";
    String departmentName = "Personāldaļa";
    int idEmployee = 1;

    EmployeeDTO employeeDefault = new EmployeeDTO();
    EmployeeDTO employeeWithBasicDetails = new EmployeeDTO(name, surname);
    EmployeeDTO employeeWithFullDetails = new EmployeeDTO(idEmployee, name, surname, position, isElected, workContractNoDate, departmentName);

    @Test
    void testDefaultConstructor() {
        assertNotNull(employeeDefault);
    }

    @Test
    void testParameterizedConstructorWithBasicDetails() {
        assertEquals(name, employeeWithBasicDetails.getName());
        assertEquals(surname, employeeWithBasicDetails.getSurname());
    }

    @Test
    void testParameterizedConstructorWithFullDetails() {
        assertEquals(idEmployee, employeeWithFullDetails.getIdEmployee());
        assertEquals(name, employeeWithFullDetails.getName());
        assertEquals(surname, employeeWithFullDetails.getSurname());
        assertEquals(position, employeeWithFullDetails.getPosition());
        assertTrue(employeeWithFullDetails.isElected());
        assertEquals(workContractNoDate, employeeWithFullDetails.getWorkContractNoDate());
        assertEquals(departmentName, employeeWithFullDetails.getDepartmentName());
    }

    @Test
    void testSetAndGetMethods() {
        employeeDefault.setName(name);
        employeeDefault.setSurname(surname);
        employeeDefault.setPosition(position);
        employeeDefault.setElected(isElected);
        employeeDefault.setWorkContractNoDate(workContractNoDate);
        employeeDefault.setDepartmentName(departmentName);
        employeeDefault.setIdEmployee(idEmployee);

        assertEquals(name, employeeDefault.getName());
        assertEquals(surname, employeeDefault.getSurname());
        assertEquals(position, employeeDefault.getPosition());
        assertTrue(employeeDefault.isElected());
        assertEquals(workContractNoDate, employeeDefault.getWorkContractNoDate());
        assertEquals(departmentName, employeeDefault.getDepartmentName());
        assertEquals(idEmployee, employeeDefault.getIdEmployee());
    }

    @Test
    void testToString() {
        String expected = "EmployeeDTO(name=Jānis, surname=Bērziņš, position=Vadītājs, isElected=true, workContractNoDate=2023-01-01, departmentName=Personāldaļa, idEmployee=1)";
        assertEquals(expected, employeeWithFullDetails.toString());
    }

}