DROP PROCEDURE IF EXISTS getAcademicWorkLoadForEmployee;

CREATE PROCEDURE getAcademicWorkLoadForEmployee(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT
)

SELECT awl.id_fin_source, awl.code, awl.title
FROM academic_work_load awl
         JOIN finance_order_work fow ON awl.id_fin_source = fow.id_ac_work_load
         JOIN employee_time_sheet ets ON fow.id_fin_ord_work = ets.id_fin_ord_work_employee
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE (EXTRACT(YEAR FROM ets.year_month_day) = yearParam AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam)
  AND emp.id_employee = employeeIdParam
GROUP BY awl.id_fin_source;


DROP PROCEDURE IF EXISTS getBaseFinForEmployee;

CREATE PROCEDURE getBaseFinForEmployee(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT
)

SELECT bf.id_fin_source, bf.code, bf.title
FROM base_fin bf
         JOIN finance_order_work fow ON bf.id_fin_source = fow.id_base_fin
         JOIN employee_time_sheet ets ON fow.id_fin_ord_work = ets.id_fin_ord_work_employee
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE (EXTRACT(YEAR FROM ets.year_month_day) = yearParam AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam)
  AND emp.id_employee = employeeIdParam
GROUP BY bf.id_fin_source;


DROP PROCEDURE IF EXISTS GetAllEmployeesInOneProject;

CREATE PROCEDURE GetAllEmployeesInOneProject(IN projId INT)

SELECT * FROM employee e
                  JOIN order_work ow ON e.id_employee = ow.id_employee_order
                  JOIN finance_order_work fow ON ow.id_order = fow.id_ord_work
                  JOIN project p ON fow.id_project = p.id_fin_source
WHERE p.id_fin_source = projId AND e.is_deleted = FALSE;


DROP PROCEDURE IF EXISTS GetAllEmployeesInBaseFin;

CREATE PROCEDURE GetAllEmployeesInBaseFin(IN baseFinId INT)

SELECT * FROM employee e
                  JOIN order_work ow ON e.id_employee = ow.id_employee
                  JOIN finance_order_work fow ON ow.id_order = fow.id_ord_work
                  JOIN base_fin b ON fow.id_base_fin = b.id_fin_source
WHERE b.id_fin_source = baseFinId AND e.is_deleted = FALSE;


DROP PROCEDURE IF EXISTS GetAllEmployeesInAcademicWorkLoad;

CREATE PROCEDURE GetAllEmployeesInAcademicWorkLoad(IN academicWorkLoadId INT)

SELECT e.id_employee, e.is_elected, e.name, e.surname, e.position, e.is_elected, e.work_contract_no_date, e.id_department, e.id_employee
FROM employee e
         JOIN order_work ow ON e.id_employee = ow.id_employee
         JOIN finance_order_work fow ON ow.id_order = fow.id_ord_work
         JOIN academic_work_load awl ON fow.id_ac_work_load = awl.id_fin_source
WHERE awl.id_fin_source = academicWorkLoadId AND e.is_deleted = FALSE;


DROP PROCEDURE IF EXISTS GetAllEmployeesInIndirectVuas;

CREATE PROCEDURE GetAllEmployeesInIndirectVuas(IN indirectVuasId INT)

SELECT * FROM employee e
                  JOIN order_work ow ON e.id_employee = ow.id_employee
                  JOIN finance_order_work fow ON ow.id_order = fow.id_ord_work
                  JOIN indirect_vuas i ON fow.id_indirectvuas = i.id_fin_source
WHERE i.id_fin_source = indirectVuasId AND e.is_deleted = FALSE;


DROP PROCEDURE IF EXISTS GetHoursWorkedForEmployeeInProject;

CREATE PROCEDURE GetHoursWorkedForEmployeeInProject(
    IN yearParam INT,
    IN monthParam INT,
    IN idEmployeeParam INT,
    IN idProjectParam INT
)

SELECT ets.hours_worked
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN project p ON fow.id_project = p.id_fin_source
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE (EXTRACT(YEAR FROM ets.year_month_day) = yearParam AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam)
  AND emp.id_employee = idEmployeeParam AND p.id_fin_source = idProjectParam;


DROP PROCEDURE IF EXISTS GetDistinctYearsFromTimeSheet;

CREATE PROCEDURE GetDistinctYearsFromTimeSheet()

SELECT DISTINCT EXTRACT(YEAR FROM year_month_day) AS distinct_year FROM employee_time_sheet;


DROP PROCEDURE IF EXISTS GetHoursWorkedOnSpecificDayForEmployeeProject;

CREATE PROCEDURE GetHoursWorkedOnSpecificDayForEmployeeProject(
    IN yearParam INT,
    IN monthParam INT,
    IN dayParam INT,
    IN employeeIdParam INT,
    IN projectIdParam INT
)

SELECT ets.hours_worked
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND EXTRACT(DAY FROM ets.year_month_day) = dayParam
  AND emp.id_employee = employeeIdParam
  AND fow.id_project = projectIdParam;


DROP PROCEDURE IF EXISTS GetSickDaysByDateAndEmployeeAndProject;

CREATE PROCEDURE GetSickDaysByDateAndEmployeeAndProject(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN projectIdParam INT
)

SELECT COUNT(ets.sick_leave)
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
  AND fow.id_project = projectIdParam
  AND ets.sick_leave IS NOT NULL;


DROP PROCEDURE IF EXISTS GetVacationTimeByDateAndEmployeeAndProject;

CREATE PROCEDURE GetVacationTimeByDateAndEmployeeAndProject(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN projectIdParam INT
)

SELECT COUNT(ets.id_ord_vacation)
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
  AND fow.id_project = projectIdParam
  AND ets.id_ord_vacation IS NOT NULL;


DROP PROCEDURE IF EXISTS GetHoursWorkedForEmployeeBaseFin;

CREATE PROCEDURE GetHoursWorkedForEmployeeBaseFin(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN baseFinIdParam INT
)

SELECT ets.hours_worked
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN base_fin b ON fow.id_project = b.id_fin_source
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
  AND b.id_fin_source = baseFinIdParam;


DROP PROCEDURE IF EXISTS GetEmployeeHoursWorkedOnSpecificDay;

CREATE PROCEDURE GetEmployeeHoursWorkedOnSpecificDay(
    IN yearParam INT,
    IN monthParam INT,
    IN dayParam INT,
    IN employeeIdParam INT,
    IN finSourceIdParam INT
)

SELECT ets.hours_worked
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND EXTRACT(DAY FROM ets.year_month_day) = dayParam
  AND emp.id_employee = employeeIdParam
  AND (fow.id_base_fin = finSourceIdParam OR fow.id_project = finSourceIdParam OR fow.id_ac_work_load = finSourceIdParam OR fow.id_indirectvuas = finSourceIdParam);


DROP PROCEDURE IF EXISTS GetSickDaysSByDateAndEmployeeAndFinSource;

CREATE PROCEDURE GetSickDaysSByDateAndEmployeeAndFinSource(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN finSourceIdParam INT
)

SELECT COUNT(ets.sick_leave)
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
  AND (fow.id_project = finSourceIdParam OR fow.id_base_fin = finSourceIdParam OR fow.id_ac_work_load = finSourceIdParam OR fow.id_indirectvuas = finSourceIdParam)
  AND ets.sick_leave IS NOT NULL;


DROP PROCEDURE IF EXISTS GetByYearMonthSickLeaveSForEmployeeFinSource;

CREATE PROCEDURE GetByYearMonthSickLeaveSForEmployeeFinSource(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN finSourceIdParam INT
)

SELECT *
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
  AND (fow.id_project = finSourceIdParam OR fow.id_base_fin = finSourceIdParam OR fow.id_ac_work_load = finSourceIdParam OR fow.id_indirectvuas = finSourceIdParam)
  AND ets.sick_leave = 'S';


DROP PROCEDURE IF EXISTS GetByYearAndMonthAndSickLeaveSbForEmployeeFinSource;

CREATE PROCEDURE GetByYearAndMonthAndSickLeaveSbForEmployeeFinSource(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN finSourceIdParam INT
)

SELECT *
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE (EXTRACT(YEAR FROM ets.year_month_day) = yearParam AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam)
  AND emp.id_employee = employeeIdParam
  AND (fow.id_project = finSourceIdParam OR fow.id_base_fin = finSourceIdParam OR fow.id_ac_work_load = finSourceIdParam OR fow.id_indirectvuas = finSourceIdParam)
  AND ets.sick_leave = 'Sb';


DROP PROCEDURE IF EXISTS GetByYearAndMonthAndMissionEmployeeFinSource;

CREATE PROCEDURE GetByYearAndMonthAndMissionEmployeeFinSource(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN finSourceIdParam INT
)

SELECT *
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
  AND (fow.id_project = finSourceIdParam OR fow.id_base_fin = finSourceIdParam OR fow.id_ac_work_load = finSourceIdParam OR fow.id_indirectvuas = finSourceIdParam)
  AND ets.id_ord_mission IS NOT NULL;


DROP PROCEDURE IF EXISTS GetByYearAndMonthAndOrderVacation;

CREATE PROCEDURE GetByYearAndMonthAndOrderVacation(
    IN yearParam INT,
    IN monthParam INT
)

SELECT *
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND ets.id_ord_vacation IS NOT NULL;


DROP PROCEDURE IF EXISTS GetByYearMonthForEmployeeInFinSource;

CREATE PROCEDURE GetByYearMonthForEmployeeInFinSource(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN finSourceIdParam INT
)

SELECT *
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
  AND (fow.id_project = finSourceIdParam OR fow.id_base_fin = finSourceIdParam OR fow.id_ac_work_load = finSourceIdParam OR fow.id_indirectvuas = finSourceIdParam);


DROP PROCEDURE IF EXISTS GetByYearAndMonthAndOrderOther;

CREATE PROCEDURE GetByYearAndMonthAndOrderOther(
    IN yearParam INT,
    IN monthParam INT
)

SELECT *
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND ets.id_ord_other IS NOT NULL;


DROP PROCEDURE IF EXISTS GetHoursWorkedForEmployeeInBaseFin;

CREATE PROCEDURE GetHoursWorkedForEmployeeInBaseFin(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN baseFinIdParam INT
)

SELECT ets.hours_worked
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN base_fin b ON fow.id_base_fin = b.id_fin_source
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
  AND b.id_fin_source = baseFinIdParam;


DROP PROCEDURE IF EXISTS GetHoursWorkedForEmployeeInAcademicWorkLoad;

CREATE PROCEDURE GetHoursWorkedForEmployeeInAcademicWorkLoad(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN academicWorkLoadIdParam INT
)

SELECT ets.hours_worked
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN academic_work_load awl ON fow.id_ac_work_load = awl.id_fin_source
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
  AND awl.id_fin_source = academicWorkLoadIdParam;


DROP PROCEDURE IF EXISTS GetHoursWorkedForEmployeeInIndirectVuas;

CREATE PROCEDURE GetHoursWorkedForEmployeeInIndirectVuas(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN indirectVuasIdParam INT
)

SELECT ets.hours_worked
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN indirect_vuas i ON fow.id_indirectvuas = i.id_fin_source
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
  AND i.id_fin_source = indirectVuasIdParam;


DROP PROCEDURE IF EXISTS GetByYearAndMonthAndUnjustifAbsenceForEmployeeInFinSource;

CREATE PROCEDURE GetByYearAndMonthAndUnjustifAbsenceForEmployeeInFinSource(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN finSourceIdParam INT
)

SELECT *
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
  AND (fow.id_project = finSourceIdParam OR fow.id_base_fin = finSourceIdParam OR fow.id_ac_work_load = finSourceIdParam OR fow.id_indirectvuas = finSourceIdParam)
  AND ets.remarks = 'N';


DROP PROCEDURE IF EXISTS FindByDateAndRemarkRecuperation;

CREATE PROCEDURE FindByDateAndRemarkRecuperation(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT,
    IN finSourceIdParam INT
)

SELECT ets.remarks
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
  AND (fow.id_project = finSourceIdParam OR fow.id_base_fin = finSourceIdParam OR fow.id_ac_work_load = finSourceIdParam OR fow.id_indirectvuas = finSourceIdParam)
  AND ets.remarks = 'BR';

DROP PROCEDURE IF EXISTS GetWorkedHoursOneEmployeeOnSpecificDate;

CREATE PROCEDURE GetWorkedHoursOneEmployeeOnSpecificDate(
    IN yearParam INT,
    IN monthParam INT,
    IN dayParam INT,
    IN employeeIdParam INT
)

SELECT ets.hours_worked
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND EXTRACT(DAY FROM ets.year_month_day) = dayParam
  AND emp.id_employee = employeeIdParam;


DROP PROCEDURE IF EXISTS GetAllByYearMonthDayForEmployeeInFinSource;

CREATE PROCEDURE GetAllByYearMonthDayForEmployeeInFinSource(
    IN yearParam INT,
    IN monthParam INT,
    IN dayParam INT,
    IN employeeIdParam INT,
    IN finSourceIdParam INT
)

SELECT *
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND EXTRACT(DAY FROM ets.year_month_day) = dayParam
  AND emp.id_employee = employeeIdParam
  AND (fow.id_project = finSourceIdParam OR fow.id_base_fin = finSourceIdParam OR fow.id_ac_work_load = finSourceIdParam OR fow.id_indirectvuas = finSourceIdParam);


DROP PROCEDURE IF EXISTS FindByDateAndSickLeaveS;

CREATE PROCEDURE FindByDateAndSickLeaveS(
    IN dayParam INT,
    IN monthParam INT,
    IN yearParam INT,
    IN employeeIdParam INT,
    IN finSourceIdParam INT
)

SELECT *
FROM employee_time_sheet ets
         JOIN finance_order_work fow ON ets.id_fin_ord_work_employee = fow.id_fin_ord_work
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(DAY FROM ets.year_month_day) = dayParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND emp.id_employee = employeeIdParam
  AND (fow.id_project = finSourceIdParam OR fow.id_base_fin = finSourceIdParam OR fow.id_ac_work_load = finSourceIdParam OR fow.id_indirectvuas = finSourceIdParam)
  AND ets.sick_leave = 'S';


DROP PROCEDURE IF EXISTS GetPositionInProjectForEmployee;

CREATE PROCEDURE GetPositionInProjectForEmployee(
    IN employeeIdParam INT,
    IN finSourceIdParam INT
)

SELECT fow.position_in_project
FROM finance_order_work fow
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE emp.id_employee = employeeIdParam AND fow.id_project = finSourceIdParam;


DROP PROCEDURE IF EXISTS GetPayPerHourByEmployeeAndProject;

CREATE PROCEDURE GetPayPerHourByEmployeeAndProject(
    IN employeeIdParam INT,
    IN projectIdParam INT
)

SELECT fow.pay_per_hour
FROM finance_order_work fow
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE emp.id_employee = employeeIdParam AND fow.id_project = projectIdParam;


DROP PROCEDURE IF EXISTS GetPayPerHourByEmployeeInFinSource;

CREATE PROCEDURE GetPayPerHourByEmployeeInFinSource(
    IN employeeIdParam INT,
    IN finSourceIdParam INT
)

SELECT fow.pay_per_hour
FROM finance_order_work fow
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE emp.id_employee = employeeIdParam
  AND (fow.id_project = finSourceIdParam OR fow.id_base_fin = finSourceIdParam OR fow.id_ac_work_load = finSourceIdParam OR fow.id_indirectvuas = finSourceIdParam);


DROP PROCEDURE IF EXISTS GetFinanceOrderWorkByEmployeeAndDetails;

CREATE PROCEDURE GetFinanceOrderWorkByEmployeeAndDetails(
    IN employeeIdParam INT,
    IN financeSourceIdParam INT,
    IN positionParam VARCHAR(255)
)

SELECT *
FROM finance_order_work fow
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE emp.id_employee = employeeIdParam
  AND (fow.id_project = financeSourceIdParam OR fow.id_base_fin = financeSourceIdParam OR fow.id_ac_work_load = financeSourceIdParam OR fow.id_indirectvuas = financeSourceIdParam)
  AND fow.position_in_project = positionParam;


DROP PROCEDURE IF EXISTS GetByEmployeeIdAndFinanceSource;

CREATE PROCEDURE GetByEmployeeIdAndFinanceSource(
    IN employeeIdParam INT,
    IN financeSourceIdParam INT
)

SELECT *
FROM finance_order_work fow
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE emp.id_employee = employeeIdParam
  AND (fow.id_project = financeSourceIdParam OR fow.id_base_fin = financeSourceIdParam OR fow.id_ac_work_load = financeSourceIdParam OR fow.id_indirectvuas = financeSourceIdParam);


DROP PROCEDURE IF EXISTS FindByDateAndHolidayTitle;

CREATE PROCEDURE FindByDateAndHolidayTitle(
    IN dayParam INT,
    IN monthParam INT,
    IN yearParam INT
)

SELECT *
FROM holiday_preholiday_transfered_holiday h
         JOIN day_status d ON h.id_day_status_holiday = d.id_day_status
WHERE EXTRACT(DAY FROM h.date) = dayParam
  AND EXTRACT(MONTH FROM h.date) = monthParam
  AND EXTRACT(YEAR FROM h.date) = yearParam
  AND d.day_title LIKE 'Sv%';


DROP PROCEDURE IF EXISTS FindByDateAndTransferredWorkDayRemark;

CREATE PROCEDURE FindByDateAndTransferredWorkDayRemark(
    IN dayParam INT,
    IN monthParam INT,
    IN yearParam INT
)

SELECT *
FROM holiday_preholiday_transfered_holiday h
         JOIN day_status d ON h.id_day_status_holiday = d.id_day_status
WHERE EXTRACT(DAY FROM h.date) = dayParam
  AND EXTRACT(MONTH FROM h.date) = monthParam
  AND EXTRACT(YEAR FROM h.date) = yearParam
  AND d.day_title = 'Pārcelta-D';


DROP PROCEDURE IF EXISTS FindByDateAndTransferredHolidayRemark;

CREATE PROCEDURE FindByDateAndTransferredHolidayRemark(
    IN dayParam INT,
    IN monthParam INT,
    IN yearParam INT
)

SELECT *
FROM holiday_preholiday_transfered_holiday h
         JOIN day_status d ON h.id_day_status_holiday = d.id_day_status
WHERE EXTRACT(DAY FROM h.date) = dayParam
  AND EXTRACT(MONTH FROM h.date) = monthParam
  AND EXTRACT(YEAR FROM h.date) = yearParam
  AND d.day_title = 'Pārcelta-B';


DROP PROCEDURE IF EXISTS FindByDateAndPreHoliday;

CREATE PROCEDURE FindByDateAndPreHoliday(
    IN dayParam INT,
    IN monthParam INT,
    IN yearParam INT
)

SELECT *
FROM holiday_preholiday_transfered_holiday h
         JOIN day_status d ON h.id_day_status_holiday = d.id_day_status
WHERE EXTRACT(DAY FROM h.date) = dayParam
  AND EXTRACT(MONTH FROM h.date) = monthParam
  AND EXTRACT(YEAR FROM h.date) = yearParam
  AND d.day_title = 'Pirmssvētku';


DROP PROCEDURE IF EXISTS GetIndirectVuasByEmployeeAndDate;

CREATE PROCEDURE GetIndirectVuasByEmployeeAndDate(
    IN yearParam INT,
    IN monthParam INT,
    IN employeeIdParam INT
)

SELECT iv.id_fin_source, iv.code, iv.title
FROM indirect_vuas iv
         JOIN finance_order_work fow ON iv.id_fin_source = fow.id_indirectvuas
         JOIN employee_time_sheet ets ON fow.id_fin_ord_work = ets.id_fin_ord_work_employee
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND emp.id_employee = employeeIdParam
GROUP BY iv.id_fin_source;


DROP PROCEDURE IF EXISTS FindByDateAndOrderWorkMissionIDAndDesignation;

CREATE PROCEDURE FindByDateAndOrderWorkMissionIDAndDesignation(
    IN dayParam INT,
    IN monthParam INT,
    IN yearParam INT,
    IN employeeIdParam INT
)

SELECT *
FROM order_mission om
         JOIN employee_time_sheet ets ON om.id_order = ets.id_ord_mission
WHERE EXTRACT(DAY FROM ets.year_month_day) = dayParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND om.designation = 'KD'
  AND om.id_employee_order = employeeIdParam;


DROP PROCEDURE IF EXISTS FindByDateAndOrderVacationIDAndDesignation;

CREATE PROCEDURE FindByDateAndOrderVacationIDAndDesignation(
    IN dayParam INT,
    IN monthParam INT,
    IN yearParam INT,
    IN employeeIdParam INT
)

SELECT *
FROM order_vacation ov
         JOIN employee_time_sheet ets ON ov.id_order = ets.id_ord_vacation
WHERE EXTRACT(DAY FROM ets.year_month_day) = dayParam
  AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam
  AND EXTRACT(YEAR FROM ets.year_month_day) = yearParam
  AND ov.designation = 'AI'
  AND ov.id_employee_order = employeeIdParam;


DROP PROCEDURE IF EXISTS FindProjectCharacterByProjId;
CREATE PROCEDURE FindProjectCharacterByProjId(
    IN projectIdParam INT
)

SELECT pch.proj_char_title
FROM project_character pch
         JOIN project p ON p.id_proj_char = pch.id_proj_char
WHERE p.id_fin_source = projectIdParam;


DROP PROCEDURE IF EXISTS getFilteredProjects;

CREATE PROCEDURE getFilteredProjects(in yearParam INT, in monthParam INT, in employeeIdParam INT)

SELECT DISTINCT p.id_fin_source, p.code, p.title, p.accounting_acronym,
                p.contract_no, p.end, p.proj_lenght_months, p.start,
                p.id_proj_cat, p.id_proj_char, p.is_active
FROM project p
         JOIN finance_order_work fow ON p.id_fin_source = fow.id_project
         JOIN employee_time_sheet ets ON fow.id_fin_ord_work = ets.id_fin_ord_work_employee
         JOIN order_work ow ON fow.id_ord_work = ow.id_order
         JOIN employee emp ON ow.id_employee_order = emp.id_employee
WHERE (EXTRACT(YEAR FROM ets.year_month_day) = yearParam AND EXTRACT(MONTH FROM ets.year_month_day) = monthParam)
  AND emp.id_employee = employeeIdParam
GROUP BY p.id_fin_source;
