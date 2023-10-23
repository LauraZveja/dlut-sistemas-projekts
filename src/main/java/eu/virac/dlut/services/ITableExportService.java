package eu.virac.dlut.services;

import java.util.ArrayList;
import java.util.Map;

import eu.virac.dlut.models.AcademicWorkLoad;
import eu.virac.dlut.models.BaseFin;
import eu.virac.dlut.models.Employee;
import eu.virac.dlut.models.FinanceSource;
import eu.virac.dlut.models.IndirectVUAS;
import eu.virac.dlut.models.Project;
import eu.virac.dlut.models.helpers.EmployeeAndHourDTO;

public interface ITableExportService {
	
	ArrayList<FinanceSource> selectAllFinSources();

	ArrayList<EmployeeAndHourDTO> selectEmployeesAndHoursForProjectForYearMonth(int projectId, int year, int month);

//	int parseStringToInteger(String valueString);
//	
	int determineDaysInMonth(int year, int month);
//	
//	Map<Integer, String> putDaysOfMonthAndCorrespondingHoursForEmployeeInProject(String year, String month,
//			int employeeId, int projectId);
//	
//
//	
	//ArrayList<EmployeeAndHourDTO> selectOneEmployeeAndAllProjects(Employee employee, int year, int month);
	
	ArrayList<EmployeeAndHourDTO> selectEmployeesAndHoursForBaseFinForYearMonth(int baseFinId, int year,
			int month);
	
	ArrayList<EmployeeAndHourDTO> selectEmployeesAndHoursForAcademicWorkLoadForYearMonth(int academicWorkLoadId, int year,
			int month);

	ArrayList<EmployeeAndHourDTO> selectEmployeesAndHoursForIndirectVuasForYearMonth(int indirectVuasId, int year,
			int month);
	
	//tomēr salikti kopā vienā sarkastā ar pārējienm finansējuma avotiem
	//ArrayList<Project> selectAllProjectsOneEmployeeInYearAndMonth(int year, int month, int employeeId);
	//ArrayList<EmployeeAndHourDTO> selectDataEmployeeInProjectsInMonthAndYear(int year, int month, int employeeId) throws Exception;
	
	ArrayList<EmployeeAndHourDTO> selectNecessaryDataForEmployeeInAllOtherFinanceSourcesInOneMonth(int year, int month, int employeeId) throws Exception;
	 ArrayList<FinanceSource> selectAllOtherFinanceSourcesForOneEmployeeInSpecificYearAndMonth(int year, int month, int employeeId);
	 EmployeeAndHourDTO makeDTOForEmployeeInProjectsInSpecificMonth(int year, int month, int employeeId, int projectId) throws Exception;
	 EmployeeAndHourDTO makeDTOInBaseFinForSpecificMonthForEmployee(int year, int month, int employeeId, int baseFinId) throws Exception;
	 EmployeeAndHourDTO makeDTOForAcademicWorkLoadForEmpolyeeForDate(int year, int month, int employeeId, int academicWorkLoadId) throws Exception;
	 EmployeeAndHourDTO makeDTOForEmpolyeeAndIndirectVuasForMonthAndYear(int year, int month, int employeeId, int indirectVuasId) throws Exception;
	
//	//
	Map<Integer, String> uniteDaysOfMonthAndCorrespondingEmployeeHours(int year, int month, int employeeId, int finSourceId);
	
	int selectSickDaysSCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId, int finSourceId);
	
	int selectSickDaysSbCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId, int finSourceId);
	
	int selectMissionWorkKDCountByDateForEmployeeInFinSource(int year, int month, int employeeId, int finSourceId);
	
	int selectMissionEducationKMCountByDateForEmployeeInFinSource(int year, int month, int employeeId, int finSourceId);
	
	int selectAnnualVacationDaysAICountByDateAndEmployeeAndFinSource(int year, int month, int employeeId, int finSourceId);
	
	int selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId);
	
	int  selectUnpaidVacationDaysABCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId);
	
	int  selectVacationEducationDaysAMCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId);
	
	int  selectVacationExtraDaysAPCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId);
	
	int  selectVacationCreativeDaysARCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId);
	
	int  selectVoluntaryWorkDaysBDCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId);
	
	int selectUnjustifiedAbsenceNCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId);
	
	
	double selectVacationHoursInFinSourceDateEmployee(int year, int month,
			int employeeId, int finSourceId);
	
	
	boolean areSickDaysSZero(ArrayList<EmployeeAndHourDTO> selectedResults);
	
	boolean areSickSbZero(ArrayList<EmployeeAndHourDTO> selectedResults);
	
	boolean areAnnualVacationAIZero(ArrayList<EmployeeAndHourDTO> selectedResults);
	
	boolean areUnpaidVacationZero(ArrayList<EmployeeAndHourDTO> selectedResults);
	
	boolean areParentalLeaveDaysZero(ArrayList<EmployeeAndHourDTO> selectedResults);
	
	boolean areVacationEducationDaysForAllZero(ArrayList<EmployeeAndHourDTO> selectedResults);
	
	boolean areExtraVacationDaysAllZero(ArrayList<EmployeeAndHourDTO> selectedResults);
	
	boolean areCreativeVacationDaysForAllZero(ArrayList<EmployeeAndHourDTO> selectedResults);
	
	boolean areVolunatryWorkAllDaysZero(ArrayList<EmployeeAndHourDTO> selectedResults);
	
	boolean areWorkMissionDaysZero(ArrayList<EmployeeAndHourDTO> selectedResults);
	
	boolean areDaysOfMissionEducationZero(ArrayList<EmployeeAndHourDTO> selectedResults);
	
	boolean areUnjustifiedAbsenceDaysZero(ArrayList<EmployeeAndHourDTO> selectedResults);
	
	
	public Map<Integer, Double> allHoursOneEmployeeOnDate(int year, int month, int employeeId);
	
//	int countAnnulVacationDaysInAllFinSources(ArrayList<EmployeeAndHourDTO> resList);
	
	double sumAllHoursWorked(Map<Integer, Double> resMap);
	//int sumAllDaysWorked(ArrayList<EmployeeAndHourDTO> resList);
}

