package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderWorkTest {

    String orderNo = "12345";
    LocalDate date = LocalDate.of(2024, 6, 1);
    LocalDate startDate = LocalDate.of(2024, 6, 15);
    LocalDate endDate = LocalDate.of(2024, 6, 30);
    boolean isActive = true;
    Employee employee = new Employee();
    FinanceOrderWork financeOrderWork = new FinanceOrderWork();

    OrderWork orderWorkWithoutEndDate = new OrderWork(orderNo, date, startDate, employee, isActive);
    OrderWork orderWorkWithEndDate = new OrderWork(orderNo, date, startDate, endDate, employee, isActive);
    OrderWork orderWork = new OrderWork();

    @Test
    void testOrderWorkCreationWithoutEndDate() {
        assertEquals(orderNo, orderWorkWithoutEndDate.getOrderNo());
        assertEquals(date, orderWorkWithoutEndDate.getDate());
        assertEquals(startDate, orderWorkWithoutEndDate.getStartDate());
        assertEquals(employee, orderWorkWithoutEndDate.getEmployee());
        assertTrue(orderWorkWithoutEndDate.isActive());
        assertNull(orderWorkWithoutEndDate.getEndDate());
    }

    @Test
    void testOrderWorkCreationWithEndDate() {
        assertEquals(orderNo, orderWorkWithEndDate.getOrderNo());
        assertEquals(date, orderWorkWithEndDate.getDate());
        assertEquals(startDate, orderWorkWithEndDate.getStartDate());
        assertEquals(endDate, orderWorkWithEndDate.getEndDate());
        assertEquals(employee, orderWorkWithEndDate.getEmployee());
        assertTrue(orderWorkWithEndDate.isActive());
    }

    @Test
    void testSetActive() {
        orderWork.setActive(false);
        assertFalse(orderWork.isActive());
    }

    @Test
    void testFinOrdWorkColInitialization() {
        OrderWork ow = new OrderWork();
        ow.setFinOrdWorkCol(new ArrayList<>());
        assertNotNull(ow.getFinOrdWorkCol());
        assertTrue(ow.getFinOrdWorkCol().isEmpty());
    }

    @Test
    void testAddFinanceOrderWork() {
        orderWork.setFinOrdWorkCol(new ArrayList<>());
        orderWork.addFinanceOrderWorkToOrderWork(financeOrderWork);
        assertTrue(orderWork.getFinOrdWorkCol().contains(financeOrderWork));
    }

    @Test
    void testRemoveFinanceOrderWork() {
        orderWork.setFinOrdWorkCol(new ArrayList<>());
        orderWork.addFinanceOrderWorkToOrderWork(financeOrderWork);
        orderWork.removeFinanceOrderWorkFromOrderWork(financeOrderWork);
        assertFalse(orderWork.getFinOrdWorkCol().contains(financeOrderWork));
    }

    @Test
    void testOrderWorkToString() {
        String expected = "OrderWork(isActive=true)";
        assertEquals(expected, orderWorkWithEndDate.toString());
    }

}