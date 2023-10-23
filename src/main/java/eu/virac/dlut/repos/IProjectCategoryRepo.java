package eu.virac.dlut.repos;

import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.ProjectCategory;

public interface IProjectCategoryRepo extends CrudRepository<ProjectCategory, Integer> {

}
