package eu.virac.dlut.repos;

import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.OrderWork;

public interface IOrderWorkRepo extends CrudRepository<OrderWork, Integer>{

	OrderWork findByEmployeeIdEmployee(int employeeId);

}
