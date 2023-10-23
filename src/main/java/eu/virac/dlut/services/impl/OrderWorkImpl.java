package eu.virac.dlut.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.OrderWork;
import eu.virac.dlut.repos.IOrderWorkRepo;
import eu.virac.dlut.services.IOrderWorkService;

@Service
public class OrderWorkImpl implements IOrderWorkService{
	
	@Autowired
	IOrderWorkRepo ordWorkRepo;

	@Override
	public OrderWork selectOrderWorkByEmployeeId(int employeeId) {
		OrderWork res = ordWorkRepo.findByEmployeeIdEmployee(employeeId);
		return null;
	}

}
