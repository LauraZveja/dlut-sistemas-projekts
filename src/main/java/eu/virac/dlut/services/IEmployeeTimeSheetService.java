package eu.virac.dlut.services;

import java.util.ArrayList;

import eu.virac.dlut.models.EmployeeTimeSheet;

public interface IEmployeeTimeSheetService {
	
	ArrayList<Double> selectHoursWorkedForEmployeeInProject(int year, int month, int employeeId, int projectId);

	EmployeeTimeSheet selectEmployeeTimeSheetByFinaceOrderWorkId(int finOrdWorkId);
	
	ArrayList<String> selectYearsFromTimeSheets();
	
	//Float selectHoursWorkedForEmployeeOnSpecificDay(String year, String month, String day, int employeeId, int projectId);
	ArrayList<Double> selectHoursWorkedForEmployeeOnSpecificDay(String year, String month, String day, int employeeId, int projectId);
	
	int selectDaysForEmployeeWithSickLeaveInProject(String year, String month, int employeeId, int projectId);
	
	int selectVacationDaysInProjectForOneEmployee(String year, String month, int employeeId, int projectId);

	ArrayList<Double> selectHoursWorkedForEmployeeInBaseFin(int year, int month, int idEmployee, int baseFinId);

	ArrayList<Double> selectHoursWorkedForEmployeeInAcademicWorkLoad(int year, int month, int idEmployee,
			int academicWorkLoadId);

	ArrayList<Double> selectHoursWorkedForEmployeeInIndirectVuas(int year, int month, int idEmployee,
			int indirectVuasId);
	
	boolean isDayRecuperation (int year, int month, int idEmployee, int finSourceId);
	
	void updateEntry(int year, int month, int day, int idEmployee, int finSourceId, double hoursDouble);
	
	void saveNewFromEditEmployeeTable(int year, int month, int day, int employeeId, int financeSourceId,
			double hoursDouble, String position);
	
	boolean isSickDayS(int day, int month, int year, int employeeId, int finSourceId);


	
}
