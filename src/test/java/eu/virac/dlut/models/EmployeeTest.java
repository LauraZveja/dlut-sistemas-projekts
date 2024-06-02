package eu.virac.dlut.models;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class EmployeeTest {

    Department d1 = new Department("Augstas veiktspējas skaitļošanas nodaļa", "HCP");

    Employee emp1 = new Employee();
    Employee e1 = new Employee("Karina", "Šķirmante", "Pētniece", true, "P77/1, 2011.11.12", d1);

    @Test
    public void testEmployee(){
        assertEquals("Karina", e1.getName());
        assertEquals("Šķirmante", e1.getSurname());
        assertEquals("Pētniece", e1.getPosition());
        assertTrue(e1.isElected());
        assertEquals("P77/1, 2011.11.12", e1.getWorkContractNoDate());
        assertEquals(d1, e1.getDepartment());
    }

    @Test
    public void testEmployeeSetters(){
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
    public void testEmployeeToString() {
        String expected = "Employee(idEmployee=0, name=Karina, surname=Šķirmante, position=Pētniece, isElected=true, workContractNoDate=P77/1, 2011.11.12, department=Department(idDepartment=0, title=Augstas veiktspējas skaitļošanas nodaļa, abbreviation=HCP, isDeleted=false), isDeleted=false)";
        assertEquals(expected, e1.toString());
    }
    


}
