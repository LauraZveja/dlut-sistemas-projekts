package eu.virac.dlut.models.helpers;

import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeAndHourDTOTest {

    int idFromDb = 1;
    String name = "Jānis";
    String surname = "Bērziņš";
    String position = "Pētnieks";
    ArrayList<Double> hours = new ArrayList<>();
    Map<Integer, String> hoursInMonth = new HashMap<>();
    int sickDaysS = 2;
    int sickDaysSb = 1;
    int vacationAnnualDays = 10;
    int missionWorkDays = 5;
    int missionEducationDays = 3;
    int parentalLeave = 0;
    int unpaidVacation = 0;
    int vacationEducation = 2;
    int vacationExtra = 1;
    int vacationCreative = 0;
    int voluntaryWork = 1;
    int unjustifiedAbsence = 0;
    double payPerHour = 15.0;
    double vacationHours = 20.0;
    String financeSourceTitle = "Projekts A";
    String financeSourceCode = "PA123";
    String projectCharacter = "Pētniecība";
    int financeSourceId = 1001;

    EmployeeAndHourDTO employeeAndHourDTO = new EmployeeAndHourDTO(idFromDb, name, surname, position, hours,
            hoursInMonth, sickDaysS, sickDaysSb, vacationAnnualDays, missionWorkDays, missionEducationDays,
            parentalLeave, unpaidVacation, vacationEducation, vacationExtra, vacationCreative, voluntaryWork,
            unjustifiedAbsence, payPerHour, vacationHours, financeSourceTitle, financeSourceCode, projectCharacter, financeSourceId);

    @Test
    void testEmployeeAndHourDTOCreation() {
        assertEquals(idFromDb, employeeAndHourDTO.getIdFromDb());
        assertEquals(name, employeeAndHourDTO.getName());
        assertEquals(surname, employeeAndHourDTO.getSurname());
        assertEquals(position, employeeAndHourDTO.getPosition());
        assertEquals(hours, employeeAndHourDTO.getHours());
        assertEquals(hoursInMonth, employeeAndHourDTO.getHoursInMonth());
        assertEquals(sickDaysS, employeeAndHourDTO.getSickDaysS());
        assertEquals(sickDaysSb, employeeAndHourDTO.getSickDaysSb());
        assertEquals(vacationAnnualDays, employeeAndHourDTO.getVacationAnnualDays());
        assertEquals(missionWorkDays, employeeAndHourDTO.getMissionWorkDays());
        assertEquals(missionEducationDays, employeeAndHourDTO.getMissionEducationDays());
        assertEquals(parentalLeave, employeeAndHourDTO.getParentalLeave());
        assertEquals(unpaidVacation, employeeAndHourDTO.getUnpaidVacation());
        assertEquals(vacationEducation, employeeAndHourDTO.getVacationEducation());
        assertEquals(vacationExtra, employeeAndHourDTO.getVacationExtra());
        assertEquals(vacationCreative, employeeAndHourDTO.getVacationCreative());
        assertEquals(voluntaryWork, employeeAndHourDTO.getVoluntaryWork());
        assertEquals(unjustifiedAbsence, employeeAndHourDTO.getUnjustifiedAbsence());
        assertEquals(payPerHour, employeeAndHourDTO.getPayPerHour(), 0.001);
        assertEquals(vacationHours, employeeAndHourDTO.getVacationHours(), 0.001);
        assertEquals(financeSourceTitle, employeeAndHourDTO.getFinanceSourceTitle());
        assertEquals(financeSourceCode, employeeAndHourDTO.getFinanceSourceCode());
        assertEquals(projectCharacter, employeeAndHourDTO.getProjectCharacter());
        assertEquals(financeSourceId, employeeAndHourDTO.getFinanceSourceId());
    }

    @Test
    void testSumHours() {
        hours.add(8.0);
        hours.add(7.5);
        hours.add(8.0);
        assertEquals(23.5, employeeAndHourDTO.sumHours(), 0.001);
    }

    @Test
    void testDaysWorkedAccordingToHours() {
        hours.add(8.0);
        hours.add(0.0);
        hours.add(8.0);
        assertEquals(2, employeeAndHourDTO.daysWorkedAccordingToHours());
    }

    @Test
    void testCalculateFullTimeEquivalent() {
        hours.add(8.0);
        hours.add(7.5);
        hours.add(8.0);
        double hoursInMonth = 160.0;
        double expectedFTE = Precision.round((23.5 + vacationHours) / hoursInMonth, 3);
        assertEquals(expectedFTE, employeeAndHourDTO.calculateFullTimeEquivalent(hoursInMonth), 0.001);
    }

    @Test
    void testSettersAndGetters() {
        String newName = "Anna";
        String newSurname = "Kalniņa";
        String newPosition = "Asistente";
        ArrayList<Double> newHours = new ArrayList<>();
        Map<Integer, String> newHoursInMonth = new HashMap<>();
        int newSickDaysS = 3;
        int newSickDaysSb = 2;
        int newVacationAnnualDays = 12;
        int newMissionWorkDays = 6;
        int newMissionEducationDays = 4;
        int newParentalLeave = 1;
        int newUnpaidVacation = 1;
        int newVacationEducation = 3;
        int newVacationExtra = 2;
        int newVacationCreative = 1;
        int newVoluntaryWork = 2;
        int newUnjustifiedAbsence = 1;
        double newPayPerHour = 20.0;
        double newVacationHours = 25.0;
        String newFinanceSourceTitle = "Projekts B";
        String newFinanceSourceCode = "PB123";
        String newProjectCharacter = "Izglītība";
        int newFinanceSourceId = 1002;

        employeeAndHourDTO.setName(newName);
        employeeAndHourDTO.setSurname(newSurname);
        employeeAndHourDTO.setPosition(newPosition);
        employeeAndHourDTO.setHours(newHours);
        employeeAndHourDTO.setHoursInMonth(newHoursInMonth);
        employeeAndHourDTO.setSickDaysS(newSickDaysS);
        employeeAndHourDTO.setSickDaysSb(newSickDaysSb);
        employeeAndHourDTO.setVacationAnnualDays(newVacationAnnualDays);
        employeeAndHourDTO.setMissionWorkDays(newMissionWorkDays);
        employeeAndHourDTO.setMissionEducationDays(newMissionEducationDays);
        employeeAndHourDTO.setParentalLeave(newParentalLeave);
        employeeAndHourDTO.setUnpaidVacation(newUnpaidVacation);
        employeeAndHourDTO.setVacationEducation(newVacationEducation);
        employeeAndHourDTO.setVacationExtra(newVacationExtra);
        employeeAndHourDTO.setVacationCreative(newVacationCreative);
        employeeAndHourDTO.setVoluntaryWork(newVoluntaryWork);
        employeeAndHourDTO.setUnjustifiedAbsence(newUnjustifiedAbsence);
        employeeAndHourDTO.setPayPerHour(newPayPerHour);
        employeeAndHourDTO.setVacationHours(newVacationHours);
        employeeAndHourDTO.setFinanceSourceTitle(newFinanceSourceTitle);
        employeeAndHourDTO.setFinanceSourceCode(newFinanceSourceCode);
        employeeAndHourDTO.setProjectCharacter(newProjectCharacter);
        employeeAndHourDTO.setFinanceSourceId(newFinanceSourceId);

        assertEquals(newName, employeeAndHourDTO.getName());
        assertEquals(newSurname, employeeAndHourDTO.getSurname());
        assertEquals(newPosition, employeeAndHourDTO.getPosition());
        assertEquals(newHours, employeeAndHourDTO.getHours());
        assertEquals(newHoursInMonth, employeeAndHourDTO.getHoursInMonth());
        assertEquals(newSickDaysS, employeeAndHourDTO.getSickDaysS());
        assertEquals(newSickDaysSb, employeeAndHourDTO.getSickDaysSb());
        assertEquals(newVacationAnnualDays, employeeAndHourDTO.getVacationAnnualDays());
        assertEquals(newMissionWorkDays, employeeAndHourDTO.getMissionWorkDays());
        assertEquals(newMissionEducationDays, employeeAndHourDTO.getMissionEducationDays());
        assertEquals(newParentalLeave, employeeAndHourDTO.getParentalLeave());
        assertEquals(newUnpaidVacation, employeeAndHourDTO.getUnpaidVacation());
        assertEquals(newVacationEducation, employeeAndHourDTO.getVacationEducation());
        assertEquals(newVacationExtra, employeeAndHourDTO.getVacationExtra());
        assertEquals(newVacationCreative, employeeAndHourDTO.getVacationCreative());
        assertEquals(newVoluntaryWork, employeeAndHourDTO.getVoluntaryWork());
        assertEquals(newUnjustifiedAbsence, employeeAndHourDTO.getUnjustifiedAbsence());
        assertEquals(newPayPerHour, employeeAndHourDTO.getPayPerHour(), 0.001);
        assertEquals(newVacationHours, employeeAndHourDTO.getVacationHours(), 0.001);
        assertEquals(newFinanceSourceTitle, employeeAndHourDTO.getFinanceSourceTitle());
        assertEquals(newFinanceSourceCode, employeeAndHourDTO.getFinanceSourceCode());
        assertEquals(newProjectCharacter, employeeAndHourDTO.getProjectCharacter());
        assertEquals(newFinanceSourceId, employeeAndHourDTO.getFinanceSourceId());
    }

}