package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    String orderNo = "12345";
    String newOrderNo = "54321";
    LocalDate date = LocalDate.of(2024, 6, 1);
    LocalDate newDate = LocalDate.of(2024, 7, 1);
    LocalDate startDate = LocalDate.of(2024, 6, 15);
    LocalDate newStartDate = LocalDate.of(2024, 7, 15);
    LocalDate endDate = LocalDate.of(2024, 6, 30);
    LocalDate newEndDate = LocalDate.of(2024, 7, 30);
    String designation = "IA";
    Employee employee = new Employee();
    Employee newEmployee = new Employee();

    Order orderWithoutEndDate = new Order(orderNo, date, startDate, employee);
    Order orderWithEndDate = new Order(orderNo, date, startDate, endDate, employee);
    Order order = new Order();

    @Test
    void testOrderCreationWithoutEndDate() {
        assertEquals(orderNo, orderWithoutEndDate.getOrderNo());
        assertEquals(date, orderWithoutEndDate.getDate());
        assertEquals(startDate, orderWithoutEndDate.getStartDate());
        assertEquals(employee, orderWithoutEndDate.getEmployee());
        assertNull(orderWithoutEndDate.getEndDate());
    }

    @Test
    void testOrderCreationWithEndDate() {
        assertEquals(orderNo, orderWithEndDate.getOrderNo());
        assertEquals(date, orderWithEndDate.getDate());
        assertEquals(startDate, orderWithEndDate.getStartDate());
        assertEquals(endDate, orderWithEndDate.getEndDate());
        assertEquals(employee, orderWithEndDate.getEmployee());
    }

    @Test
    void testSettersOrderTest() {
        order.setOrderNo(newOrderNo);
        order.setDate(newDate);
        order.setStartDate(newStartDate);
        order.setEndDate(newEndDate);
        order.setDesignation(designation);
        order.setEmployee(newEmployee);

        assertEquals(newOrderNo, order.getOrderNo());
        assertEquals(newDate, order.getDate());
        assertEquals(newStartDate, order.getStartDate());
        assertEquals(newEndDate, order.getEndDate());
        assertEquals(designation, order.getDesignation());
        assertEquals(newEmployee, order.getEmployee());
    }

    @Test
    void testGettersOrderTest() {
        assertEquals(orderNo, orderWithoutEndDate.getOrderNo());
        assertEquals(date, orderWithoutEndDate.getDate());
        assertEquals(startDate, orderWithoutEndDate.getStartDate());
        assertEquals(employee, orderWithoutEndDate.getEmployee());
        assertNull(orderWithoutEndDate.getEndDate());
    }

    @Test
    void testOrderToString() {
        String expected = "Order(idOrder=0, orderNo=12345, date=2024-06-01, startDate=2024-06-15, endDate=null, designation=null, employee=Employee(idEmployee=0, name=null, surname=null, position=null, isElected=false, workContractNoDate=null, department=null, isDeleted=false))";
        assertEquals(expected, orderWithoutEndDate.toString());
    }

}