package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderMissionTest {

    String orderNo = "12345";
    LocalDate date = LocalDate.of(2024, 6, 1);
    LocalDate startDate = LocalDate.of(2024, 6, 15);
    LocalDate endDate = LocalDate.of(2024, 6, 30);
    short transferedDays = 5;
    Project project = new Project();
    Employee employee = new Employee();
    EmployeeTimeSheet employeeTimeSheet = new EmployeeTimeSheet();

    OrderMission orderMission = new OrderMission(orderNo, date, project, startDate, endDate, employee, transferedDays);

    @Test
    void testOrderMissionCreation() {
        assertEquals(orderNo, orderMission.getOrderNo());
        assertEquals(date, orderMission.getDate());
        assertEquals(startDate, orderMission.getStartDate());
        assertEquals(endDate, orderMission.getEndDate());
        assertEquals(employee, orderMission.getEmployee());
        assertEquals(transferedDays, orderMission.getTransferedDays());
        assertEquals(project, orderMission.getProject());
    }

    @Test
    void testSetTransferedDays() {
        short newTransferedDays = 10;
        orderMission.setTransferedDays(newTransferedDays);
        assertEquals(newTransferedDays, orderMission.getTransferedDays());
    }

    @Test
    void testSetProject() {
        Project newProject = new Project();
        orderMission.setProject(newProject);
        assertEquals(newProject, orderMission.getProject());
    }

    @Test
    void testEmplTimeSheetsInitialization() {
        OrderMission om = new OrderMission();
        om.setEmplTimeSheets(new ArrayList<>());
        assertNotNull(om.getEmplTimeSheets());
        assertTrue(om.getEmplTimeSheets().isEmpty());
    }

    @Test
    void testAddEmployeeTimeSheet() {
        orderMission.setEmplTimeSheets(new ArrayList<>());
        orderMission.addEmployeeTimeSheetToOrderMission(employeeTimeSheet);
        assertTrue(orderMission.getEmplTimeSheets().contains(employeeTimeSheet));
    }

    @Test
    void testRemoveEmployeeTimeSheet() {
        orderMission.setEmplTimeSheets(new ArrayList<>());
        orderMission.addEmployeeTimeSheetToOrderMission(employeeTimeSheet);
        orderMission.removeEmployeeTimeSheetFromOrderMission(employeeTimeSheet);
        assertFalse(orderMission.getEmplTimeSheets().contains(employeeTimeSheet));
    }

    @Test
    void testOrderMissionToString() {
        String expected = "OrderMission(transferedDays=5, project=Project(accountingAcronym=null, contractNo=null, projChar=null, projCat=null, projLenghtMonths=0, start=null, end=null, isActive=false))";
        assertEquals(expected, orderMission.toString());
    }

}