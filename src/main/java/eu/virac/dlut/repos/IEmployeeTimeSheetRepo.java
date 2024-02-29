package eu.virac.dlut.repos;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.EmployeeTimeSheet;
import eu.virac.dlut.models.Project;
import org.springframework.data.repository.query.Param;

public interface IEmployeeTimeSheetRepo extends CrudRepository<EmployeeTimeSheet, Integer> {

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

    @Query(value = "CALL GetHoursWorkedOnSpecificDayForEmployeeProject(:yearParam, :monthParam, :dayParam, :employeeIdParam, :projectIdParam);", nativeQuery = true)
    ArrayList<Double> getHoursWorkedOnSpecificDayForEmployeeProject(@Param("yearParam") String year,
                                                                    @Param("monthParam") String month,
                                                                    @Param("dayParam") String day,
                                                                    @Param("employeeIdParam") int employeeId,
                                                                    @Param("projectIdParam") int projectId);

    @Query(value = "CALL GetSickDaysByDateAndEmployeeAndProject(:yearParam, :monthParam, :employeeIdParam, :projectIdParam);", nativeQuery = true)
    int getSickDaysByDateAndEmployeeAndProject(@Param("yearParam") String year,
                                               @Param("monthParam") String month,
                                               @Param("employeeIdParam") int employeeId,
                                               @Param("projectIdParam") int projectId);


    @Query(value = "CALL GetVacationTimeByDateAndEmployeeAndProject(:yearParam, :monthParam, :employeeIdParam, :projectIdParam);", nativeQuery = true)
    int getVacationTimeBySpecificDateAndEmployeeAndProject(@Param("yearParam") String year,
                                                           @Param("monthParam") String month,
                                                           @Param("employeeIdParam") int employeeId,
                                                           @Param("projectIdParam") int projectId);


	@Query(value = "CALL GetHoursWorkedForEmployeeBaseFin(:yearParam, :monthParam, :employeeIdParam, :baseFinIdParam);", nativeQuery = true)
	ArrayList<Double> getHoursWorkedForEmployeeBaseFin(@Param("yearParam") String year,
													   @Param("monthParam") String month,
													   @Param("employeeIdParam") int idEmployee,
													   @Param("baseFinIdParam") int baseFinId);


	@Query(value = "CALL GetEmployeeHoursWorkedOnSpecificDay(:yearParam, :monthParam, :dayParam, :employeeIdParam, :finSourceIdParam);", nativeQuery = true)
	ArrayList<Double> getEmployeeHoursWorkedOnSpecificDay(@Param("yearParam") int year,
														  @Param("monthParam") int month,
														  @Param("dayParam") int day,
														  @Param("employeeIdParam") int employeeId,
														  @Param("finSourceIdParam") int finSourceId);


	//sick days
	@Query(value = "CALL GetSickDaysSByDateAndEmployeeAndFinSource(:yearParam, :monthParam, :employeeIdParam, :finSourceIdParam);", nativeQuery = true)
	int getSickDaysSByDateAndEmployeeAndFinSource(@Param("yearParam") String year,
												  @Param("monthParam") String month,
												  @Param("employeeIdParam") int employeeId,
												  @Param("finSourceIdParam") int finSourceId);

	@Query(value = "CALL GetByYearMonthSickLeaveSForEmployeeFinSource(:yearParam, :monthParam, :employeeIdParam, :finSourceIdParam);", nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndSickLeaveSForEmployeeFinSource(@Param("yearParam") int year,
																					@Param("monthParam") int month,
																					@Param("employeeIdParam") int employeeId,
																					@Param("finSourceIdParam") int finSourceId);


	@Query(value = "CALL GetByYearAndMonthAndSickLeaveSbForEmployeeFinSource(:year, :month, :employeeId, :finSourceId);", nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndSickLeaveSbForEmployeeFinSource(@Param("year") int year,
																					 @Param("month") int month,
																					 @Param("employeeId") int employeeId,
																					 @Param("finSourceId") int finSourceId);


	@Query(value = "CALL GetByYearAndMonthAndMissionEmployeeFinSource(:year, :month, :employeeId, :finSourceId);", nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndMissionEmployeeFinSource(@Param("year") int year,
																			  @Param("month") int month,
																			  @Param("employeeId") int employeeId,
																			  @Param("finSourceId") int finSourceId);


	//lai atlasītu tās dienas vienā mēnesī un gadā, kad ir ikgadējais atvaļinājums
	@Query(value = "CALL GetByYearAndMonthAndOrderVacation(:year, :month);", nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndOrderVacationForEmployeeFinSource(@Param("year") int year,
																					   @Param("month") int month);



	@Query(value = "CALL GetByYearMonthForEmployeeInFinSource(:year, :month, :employeeId, :finSourceId);", nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndForEmployeeInFinSource(@Param("year") String year,
																			@Param("month") String month,
																			@Param("employeeId") int employeeId,
																			@Param("finSourceId") int finSourceId);

	@Query(value = "CALL GetByYearAndMonthAndOrderOther(:year, :month);", nativeQuery = true)
	ArrayList<EmployeeTimeSheet> getByYearAndMonthAndOrderOther(@Param("year") int year,
																@Param("month") int month);


	@Query(value = "CALL GetHoursWorkedForEmployeeInBaseFin(:year, :month, :employeeId, :baseFinId);", nativeQuery = true)
	ArrayList<Double> getHoursWorkedForEmployeeInBaseFin(@Param("year") int year,
														 @Param("month") int month,
														 @Param("employeeId") int idEmployee,
														 @Param("baseFinId") int baseFinId);


	@Query(value = "CALL GetHoursWorkedForEmployeeInAcademicWorkLoad(:year, :month, :employeeId, :academicWorkLoadId);", nativeQuery = true)
	ArrayList<Double> getHoursWorkedForEmployeeInacademicWorkLoad(@Param("year") int year,
																  @Param("month") int month,
																  @Param("employeeId") int idEmployee,
																  @Param("academicWorkLoadId") int academicWorkLoadId);

	@Query(value = "CALL GetHoursWorkedForEmployeeInIndirectVuas(:year, :month, :employeeId, :indirectVuasId);", nativeQuery = true)
	ArrayList<Double> getHoursWorkedForEmployeeInIndirectVuas(@Param("year") int year,
															  @Param("month") int month,
															  @Param("employeeId") int idEmployee,
															  @Param("indirectVuasId") int indirectVuasId);

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
