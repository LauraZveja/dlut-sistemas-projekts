package eu.virac.dlut.repos;

import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.FullTimeEquivalent;

public interface IFullTimeEquivalentRepo extends CrudRepository<FullTimeEquivalent, Integer>{

	FullTimeEquivalent findByYearAndMonthAndEmployeeIdEmployeeAndFinanceSourceIdFinSource(int year, int month, int employeeId,
			int finSourceId);

}
