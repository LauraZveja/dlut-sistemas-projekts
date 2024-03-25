package eu.virac.dlut.models.helpers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeSummaryResponseDTO {

    private EmployeeDTO employee;
    private ArrayList<EmployeeAndHourDTO> employeeHoursDetails;
    private double workHoursThisMonth;
    private Map<Integer, String> monthNumberAndName;
    private boolean areSDaysAllZero;
    private boolean areSbAllZero;
    private boolean countAnnualVacAI;
    private boolean unpaidVacationCountAB;
    private boolean parentalLeaveAA;
    private boolean educationVacationAMZero;
    private boolean areExtraVacationAPZero;
    private boolean areCreativeVacationARDaysZero;
    private boolean areVoluntaryWorkBDAllZero;
    private boolean areMissionWorkAllDaysZero;
    private boolean areEducationWorkDaysAllZero;
    private double allHoursWorked;

    public EmployeeSummaryResponseDTO(EmployeeDTO employee, ArrayList<EmployeeAndHourDTO> employeeHoursDetails, double workHoursThisMonth, Map<Integer, String> monthNumberAndName, boolean areSDaysAllZero, boolean areSbAllZero, boolean countAnnualVacAI, boolean unpaidVacationCountAB, boolean parentalLeaveAA, boolean educationVacationAMZero, boolean areExtraVacationAPZero, boolean areCreativeVacationARDaysZero, boolean areVoluntaryWorkBDAllZero, boolean areMissionWorkAllDaysZero, boolean areEducationWorkDaysAllZero, double allHoursWorked) {
        this.employee = employee;
        this.employeeHoursDetails = employeeHoursDetails;
        this.workHoursThisMonth = workHoursThisMonth;
        this.monthNumberAndName = monthNumberAndName;
        this.areSDaysAllZero = areSDaysAllZero;
        this.areSbAllZero = areSbAllZero;
        this.countAnnualVacAI = countAnnualVacAI;
        this.unpaidVacationCountAB = unpaidVacationCountAB;
        this.parentalLeaveAA = parentalLeaveAA;
        this.educationVacationAMZero = educationVacationAMZero;
        this.areExtraVacationAPZero = areExtraVacationAPZero;
        this.areCreativeVacationARDaysZero = areCreativeVacationARDaysZero;
        this.areVoluntaryWorkBDAllZero = areVoluntaryWorkBDAllZero;
        this.areMissionWorkAllDaysZero = areMissionWorkAllDaysZero;
        this.areEducationWorkDaysAllZero = areEducationWorkDaysAllZero;
        this.allHoursWorked = allHoursWorked;
    }
}
