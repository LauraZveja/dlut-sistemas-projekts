package eu.virac.dlut.services;

import eu.virac.dlut.models.FullTimeEquivalent;

public interface IFullTimeEquivalentService {

//	boolean updateVacationHoursByYearMonthFinSourceEmployee(int year, int month, int finSourceId,
//			int employeeId, FullTimeEquivalent fullTimeEqivalent);
	
	boolean updateVacationHoursByYearMonthFinSourceEmployeeFromEditEmployee(int year, int month, int employeeId, int finSourceId,
			double vacationHours);
}
