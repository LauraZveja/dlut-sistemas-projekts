package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderOtherTest {

    String orderNo = "12345";
    LocalDate date = LocalDate.of(2024, 6, 1);
    LocalDate startDate = LocalDate.of(2024, 6, 15);
    LocalDate endDate = LocalDate.of(2024, 6, 30);
    String remarks = "Darba samaksa saglabāsies";
    Employee employee = new Employee();
    EmployeeTimeSheet employeeTimeSheet = new EmployeeTimeSheet();

    OrderOther orderOther = new OrderOther(orderNo, date, startDate, endDate, employee, remarks);

    @Test
    void testOrderOtherCreation() {
        assertEquals(orderNo, orderOther.getOrderNo());
        assertEquals(date, orderOther.getDate());
        assertEquals(startDate, orderOther.getStartDate());
        assertEquals(endDate, orderOther.getEndDate());
        assertEquals(employee, orderOther.getEmployee());
        assertEquals(remarks, orderOther.getRemarks());
    }

    @Test
    void testSetRemarks() {
        String newRemarks = "Darba samaksa nesaglabāsies";
        orderOther.setRemarks(newRemarks);
        assertEquals(newRemarks, orderOther.getRemarks());
    }

    @Test
    void testEmplTimeSheetsInitialization() {
        OrderOther oo = new OrderOther();
        oo.setEmplTimeSheets(new ArrayList<>());
        assertNotNull(oo.getEmplTimeSheets());
        assertTrue(oo.getEmplTimeSheets().isEmpty());
    }

    @Test
    void testAddEmployeeTimeSheet() {
        orderOther.setEmplTimeSheets(new ArrayList<>());
        orderOther.addEmployeeTimeSheetToOrderOther(employeeTimeSheet);
        assertTrue(orderOther.getEmplTimeSheets().contains(employeeTimeSheet));
    }

    @Test
    void testRemoveEmployeeTimeSheet() {
        orderOther.setEmplTimeSheets(new ArrayList<>());
        orderOther.addEmployeeTimeSheetToOrderOther(employeeTimeSheet);
        orderOther.removeEmployeeTimeSheetFromOrderOther(employeeTimeSheet);
        assertFalse(orderOther.getEmplTimeSheets().contains(employeeTimeSheet));
    }

    @Test
    void testOrderOtherToString() {
        String expected = "OrderOther(remarks=Darba samaksa saglabāsies)";
        assertEquals(expected, orderOther.toString());
    }

}