package eu.virac.dlut.repos;

import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.Department;

public interface IDepartmentRepo extends CrudRepository<Department, Integer>{

}
