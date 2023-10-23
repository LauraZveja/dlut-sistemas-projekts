package eu.virac.dlut.services;

import eu.virac.dlut.models.OrderWork;

public interface IOrderWorkService {

	OrderWork selectOrderWorkByEmployeeId(int employeeId);
}
