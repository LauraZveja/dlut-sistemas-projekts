package eu.virac.dlut.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.FinanceOrderWork;
import eu.virac.dlut.repos.IFinanceOrderWorkRepo;
import eu.virac.dlut.services.IFinanceOrderWorkService;

@Service
public class FinanceOrderWorkServiceImpl implements IFinanceOrderWorkService{
	
	@Autowired
	IFinanceOrderWorkRepo finOrdWorkRepo;

//	@Override
//	public String selectPositionInProjectForEmployee(int employeeId, int projectId) {
//		String res = finOrdWorkRepo.getPositionInProjectForEmployee(employeeId, projectId);	
//		return res;
//	}
	@Override
	public String selectPositionInProjectForEmployee(int employeeId, int projectId) {
		String res = finOrdWorkRepo.getPositionInProjectForEmployee(employeeId, projectId);	
		return res;
	}

	@Override
	public FinanceOrderWork selectFinanceOrderWorkByOrderWorkId(int ordWorkId) {
		FinanceOrderWork res = finOrdWorkRepo.findByOrdWorkIdOrder(ordWorkId);
		return res;
	}

	@Override
	public double selectPayPerHourForSpecificEmployeeInProject(int employeeId, int projectId) {
		double res = finOrdWorkRepo.getPayPerHourByEmployeeAndProject(employeeId, projectId);
		return res;
	}

	@Override
	public double selectPayPerHourForSpecificEmployeeInFinSource(int employeeId, int finSourceId) {
		double res = finOrdWorkRepo.getPayPerHourByEmployeeInFinSource(employeeId, finSourceId);
		return res;
	}
	
}
