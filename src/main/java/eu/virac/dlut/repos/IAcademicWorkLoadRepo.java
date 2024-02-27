package eu.virac.dlut.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.AcademicWorkLoad;
import org.springframework.data.repository.query.Param;

public interface IAcademicWorkLoadRepo extends CrudRepository<AcademicWorkLoad, Integer> {

	@Query(value = "CALL getAcademicWorkLoadForEmployee(:yearParam, :monthParam, :employeeIdParam);", nativeQuery = true)
	ArrayList<AcademicWorkLoad> findByDateAndEmployee(@Param("yearParam") int year,
													  @Param("monthParam") int month,
													  @Param("employeeIdParam") int employeeId);

}
