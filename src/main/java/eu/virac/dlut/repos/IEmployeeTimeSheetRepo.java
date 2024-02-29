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

    @Query(value = "CALL GetHoursWorkedForEmployeeInProject(:yearParam, :monthParam, :idEmployeeParam, :idProjectParam)", nativeQuery = true)
    ArrayList<Double> getHoursWorkedForEmployeeInProject(
            @Param("yearParam") int year,
            @Param("monthParam") int month,
            @Param("idEmployeeParam") int idEmployee,
            @Param("idProjectParam") int idProject
    );

    @Query(value = "CALL GetDistinctYearsFromTimeSheet()", nativeQuery = true)
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
    @Query(value = "CALL GetByYearAndMonthAndUnjustifAbsenceForEmployeeInFinSource(:year, :month, :employeeId, :finSourceId);", nativeQuery = true)
    ArrayList<EmployeeTimeSheet> getByYearAndMonthAndUnjustifAbsenceForEmployeeInFinSource(@Param("year") int year,
                                                                                           @Param("month") int month,
                                                                                           @Param("employeeId") int employeeId,
                                                                                           @Param("finSourceId") int finSourceId);


    @Query(value = "CALL FindByDateAndRemarkRecuperation(:year, :month, :employeeId, :finSourceId);", nativeQuery = true)
    EmployeeTimeSheet findByDateAndRemarkRecuperation(@Param("year") int year,
                                                      @Param("month") int month,
                                                      @Param("employeeId") int idEmployee,
                                                      @Param("finSourceId") int finSourceId);


    @Query(value = "CALL GetWorkedHoursOneEmployeeOnSpecificDate(:year, :month, :day, :employeeId);", nativeQuery = true)
    ArrayList<Double> getWorkedHoursOneEmployeeOnSpecificDate(@Param("year") int year,
                                                              @Param("month") int month,
                                                              @Param("day") int day,
                                                              @Param("employeeId") int employeeId);


    @Query(value = "CALL GetAllByYearMonthDayForEmployeeInFinSource(:year, :month, :day, :employeeId, :finSourceId);", nativeQuery = true)
    EmployeeTimeSheet getAllByYearAndMonthAndDayForEmployeeInFinSource(@Param("year") int year,
                                                                       @Param("month") int month,
                                                                       @Param("day") int day,
                                                                       @Param("employeeId") int employeeId,
                                                                       @Param("finSourceId") int finSourceId);


    @Query(value = "CALL FindByDateAndSickLeaveS(:day, :month, :year, :employeeId, :finSourceId);", nativeQuery = true)
    EmployeeTimeSheet findByDateAndSickLeaveS(@Param("day") int day,
                                              @Param("month") int month,
                                              @Param("year") int year,
                                              @Param("employeeId") int employeeId,
                                              @Param("finSourceId") int finSourceId);


}
