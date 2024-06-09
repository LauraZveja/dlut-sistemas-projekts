package eu.virac.dlut.services;

public interface IFullTimeEquivalentService {

	boolean updateVacationHoursByYearMonthFinSourceEmployeeFromEditEmployee(int year, int month, int employeeId, int finSourceId,
			double vacationHours);
}
