package eu.virac.dlut.repos;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.EmployeeTimeSheet;
import eu.virac.dlut.models.Project;

public interface IEmployeeTimeSheetRepo extends CrudRepository<EmployeeTimeSheet, Integer>{

//	@Query(value = "SELECT ets.hours_worked FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
//			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
//			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
//			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND emp.id_employee = ?3 ",
//			nativeQuery = true)
//	ArrayList<Float> getHoursWorkedForEmployee(String year, String month, int idEmployee);
	
	@Query(value = "SELECT ets.hours_worked FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN project p ON fow.id_project=p.id_fin_source "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) "
			+ "AND emp.id_employee = ?3 AND p.id_fin_source = ?4",
			nativeQuery = true)
	ArrayList<Double> getHoursWorkedForEmployeeInProject(int year, int month, int idEmployee, int idProject);

	@Query(value = "SELECT DISTINCT (EXTRACT(YEAR FROM year_month_day)) FROM employee_time_sheet",
			nativeQuery = true)
	ArrayList<String> getDistinctYearsFromTimeSheet();

	EmployeeTimeSheet findByFinOrdWorkIdFinOrdWork(int finOrdWorkId);

//	@Query(value = "SELECT ets.hours_worked FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
//			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
//			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
//			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 AND EXTRACT(DAY FROM ets.year_month_day) = ?3 ) AND emp.id_employee = ?4 AND fow.id_project = ?5 ",
//			nativeQuery = true)
//	Float getHoursWorkedOnSpecificDayForEmployee(String year, String month, String day, int employeeId, int projectId);

	@Query(value = "SELECT ets.hours_worked FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 AND EXTRACT(DAY FROM ets.year_month_day) = ?3 ) AND emp.id_employee = ?4 AND fow.id_project = ?5 ",
			nativeQuery = true)
	ArrayList<Double> getHoursWorkedOnSpecificDayForEmployeeProject(String year, String month, String day, int employeeId, int projectId);
	
	@Query(value = "SELECT COUNT(ets.sick_leave) FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND emp.id_employee = ?3 AND fow.id_project = ?4 AND ets.sick_leave IS NOT NULL",
			nativeQuery = true)
	int getSickDaysByDateAndEmployeeAndProject(String year, String month, int employeeId, int projectId);

	
	@Query(value = "SELECT COUNT(ets.id_ord_vacation) FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND emp.id_employee = ?3 AND fow.id_project = ?4 AND ets.id_ord_vacation IS NOT NULL",
			nativeQuery = true)
	int getVacationTimeBySpecificDateAndEmployeeAndProject(String year, String month, int employeeId, int projectId);

	
	@Query(value = "SELECT ets.hours_worked FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN base_fin b ON fow.id_project=b.id_fin_source "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) "
			+ "AND emp.id_employee = ?3 AND b.id_fin_source = ?4",
			nativeQuery = true)
	ArrayList<Double> getHoursWorkedForEmployeeBaseFin(String year, String month, int idEmployee, int baseFinId);


	@Query(value = "SELECT ets.hours_worked FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 AND EXTRACT(DAY FROM ets.year_month_day) = ?3 ) AND emp.id_employee = ?4 "
			+ "AND (fow.id_base_fin = ?5 OR fow.id_project = ?5 OR fow.id_ac_work_load = ?5 OR fow.id_indirectvuas = ?5 )",
			nativeQuery = true)
	ArrayList<Double> getEmployeeHoursWorkedOnSpecificDay(int year, int month, int day, int employeeId, int finSourceId);
	
	//sick days
	@Query(value = "SELECT COUNT(ets.sick_leave) FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND emp.id_employee = ?3 AND fow.id_project = ?4 AND ets.sick_leave IS NOT NULL",
			nativeQuery = true)
	int getSickDaysSByDateAndEmployeeAndFinSource(String year, String month, int employeeId, int finSourceId);

	@Query(value = "SELECT * FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND emp.id_employee = ?3 "
			+ "AND (fow.id_project = ?4 OR fow.id_base_fin = ?4 OR fow.id_ac_work_load = ?4 OR id_indirectvuas = ?4 ) AND ets.sick_leave = 'S' ",
			nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndSickLeaveSForEmployeeFinSource(int year, int month, int employeeId, int finSourceId);
	
	@Query(value = "SELECT * FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND emp.id_employee = ?3 "
			+ "AND (fow.id_project = ?4 OR fow.id_base_fin = ?4 OR fow.id_ac_work_load = ?4 OR id_indirectvuas = ?4 ) AND ets.sick_leave = 'Sb' ",
			nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndSickLeaveSbForEmployeeFinSource(int year, int month, int employeeId, int finSourceId);
	

	@Query(value = "SELECT * FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND emp.id_employee = ?3 "
			+ "AND (fow.id_project = ?4 OR fow.id_base_fin = ?4 OR fow.id_ac_work_load = ?4 OR id_indirectvuas = ?4 ) AND ets.id_ord_mission IS NOT NULL",
			nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndMissionEmployeeFinSource(int year, int month, int employeeId,
			int finSourceId);

	//lai atlasītu tās dienas vienā mēnesī un gadā, kad ir ikgadējais atvaļinājums
	@Query(value = "SELECT * FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND ets.id_ord_vacation IS NOT NULL",
			nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndOrderVacationForEmployeeFinSource(int year, int month);
	
	
	@Query(value = "SELECT * FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND emp.id_employee = ?3 "
			+ "AND (fow.id_project = ?4 OR fow.id_base_fin = ?4 OR fow.id_ac_work_load = ?4 OR id_indirectvuas = ?4 )",
			nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndForEmployeeInFinSource(String year, String month,
			int employeeId, int finSourceId);

	@Query(value = "SELECT * FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND ets.id_ord_other IS NOT NULL",
			nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndOrderOther(int year, int month);

	@Query(value = "SELECT ets.hours_worked FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN base_fin b ON fow.id_base_fin=b.id_fin_source "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) "
			+ "AND emp.id_employee = ?3 AND b.id_fin_source = ?4 ",
			nativeQuery = true)
	ArrayList<Double> getHoursWorkedForEmployeeInBaseFin(int year, int month, int idEmployee, int baseFinId);

	@Query(value = "SELECT ets.hours_worked FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN academic_work_load awl ON fow.id_ac_work_load=awl.id_fin_source "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) "
			+ "AND emp.id_employee = ?3 AND awl.id_fin_source = ?4 ",
			nativeQuery = true)
	ArrayList<Double> getHoursWorkedForEmployeeInacademicWorkLoad(int year, int month, int idEmployee,
			int academicWorkLoadId);

	@Query(value = "SELECT ets.hours_worked FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN indirect_vuas i ON fow.id_indirectvuas=i.id_fin_source "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) "
			+ "AND emp.id_employee = ?3 AND i.id_fin_source = ?4 ",
			nativeQuery = true)
	ArrayList<Double> getHoursWorkedForEmployeeInIndirectVuas(int year, int month, int idEmployee, int indirectVuasId);

	//unjustified Absence
	@Query(value = "SELECT * FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND emp.id_employee = ?3 "
			+ "AND (fow.id_project = ?4 OR fow.id_base_fin = ?4 OR fow.id_ac_work_load = ?4 OR id_indirectvuas = ?4 ) AND ets.remarks = 'N'",
			nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndUnjustifAbsenceForEmployeeInFinSource(int year, int month,
			int employeeId, int finSourceId);

	@Query(value = "SELECT ets.remarks FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 ) AND emp.id_employee = ?3 "
			+ "AND (fow.id_project = ?4 OR fow.id_base_fin = ?4 OR fow.id_ac_work_load = ?4 OR id_indirectvuas = ?4 ) AND ets.remarks = 'BR' ",
			nativeQuery = true)
	EmployeeTimeSheet findByDateAndRemarkRecuperation(int year, int month, int idEmployee, int finSourceId);
	


	@Query(value = "SELECT ets.hours_worked FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 AND EXTRACT(DAY FROM ets.year_month_day) = ?3 ) AND emp.id_employee = ?4 ",
			nativeQuery = true)
	ArrayList<Double> getWorkedHoursOneEmployeeOnSpecificDate(int year, int month, int day, int employeeId);
	
	@Query(value = "SELECT * FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(YEAR FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 AND EXTRACT(DAY FROM ets.year_month_day) = ?3 ) AND emp.id_employee = ?4 "
			+ "AND (fow.id_project = ?5 OR fow.id_base_fin = ?5 OR fow.id_ac_work_load = ?5 OR id_indirectvuas = ?5 ) ",
			nativeQuery = true)
	EmployeeTimeSheet getAllByYearAndMonthAndDayForEmployeeInFinSource(int year, int month, int day,
			int employeeId, int finSourceId);
	
	
	@Query(value = "SELECT * FROM employee_time_sheet ets JOIN finance_order_work fow ON ets.id_fin_ord_work=fow.id_fin_ord_work "
			+ "JOIN order_work ow ON fow.id_ord_work=ow.id_order "
			+ "JOIN employee emp ON ow.id_employee=emp.id_employee "
			+ "WHERE (EXTRACT(DAY FROM ets.year_month_day) = ?1 AND EXTRACT(MONTH FROM ets.year_month_day) = ?2 AND EXTRACT(YEAR FROM ets.year_month_day) = ?3 )  AND emp.id_employee = ?4 "
			+ "AND (fow.id_project = ?5 OR fow.id_base_fin = ?5 OR fow.id_ac_work_load = ?5 OR id_indirectvuas = ?5 ) AND ets.sick_leave = 'S' ",
			nativeQuery = true)
	EmployeeTimeSheet findByDateAndSickLeaveS(int day, int month, int year, int employeeId, int finSourceId);

}
