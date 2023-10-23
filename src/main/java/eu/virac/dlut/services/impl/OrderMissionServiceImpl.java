package eu.virac.dlut.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.OrderMission;
import eu.virac.dlut.repos.IOrderMissionRepo;
import eu.virac.dlut.services.IOrderMissionService;

@Service
public class OrderMissionServiceImpl implements IOrderMissionService{

	@Autowired
	IOrderMissionRepo orderMissionRepo;
	
	@Override
	public boolean isWorkMissionDay(int day, int month, int year, int employeeId) {
		
			OrderMission wm = orderMissionRepo.findByDateAndOrderWorkMissionIDAndDesignation(day, month, year, employeeId);
			if (wm != null) {
					return true;

			}
			return false;
		}
	
	

}
