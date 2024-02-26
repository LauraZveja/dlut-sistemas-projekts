package eu.virac.dlut.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.Project;
import org.springframework.data.repository.query.Param;

public interface IProjectRepo extends CrudRepository<Project, Integer> {


	@Query(value = "CALL getFilteredProjects(:yearParam, :monthParam, :employeeIdParam);", nativeQuery = true)
	ArrayList<Project> getProjectsForEmployeeForSpecificDate(@Param("yearParam") int year,
															 @Param("monthParam") int month,
															 @Param("employeeIdParam") int employeeId);

	ArrayList<Project> findByIsActiveTrue();
}


