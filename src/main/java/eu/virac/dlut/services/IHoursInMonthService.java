package eu.virac.dlut.services;

import java.util.ArrayList;

import eu.virac.dlut.models.HoursInMonth;
import eu.virac.dlut.models.helpers.HoursInMonthDTO;

public interface IHoursInMonthService {
	
	boolean insertHoursInMonth(int year, int month, double hours);
	
	HoursInMonth updateHoursInMonthById(int id, int year, int month, double hours) throws Exception;
	
	ArrayList<HoursInMonth> selectAllHoursInMonths();

	HoursInMonth selectHoursInMonthByYearAndMonth(int year, int month) throws Exception;

	HoursInMonthDTO insertHoursInMonthDTO(HoursInMonthDTO hoursInMonthDTO) throws Exception;

	ArrayList<HoursInMonthDTO> retrieveAllHoursInMonthDTO();

	HoursInMonthDTO updateHoursInMonthDTO(HoursInMonthDTO hoursInMonthDTO) throws Exception;

	void deleteHoursInMonthDTO(HoursInMonthDTO hoursInMonthDTO);

	HoursInMonthDTO selectHoursInMonthByYearAndMonthDTO(int year, int month) throws Exception;

	ArrayList<HoursInMonthDTO> selectAllHoursInYear(int year);

	ArrayList<HoursInMonthDTO> insertHoursInYear (ArrayList<HoursInMonthDTO> hoursInYearDTO) throws Exception;

}
