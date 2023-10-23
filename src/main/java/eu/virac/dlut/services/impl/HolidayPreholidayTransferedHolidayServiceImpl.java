package eu.virac.dlut.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.HolidayPreholidayTransferedHoliday;
import eu.virac.dlut.repos.IHolidayPreholidayTransferedHolidayRepo;
import eu.virac.dlut.services.IHolidayPreholidayTransferedHolidayService;

@Service
public class HolidayPreholidayTransferedHolidayServiceImpl implements IHolidayPreholidayTransferedHolidayService {

	@Autowired
	IHolidayPreholidayTransferedHolidayRepo holidayRepo;

	@Override
	public boolean isHoliday(int day, int month, int year) {
		HolidayPreholidayTransferedHoliday h = holidayRepo.findByDateAndHolidayTitle(day, month, year);
		if (h != null) 
			return true;
		
		return false;
	}

	@Override
	public boolean isTransferedWorkDay(int day, int month, int year) {
		HolidayPreholidayTransferedHoliday td = holidayRepo.findByDateAndTransferedWorkDayRemark(day, month, year);
		if (td != null)
			return true;

		return false;
	}

	@Override
	public boolean isTransferedHoliday(int day, int month, int year) {
		HolidayPreholidayTransferedHoliday td = holidayRepo.findByDateAndTransferedHolidayRemark(day, month, year);
		if (td != null)
			return true;

		return false;
	}

	@Override
	public boolean isPreHoliday(int day, int month, int year) {
		HolidayPreholidayTransferedHoliday td = holidayRepo.findByDateAndPreHolidya(day, month, year);
		if (td != null)
			return true;

		return false;
	}
	
}
