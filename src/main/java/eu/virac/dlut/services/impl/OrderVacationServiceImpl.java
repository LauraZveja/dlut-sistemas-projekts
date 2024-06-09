package eu.virac.dlut.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.OrderVacation;
import eu.virac.dlut.repos.IOrderVacationRepo;
import eu.virac.dlut.services.IOrderVacationService;

@Service
public class OrderVacationServiceImpl implements IOrderVacationService{

	@Autowired
	IOrderVacationRepo orderVacationRepo;
	
	@Override
	public boolean isAnnualVacationDay(int day, int month, int year, int employeeId) {
		OrderVacation ov = orderVacationRepo.findByDateAndOrderVacationIDAndDesignation(day, month, year, employeeId);
		if (ov != null) {
				return true;

		}
		return false;
	}
	

}
