package eu.virac.dlut.services;

import java.util.ArrayList;

import eu.virac.dlut.models.HoursInMonth;

public interface IHoursInMonthService {
	
	boolean insertHoursInMonth(int year, int month, double hours);
	
	HoursInMonth updateHoursInMonthById(int id, int year, int month, double hours) throws Exception;
	
	ArrayList<HoursInMonth> selectAllHoursInMonths();

	HoursInMonth selectHoursInMonthByYearAndMonth(int year, int month) throws Exception;
}
