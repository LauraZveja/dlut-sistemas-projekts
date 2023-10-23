package eu.virac.dlut.services;

import java.util.ArrayList;

import eu.virac.dlut.models.FinanceOrderWork;

public interface IFinanceOrderWorkService {

	//String selectPositionInProjectForEmployee(int employeeId, int projectId);
	String selectPositionInProjectForEmployee(int employeeId, int projectId);
	
	FinanceOrderWork selectFinanceOrderWorkByOrderWorkId(int ordWorkId);
	
	double selectPayPerHourForSpecificEmployeeInProject(int employeeId, int projectId);
	
	double selectPayPerHourForSpecificEmployeeInFinSource(int employeeId, int finSourceId);
}
