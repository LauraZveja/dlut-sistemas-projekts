package eu.virac.dlut.models.helpers;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.math3.util.Precision;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class EmployeeAndHourDTO {
	
	private int idFromDb;//employeeId
	private String name;
	private String surname;
	private String position;
	private ArrayList<Double> hours;
	private Map<Integer, String> hoursInMonth;
	private int sickDaysS; //S
	private int sickDaysSb; //SB
	private int vacationAnnualDays; //AI
	private int missionWorkDays; //KD
	private int missionEducationDays; //KM
	private int parentalLeave; //AA
	private int unpaidVacation; //AB
	private int vacationEducation; //AM
	private int vacationExtra;//AP
	private int vacationCreative; //AR
	private int voluntaryWork; //BD
	private int unjustifiedAbsence; //N
	private double payPerHour;
//calculated and input from outside, after that save and can get from DB
	private double vacationHours;
	//
	private String financeSourceTitle; //projectTitle;
	private String financeSourceCode;
	private int financeSourceId;
	//if project, then need
	private String projectCharacter;
	
	private double sumHours;
	private double ple;
	
	public EmployeeAndHourDTO(int idFromDb, String name, String surname, String position, ArrayList<Double> hours,
			Map<Integer, String> hoursInMonth, int sickDaysS, int sickDaysSb, int vacationAnnualDays,
			int missionWorkDays, int missionEducationDays, int parentalLeave, int unpaidVacation, int vacationEducation,
			int vacationExtra, int vacationCreative, int voluntaryWork, int unjustifiedAbsence, double payPerHour, double vacationHours) {
		this.idFromDb = idFromDb;
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.hours = hours;
		this.hoursInMonth = hoursInMonth;
		this.sickDaysS = sickDaysS;
		this.sickDaysSb = sickDaysSb;
		this.vacationAnnualDays = vacationAnnualDays;
		this.missionWorkDays = missionWorkDays;
		this.missionEducationDays = missionEducationDays;
		this.parentalLeave = parentalLeave;
		this.unpaidVacation = unpaidVacation;
		this.vacationEducation = vacationEducation;
		this.vacationExtra = vacationExtra;
		this.vacationCreative = vacationCreative;
		this.voluntaryWork = voluntaryWork;
		this.unjustifiedAbsence = unjustifiedAbsence;
		this.payPerHour = payPerHour;
		this.vacationHours = vacationHours;
		this.sumHours = sumHours(); //
		this.ple = calculateFullTimeEquivalent(vacationHours);//
	}
	
	public EmployeeAndHourDTO(int idFromDb, String name, String surname, String position, ArrayList<Double> hours,
			Map<Integer, String> hoursInMonth, int sickDaysS, int sickDaysSb, int vacationAnnualDays,
			int missionWorkDays, int missionEducationDays, int parentalLeave, int unpaidVacation, int vacationEducation,
			int vacationExtra, int vacationCreative, int voluntaryWork, int unjustifiedAbsence, double payPerHour, double vacationHours, String financeSourceTitle,
			String financeSourceCode, String projectCharacter, int financeSourceId) {
		this.idFromDb = idFromDb;
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.hours = hours;
		this.hoursInMonth = hoursInMonth;
		this.sickDaysS = sickDaysS;
		this.sickDaysSb = sickDaysSb;
		this.vacationAnnualDays = vacationAnnualDays;
		this.missionWorkDays = missionWorkDays;
		this.missionEducationDays = missionEducationDays;
		this.parentalLeave = parentalLeave;
		this.unpaidVacation = unpaidVacation;
		this.vacationEducation = vacationEducation;
		this.vacationExtra = vacationExtra;
		this.vacationCreative = vacationCreative;
		this.voluntaryWork = voluntaryWork;
		this.unjustifiedAbsence = unjustifiedAbsence;
		this.payPerHour = payPerHour;
		this.vacationHours = vacationHours;
		this.financeSourceTitle = financeSourceTitle;
		this.financeSourceCode = financeSourceCode;
		this.projectCharacter = projectCharacter;
		this.financeSourceId = financeSourceId;
	}
	
	public EmployeeAndHourDTO(int idFromDb, String name, String surname, String position, ArrayList<Double> hours,
			Map<Integer, String> hoursInMonth, int sickDaysS, int sickDaysSb, int vacationAnnualDays,
			int missionWorkDays, int missionEducationDays, int parentalLeave, int unpaidVacation, int vacationEducation,
			int vacationExtra, int vacationCreative, int voluntaryWork, int unjustifiedAbsence, double payPerHour, double vacationHours, String financeSourceTitle, String financeSourceCode) {
		this.idFromDb = idFromDb;
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.hours = hours;
		this.hoursInMonth = hoursInMonth;
		this.sickDaysS = sickDaysS;
		this.sickDaysSb = sickDaysSb;
		this.vacationAnnualDays = vacationAnnualDays;
		this.missionWorkDays = missionWorkDays;
		this.missionEducationDays = missionEducationDays;
		this.parentalLeave = parentalLeave;
		this.unpaidVacation = unpaidVacation;
		this.vacationEducation = vacationEducation;
		this.vacationExtra = vacationExtra;
		this.vacationCreative = vacationCreative;
		this.voluntaryWork = voluntaryWork;
		this.unjustifiedAbsence = unjustifiedAbsence;
		this.payPerHour = payPerHour;
		this.vacationHours = vacationHours;
		this.financeSourceTitle = financeSourceTitle;
		this.financeSourceCode = financeSourceCode;
	}
	
	
	
	
	public double sumHours() {

			double sum = 0;
			for (int i = 0; i < hours.size(); i++) {
				sum += hours.get(i);
			}
			return sum;
		}
	
	public int daysWorkedAccordingToHours() {
		int count = hours.size();
		for (double temp : hours) {
			if(temp == 0.0)
				count--;
		}
		return count;
	}

//paarnest uz full time equivalent?
	public double calculateFullTimeEquivalent(double hoursInMonth) {
		double hoursWorked = sumHours();
		double res = (hoursWorked+vacationHours)/hoursInMonth;
		double roundedRes = Precision.round(res, 3);
		return roundedRes;
		
	}


	
}
