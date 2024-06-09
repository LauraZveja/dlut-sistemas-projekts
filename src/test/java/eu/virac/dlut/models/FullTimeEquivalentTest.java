package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FullTimeEquivalentTest {

    int year = 2024;
    int month = 6;
    double vacationHours = 20.0;
    FinanceSource financeSource = new FinanceSource("Finansējuma avots", "FA123");
    Employee employee = new Employee();

    FullTimeEquivalent fullTimeEquivalent = new FullTimeEquivalent(year, month, employee, financeSource);

    @Test
    void testFullTimeEquivalentCreation() {
        assertEquals(year, fullTimeEquivalent.getYear());
        assertEquals(month, fullTimeEquivalent.getMonth());
        assertEquals(employee, fullTimeEquivalent.getEmployee());
        assertEquals(financeSource, fullTimeEquivalent.getFinanceSource());
    }

    @Test
    void testSetYear() {
        fullTimeEquivalent.setYear(2025);
        assertEquals(2025, fullTimeEquivalent.getYear());
    }

    @Test
    void testSetMonth() {
        fullTimeEquivalent.setMonth(7);
        assertEquals(7, fullTimeEquivalent.getMonth());
    }

    @Test
    void testSetVacationHours() {
        fullTimeEquivalent.setVacationHours(25.0);
        assertEquals(25.0, fullTimeEquivalent.getVacationHours());
    }

    @Test
    void testSetFinanceSource() {
        FinanceSource newFinanceSource = new FinanceSource("Jauns avots", "JA456");
        fullTimeEquivalent.setFinanceSource(newFinanceSource);
        assertEquals(newFinanceSource, fullTimeEquivalent.getFinanceSource());
    }

    @Test
    void testSetEmployee() {
        Employee newEmployee = new Employee();
        fullTimeEquivalent.setEmployee(newEmployee);
        assertEquals(newEmployee, fullTimeEquivalent.getEmployee());
    }

    @Test
    void testFullTimeEquivalentToString() {
        String expected = "FullTimeEquivalent(idFullTimeEquivalent=0, year=2024, month=6, vacationHours=0.0, financeSource=FinanceSource(idFinSource=0, title=Finansējuma avots, code=FA123), employee=Employee(idEmployee=0, name=null, surname=null, position=null, isElected=false, workContractNoDate=null, department=null, isDeleted=false))";
        assertEquals(expected, fullTimeEquivalent.toString());
    }

}