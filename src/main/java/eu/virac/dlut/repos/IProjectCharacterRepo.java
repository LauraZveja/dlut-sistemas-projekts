package eu.virac.dlut.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.ProjectCharacter;
import org.springframework.data.repository.query.Param;

public interface IProjectCharacterRepo extends CrudRepository<ProjectCharacter, Integer> {

    @Query(value = "CALL FindProjectCharacterByProjId(:projectId);", nativeQuery = true)
    String findProjectCharacterByProjId(@Param("projectId") int projectId);


}
