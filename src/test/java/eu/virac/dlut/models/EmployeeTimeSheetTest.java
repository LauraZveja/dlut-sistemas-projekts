package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class EmployeeTimeSheetTest {

    FinanceOrderWork financeOrderWork = new FinanceOrderWork();
    OrderMission orderMission = new OrderMission();
    OrderVacation orderVacation = new OrderVacation();
    OrderOther orderOther = new OrderOther();
    LocalDate date = LocalDate.of(2024, 6, 1);
    EmployeeTimeSheet employeeTimeSheetWithMissionOrder = new EmployeeTimeSheet(date, financeOrderWork, 8.0, "Programmēšana", orderMission, true);
    EmployeeTimeSheet employeeTimeSheetWithVacationOrder = new EmployeeTimeSheet(date, financeOrderWork, 8.0, "Lasīšana", orderVacation, true);
    EmployeeTimeSheet employeeTimeSheetWithOtherOrder = new EmployeeTimeSheet(date, financeOrderWork, 8.0, "Sanāksme", orderOther, true);
    EmployeeTimeSheet employeeTimeSheetWithoutOrder = new EmployeeTimeSheet(date, financeOrderWork, 8.0, "Apmācība", true);
    EmployeeTimeSheet employeeTimeSheetWithSickLeave = new EmployeeTimeSheet(date, financeOrderWork, 8.0, "Atpūta", "Slimības lapa", true);

    @Test
    void testEmployeeTimeSheetCreationWithMissionOrder() {
        assertEquals(date, employeeTimeSheetWithMissionOrder.getYearMonthDay());
        assertEquals(financeOrderWork, employeeTimeSheetWithMissionOrder.getFinOrdWork());
        assertEquals(8.0, employeeTimeSheetWithMissionOrder.getHoursWorkedDayInFinSource());
        assertEquals("Programmēšana", employeeTimeSheetWithMissionOrder.getActivities());
        assertEquals(orderMission, employeeTimeSheetWithMissionOrder.getOrdMission());
        assertTrue(employeeTimeSheetWithMissionOrder.isEditableForEmployee());
    }

    @Test
    void testEmployeeTimeSheetCreationWithVacationOrder() {
        assertEquals(date, employeeTimeSheetWithVacationOrder.getYearMonthDay());
        assertEquals(financeOrderWork, employeeTimeSheetWithVacationOrder.getFinOrdWork());
        assertEquals(8.0, employeeTimeSheetWithVacationOrder.getHoursWorkedDayInFinSource());
        assertEquals("Lasīšana", employeeTimeSheetWithVacationOrder.getActivities());
        assertEquals(orderVacation, employeeTimeSheetWithVacationOrder.getOrdVacation());
        assertTrue(employeeTimeSheetWithVacationOrder.isEditableForEmployee());
    }

    @Test
    void testEmployeeTimeSheetCreationWithOtherOrder() {
        assertEquals(date, employeeTimeSheetWithOtherOrder.getYearMonthDay());
        assertEquals(financeOrderWork, employeeTimeSheetWithOtherOrder.getFinOrdWork());
        assertEquals(8.0, employeeTimeSheetWithOtherOrder.getHoursWorkedDayInFinSource());
        assertEquals("Sanāksme", employeeTimeSheetWithOtherOrder.getActivities());
        assertEquals(orderOther, employeeTimeSheetWithOtherOrder.getOrdOther());
        assertTrue(employeeTimeSheetWithOtherOrder.isEditableForEmployee());
    }

    @Test
    void testEmployeeTimeSheetCreationWithoutOrderAndSickLeave() {
        assertEquals(date, employeeTimeSheetWithoutOrder.getYearMonthDay());
        assertEquals(financeOrderWork, employeeTimeSheetWithoutOrder.getFinOrdWork());
        assertEquals(8.0, employeeTimeSheetWithoutOrder.getHoursWorkedDayInFinSource());
        assertEquals("Apmācība", employeeTimeSheetWithoutOrder.getActivities());
        assertTrue(employeeTimeSheetWithoutOrder.isEditableForEmployee());
    }

    @Test
    void testEmployeeTimeSheetCreationWithSickLeave() {
        assertEquals(date, employeeTimeSheetWithSickLeave.getYearMonthDay());
        assertEquals(financeOrderWork, employeeTimeSheetWithSickLeave.getFinOrdWork());
        assertEquals(8.0, employeeTimeSheetWithSickLeave.getHoursWorkedDayInFinSource());
        assertEquals("Atpūta", employeeTimeSheetWithSickLeave.getActivities());
        assertEquals("Slimības lapa", employeeTimeSheetWithSickLeave.getSickLeave());
        assertTrue(employeeTimeSheetWithSickLeave.isEditableForEmployee());
    }

    @Test
    void testSettersAndGetters() {
        EmployeeTimeSheet ets = new EmployeeTimeSheet();

        ets.setYearMonthDay(date);
        ets.setFinOrdWork(financeOrderWork);
        ets.setHoursWorkedDayInFinSource(8.0);
        ets.setActivities("Programmēšana");
        ets.setOrdMission(orderMission);
        ets.setOrdVacation(orderVacation);
        ets.setOrdOther(orderOther);
        ets.setSickLeave("Slimības lapa");
        ets.setEditableForEmployee(true);
        ets.setRemarks("Piezīme");
        ets.setInputBy("Lietotājs");

        assertEquals(date, ets.getYearMonthDay());
        assertEquals(financeOrderWork, ets.getFinOrdWork());
        assertEquals(8.0, ets.getHoursWorkedDayInFinSource());
        assertEquals("Programmēšana", ets.getActivities());
        assertEquals(orderMission, ets.getOrdMission());
        assertEquals(orderVacation, ets.getOrdVacation());
        assertEquals(orderOther, ets.getOrdOther());
        assertEquals("Slimības lapa", ets.getSickLeave());
        assertTrue(ets.isEditableForEmployee());
        assertEquals("Piezīme", ets.getRemarks());
        assertEquals("Lietotājs", ets.getInputBy());
    }

    @Test
    void testEmployeeTimeSheetToString() {
        String expected = "EmployeeTimeSheet(idTimeSheet=0, yearMonthDay=2024-06-01, finOrdWork=null, hoursWorkedDayInFinSource=8.0, activities=Programmēšana, ordMission=null, ordVacation=null, ordOther=null, sickLeave=null, remarks=null, isEditableForEmployee=true, inputBy=null)";
        EmployeeTimeSheet ets = new EmployeeTimeSheet(LocalDate.of(2024, 6, 1), null, 8.0, "Programmēšana", true);
        assertEquals(expected, ets.toString());
    }

}