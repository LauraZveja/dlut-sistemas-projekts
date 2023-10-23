package eu.virac.dlut.services;


public interface IHolidayPreholidayTransferedHolidayService {
	
	boolean isHoliday(int day, int month, int year);
	
	boolean isTransferedWorkDay(int day, int month, int year);
	
	boolean isTransferedHoliday(int day, int month, int year);
	
	boolean isPreHoliday(int day, int month, int year);

}
