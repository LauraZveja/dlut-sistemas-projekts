package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {


    Department d1 = new Department("Augstas veiktspējas skaitļošanas nodaļa", "HCP");
    Employee emp1 = new Employee();
    Employee e1 = new Employee("Karina", "Šķirmante", "Pētniece", true, "P77/1, 2011.11.12", d1);
    Department department = new Department("Departmentaments", "Abreviatūra");
    Employee employee = new Employee("Džons", "Stirna", "Pētnieks", true, "P77/1, 2024.11.12", department);
    @Test
    void testEmployee(){
        assertEquals("Karina", e1.getName());
        assertEquals("Šķirmante", e1.getSurname());
        assertEquals("Pētniece", e1.getPosition());
        assertTrue(e1.isElected());
        assertEquals("P77/1, 2011.11.12", e1.getWorkContractNoDate());
        assertEquals(d1, e1.getDepartment());
    }
    @Test
    void testEmployeeSetters(){
        emp1.setName("Karina");
        emp1.setSurname("Šķirmante");
        emp1.setPosition("Pētniece");
        emp1.setElected(true);
        emp1.setWorkContractNoDate("P77/1, 2011.11.12");
        emp1.setDepartment(d1);
        assertEquals("Karina", emp1.getName());
        assertEquals("Šķirmante", emp1.getSurname());
        assertEquals("Pētniece", emp1.getPosition());
        assertTrue(emp1.isElected());
        assertEquals("P77/1, 2011.11.12", emp1.getWorkContractNoDate());
        assertEquals(d1, emp1.getDepartment());
    }
    @Test
    void testEmployeeToString() {
        String expected = "Employee(idEmployee=0, name=Karina, surname=Šķirmante, position=Pētniece, isElected=true, workContractNoDate=P77/1, 2011.11.12, department=Department(idDepartment=0, title=Augstas veiktspējas skaitļošanas nodaļa, abbreviation=HCP, isDeleted=false), isDeleted=false)";
        assertEquals(expected, e1.toString());
    }
    @Test
    void addFinancePlanningToEmployee() {
        FinancePlanning financePlanning = new FinancePlanning();
        employee.addFinancePlanningToEmployee(financePlanning);
        assertNotNull(employee.getFinPlanningCol());
        assertTrue(employee.getFinPlanningCol().contains(financePlanning));
    }
    @Test
    void removeFinancePlanningToEmployee() {
        FinancePlanning financePlanning = new FinancePlanning();
        employee.addFinancePlanningToEmployee(financePlanning);
        employee.removeFinancePlanningFromEmployee(financePlanning);
        assertNotNull(employee.getFinPlanningCol());
        assertFalse(employee.getFinPlanningCol().contains(financePlanning));
    }
    @Test
    void testFinPlanningColInitialization() {
        Employee employee = new Employee();
        assertNotNull(employee.getFinPlanningCol());
        assertTrue(employee.getFinPlanningCol().isEmpty());
    }
}