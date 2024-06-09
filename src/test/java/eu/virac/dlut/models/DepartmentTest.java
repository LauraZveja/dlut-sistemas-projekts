package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DepartmentTest {

    Department department = new Department("Augstas veiktspējas skaitļošanas nodaļa", "HCP");
    Employee e1 = new Employee("Karina", "Šķirmante", "Pētniece", true, "P77/1, 2011.11.12", department);
    Employee e2 = new Employee("Džons", "Stirna", "Pētnieks", true, "P77/1, 2024.11.12", department);

    @Test
    void testDepartmentCreation() {
        assertEquals("Augstas veiktspējas skaitļošanas nodaļa", department.getTitle());
        assertEquals("HCP", department.getAbbreviation());
        assertFalse(department.isDeleted());
    }

    @Test
    void testAddEmployeeToDepartment() {
        Department dept = new Department("Augstas veiktspējas skaitļošanas nodaļa", "HCP");
        dept.addEmployeeToDepartment(e1);
        assertNotNull(dept.getEmployees());
        assertTrue(dept.getEmployees().contains(e1));
    }

    @Test
    void testRemoveEmployeeFromDepartment() {
        Department dept = new Department("Augstas veiktspējas skaitļošanas nodaļa", "HCP");
        dept.addEmployeeToDepartment(e1);
        dept.removeEmployeeFromDepartment(e1);
        assertNotNull(dept.getEmployees());
        assertFalse(dept.getEmployees().contains(e1));
    }

    @Test
    void testDepartmentSetters() {
        department.setTitle("Jauna Nodaļa");
        department.setAbbreviation("JN");
        department.setDeleted(true);
        assertEquals("Jauna Nodaļa", department.getTitle());
        assertEquals("JN", department.getAbbreviation());
        assertTrue(department.isDeleted());
    }

    @Test
    void testEmployeesInitialization() {
        Department dept = new Department();
        assertNotNull(dept.getEmployees());
        assertTrue(dept.getEmployees().isEmpty());
    }

    @Test
    void testDepartmentToString() {
        String expected = "Department(idDepartment=0, title=Augstas veiktspējas skaitļošanas nodaļa, abbreviation=HCP, isDeleted=false)";
        assertEquals(expected, department.toString());
    }

}