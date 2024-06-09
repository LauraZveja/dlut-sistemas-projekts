package eu.virac.dlut.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.FinanceOrderWork;
import org.springframework.data.repository.query.Param;

public interface IFinanceOrderWorkRepo extends CrudRepository<FinanceOrderWork, Integer>{

	@Query(value = "CALL GetPositionInProjectForEmployee(:idEmployee, :idFinSource);", nativeQuery = true)
	String getPositionInProjectForEmployee(@Param("idEmployee") int idEmployee,
										   @Param("idFinSource") int idFinSource);


	FinanceOrderWork findByOrdWorkIdOrder(int ordWorkId);

	@Query(value = "CALL GetPayPerHourByEmployeeAndProject(:employeeId, :projectId);", nativeQuery = true)
	double getPayPerHourByEmployeeAndProject(@Param("employeeId") int employeeId,
											 @Param("projectId") int projectId);


	@Query(value = "CALL GetPayPerHourByEmployeeInFinSource(:employeeId, :finSourceId);", nativeQuery = true)
	double getPayPerHourByEmployeeInFinSource(@Param("employeeId") int employeeId,
											  @Param("finSourceId") int finSourceId);


	//ArrayList<FinanceOrderWork> findByIdBaseFinNotNull();

	@Query(value = "CALL GetFinanceOrderWorkByEmployeeAndDetails(:employeeId, :financeSourceId, :position);", nativeQuery = true)
	FinanceOrderWork getFinanceOrderWorkByEmployeeIdAndFinSourceTitleAndPosition(@Param("employeeId") int employeeId,
																				 @Param("financeSourceId") int financeSourceId,
																				 @Param("position") String Position);

	@Query(value = "CALL GetByEmployeeIdAndFinanceSource(:employeeId, :financeSourceId);", nativeQuery = true)
	FinanceOrderWork getByEmployeeIdAndFinanceSourceTitle(@Param("employeeId") int employeeId,
														  @Param("financeSourceId") int financeSourceId);

}
