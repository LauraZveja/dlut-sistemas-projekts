package eu.virac.dlut.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.ProjectCharacter;

public interface IProjectCharacterRepo extends CrudRepository<ProjectCharacter, Integer> {

	@Query(value = "SELECT pch.proj_char_title FROM project_character pch "
			+ "JOIN project p ON p.id_proj_char=pch.id_proj_char "
			+ "WHERE p.id_fin_source = ?1 ", nativeQuery = true)
	String findProjectCharacterByProjId(int projectId);

}
