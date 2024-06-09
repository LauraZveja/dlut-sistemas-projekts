package eu.virac.dlut.models.helpers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeSummaryResponseDTOTest {

    EmployeeDTO employee = new EmployeeDTO("Jānis", "Bērziņš");
    ArrayList<EmployeeAndHourDTO> employeeHoursDetails = new ArrayList<>();
    double workHoursThisMonth = 160.0;
    Map<Integer, String> monthNumberAndName = new HashMap<>() {{
        put(1, "Janvāris");
        put(2, "Februāris");
    }};
    boolean areSDaysAllZero = true;
    boolean areSbAllZero = true;
    boolean countAnnualVacAI = true;
    boolean unpaidVacationCountAB = false;
    boolean parentalLeaveAA = false;
    boolean educationVacationAMZero = true;
    boolean areExtraVacationAPZero = true;
    boolean areCreativeVacationARDaysZero = true;
    boolean areVoluntaryWorkBDAllZero = true;
    boolean areMissionWorkAllDaysZero = true;
    boolean areEducationWorkDaysAllZero = true;
    double allHoursWorked = 2000.0;

    EmployeeSummaryResponseDTO summaryResponseDefault = new EmployeeSummaryResponseDTO();
    EmployeeSummaryResponseDTO summaryResponse = new EmployeeSummaryResponseDTO(
            employee, employeeHoursDetails, workHoursThisMonth, monthNumberAndName,
            areSDaysAllZero, areSbAllZero, countAnnualVacAI, unpaidVacationCountAB,
            parentalLeaveAA, educationVacationAMZero, areExtraVacationAPZero,
            areCreativeVacationARDaysZero, areVoluntaryWorkBDAllZero,
            areMissionWorkAllDaysZero, areEducationWorkDaysAllZero, allHoursWorked
    );

    @Test
    void testDefaultConstructor() {
        assertNotNull(summaryResponseDefault);
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals(employee, summaryResponse.getEmployee());
        assertEquals(employeeHoursDetails, summaryResponse.getEmployeeHoursDetails());
        assertEquals(workHoursThisMonth, summaryResponse.getWorkHoursThisMonth());
        assertEquals(monthNumberAndName, summaryResponse.getMonthNumberAndName());
        assertEquals(areSDaysAllZero, summaryResponse.isAreSDaysAllZero());
        assertEquals(areSbAllZero, summaryResponse.isAreSbAllZero());
        assertEquals(countAnnualVacAI, summaryResponse.isCountAnnualVacAI());
        assertEquals(unpaidVacationCountAB, summaryResponse.isUnpaidVacationCountAB());
        assertEquals(parentalLeaveAA, summaryResponse.isParentalLeaveAA());
        assertEquals(educationVacationAMZero, summaryResponse.isEducationVacationAMZero());
        assertEquals(areExtraVacationAPZero, summaryResponse.isAreExtraVacationAPZero());
        assertEquals(areCreativeVacationARDaysZero, summaryResponse.isAreCreativeVacationARDaysZero());
        assertEquals(areVoluntaryWorkBDAllZero, summaryResponse.isAreVoluntaryWorkBDAllZero());
        assertEquals(areMissionWorkAllDaysZero, summaryResponse.isAreMissionWorkAllDaysZero());
        assertEquals(areEducationWorkDaysAllZero, summaryResponse.isAreEducationWorkDaysAllZero());
        assertEquals(allHoursWorked, summaryResponse.getAllHoursWorked());
    }

    @Test
    void testSetAndGetMethods() {
        summaryResponseDefault.setEmployee(employee);
        summaryResponseDefault.setEmployeeHoursDetails(employeeHoursDetails);
        summaryResponseDefault.setWorkHoursThisMonth(workHoursThisMonth);
        summaryResponseDefault.setMonthNumberAndName(monthNumberAndName);
        summaryResponseDefault.setAreSDaysAllZero(areSDaysAllZero);
        summaryResponseDefault.setAreSbAllZero(areSbAllZero);
        summaryResponseDefault.setCountAnnualVacAI(countAnnualVacAI);
        summaryResponseDefault.setUnpaidVacationCountAB(unpaidVacationCountAB);
        summaryResponseDefault.setParentalLeaveAA(parentalLeaveAA);
        summaryResponseDefault.setEducationVacationAMZero(educationVacationAMZero);
        summaryResponseDefault.setAreExtraVacationAPZero(areExtraVacationAPZero);
        summaryResponseDefault.setAreCreativeVacationARDaysZero(areCreativeVacationARDaysZero);
        summaryResponseDefault.setAreVoluntaryWorkBDAllZero(areVoluntaryWorkBDAllZero);
        summaryResponseDefault.setAreMissionWorkAllDaysZero(areMissionWorkAllDaysZero);
        summaryResponseDefault.setAreEducationWorkDaysAllZero(areEducationWorkDaysAllZero);
        summaryResponseDefault.setAllHoursWorked(allHoursWorked);

        assertEquals(employee, summaryResponseDefault.getEmployee());
        assertEquals(employeeHoursDetails, summaryResponseDefault.getEmployeeHoursDetails());
        assertEquals(workHoursThisMonth, summaryResponseDefault.getWorkHoursThisMonth());
        assertEquals(monthNumberAndName, summaryResponseDefault.getMonthNumberAndName());
        assertEquals(areSDaysAllZero, summaryResponseDefault.isAreSDaysAllZero());
        assertEquals(areSbAllZero, summaryResponseDefault.isAreSbAllZero());
        assertEquals(countAnnualVacAI, summaryResponseDefault.isCountAnnualVacAI());
        assertEquals(unpaidVacationCountAB, summaryResponseDefault.isUnpaidVacationCountAB());
        assertEquals(parentalLeaveAA, summaryResponseDefault.isParentalLeaveAA());
        assertEquals(educationVacationAMZero, summaryResponseDefault.isEducationVacationAMZero());
        assertEquals(areExtraVacationAPZero, summaryResponseDefault.isAreExtraVacationAPZero());
        assertEquals(areCreativeVacationARDaysZero, summaryResponseDefault.isAreCreativeVacationARDaysZero());
        assertEquals(areVoluntaryWorkBDAllZero, summaryResponseDefault.isAreVoluntaryWorkBDAllZero());
        assertEquals(areMissionWorkAllDaysZero, summaryResponseDefault.isAreMissionWorkAllDaysZero());
        assertEquals(areEducationWorkDaysAllZero, summaryResponseDefault.isAreEducationWorkDaysAllZero());
        assertEquals(allHoursWorked, summaryResponseDefault.getAllHoursWorked());
    }


}