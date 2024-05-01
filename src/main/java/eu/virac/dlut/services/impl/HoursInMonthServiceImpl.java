package eu.virac.dlut.services.impl;

import java.util.ArrayList;

import eu.virac.dlut.models.helpers.HoursInMonthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.HoursInMonth;
import eu.virac.dlut.repos.IHoursInMonthRepo;
import eu.virac.dlut.services.IHoursInMonthService;

@Service
public class HoursInMonthServiceImpl implements IHoursInMonthService{
	
	@Autowired
	IHoursInMonthRepo hoursInMonthRepo;
	
	@Override
	public boolean insertHoursInMonth(int year, int month, double hours) {

		HoursInMonth h = hoursInMonthRepo.findByYearAndMonth(year, month);
		if (h == null) {
			HoursInMonth hNew = new HoursInMonth(year, month, hours);
			hoursInMonthRepo.save(hNew);
			return true;
		} else {
			h.setHoursInMonth(hours);
			hoursInMonthRepo.save(h);
			return true;
		}
			
		
	}

	@Override
	public HoursInMonth updateHoursInMonthById(int id, int year, int month, double hours) throws Exception {
		if (hoursInMonthRepo.existsById(id)) {
			HoursInMonth hEdit = hoursInMonthRepo.findById(id).get();
			hEdit.setYear(year);
			hEdit.setMonth(month);
			hEdit.setHoursInMonth(hours);
			hoursInMonthRepo.save(hEdit);
			return hEdit;
		} else
			throw new Exception("Id nav pareizs");
	}
	
	@Override
	public ArrayList<HoursInMonth> selectAllHoursInMonths() {
		return (ArrayList<HoursInMonth>) hoursInMonthRepo.findAll();
	}
	
	@Override
	public HoursInMonth selectHoursInMonthByYearAndMonth(int year, int month) throws Exception {
		HoursInMonth h = hoursInMonthRepo.findByYearAndMonth(year, month);
		if(h != null)
			return h;
		
		throw new Exception("Hours in month and year not found!");
	}

	@Override
	public HoursInMonthDTO insertHoursInMonthDTO(HoursInMonthDTO hoursInMonthDTO) throws Exception {
		HoursInMonth existingHoursInMonth = hoursInMonthRepo.findByYearAndMonth(hoursInMonthDTO.getYear(), hoursInMonthDTO.getMonth());
		if (existingHoursInMonth != null) {
			throw new Exception("Hours for this month and year have already been submitted.");
		}
		HoursInMonth hoursInMonth = new HoursInMonth(hoursInMonthDTO.getYear(), hoursInMonthDTO.getMonth(), hoursInMonthDTO.getHoursInMonth());
		hoursInMonthRepo.save(hoursInMonth);
		hoursInMonthDTO.setIdHoursInMonth(hoursInMonth.getIdHoursInMonth());
		return hoursInMonthDTO;
	}
}
