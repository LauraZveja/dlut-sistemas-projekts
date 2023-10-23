package eu.virac.dlut.services;

public interface IOrderVacationService {
	
	boolean isAnnualVacationDay(int day, int month, int year, int employeeId);

}
