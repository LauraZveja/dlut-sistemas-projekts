package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderVacationTest {

    String orderNo = "12345";
    LocalDate date = LocalDate.of(2024, 6, 1);
    LocalDate startDate = LocalDate.of(2024, 6, 15);
    LocalDate endDate = LocalDate.of(2024, 6, 30);
    Employee employee = new Employee();
    EmployeeTimeSheet employeeTimeSheet = new EmployeeTimeSheet();
    FinanceOrderVacation financeOrderVacation = new FinanceOrderVacation();

    OrderVacation orderVacation = new OrderVacation(orderNo, date, startDate, endDate, employee);

    @Test
    void testOrderVacationCreation() {
        assertEquals(orderNo, orderVacation.getOrderNo());
        assertEquals(date, orderVacation.getDate());
        assertEquals(startDate, orderVacation.getStartDate());
        assertEquals(endDate, orderVacation.getEndDate());
        assertEquals(employee, orderVacation.getEmployee());
    }

    @Test
    void testEmplTimeSheetsInitialization() {
        OrderVacation ov = new OrderVacation();
        ov.setEmplTimeSheets(new ArrayList<>());
        assertNotNull(ov.getEmplTimeSheets());
        assertTrue(ov.getEmplTimeSheets().isEmpty());
    }

    @Test
    void testAddEmployeeTimeSheet() {
        orderVacation.setEmplTimeSheets(new ArrayList<>());
        orderVacation.addEmployeeTimeSheetToOrderVacation(employeeTimeSheet);
        assertTrue(orderVacation.getEmplTimeSheets().contains(employeeTimeSheet));
    }

    @Test
    void testRemoveEmployeeTimeSheet() {
        orderVacation.setEmplTimeSheets(new ArrayList<>());
        orderVacation.addEmployeeTimeSheetToOrderVacation(employeeTimeSheet);
        orderVacation.removeEmployeeTimeSheetFromOrderVacation(employeeTimeSheet);
        assertFalse(orderVacation.getEmplTimeSheets().contains(employeeTimeSheet));
    }

    @Test
    void testFinOrdVacColInitialization() {
        OrderVacation ov = new OrderVacation();
        ov.setFinOrdVacCol(new ArrayList<>());
        assertNotNull(ov.getFinOrdVacCol());
        assertTrue(ov.getFinOrdVacCol().isEmpty());
    }

    @Test
    void testAddFinanceOrderVacation() {
        orderVacation.setFinOrdVacCol(new ArrayList<>());
        orderVacation.addFinanceOrderVacationToOrderVacation(financeOrderVacation);
        assertTrue(orderVacation.getFinOrdVacCol().contains(financeOrderVacation));
    }

    @Test
    void testRemoveFinanceOrderVacation() {
        orderVacation.setFinOrdVacCol(new ArrayList<>());
        orderVacation.addFinanceOrderVacationToOrderVacation(financeOrderVacation);
        orderVacation.removeFinanceOrderFromOrderVacation(financeOrderVacation);
        assertFalse(orderVacation.getFinOrdVacCol().contains(financeOrderVacation));
    }

    @Test
    void testOrderVacationToString() {
        String expected = "OrderVacation()";
        assertEquals(expected, orderVacation.toString());
    }

}