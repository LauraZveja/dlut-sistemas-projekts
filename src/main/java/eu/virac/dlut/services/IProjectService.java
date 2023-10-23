package eu.virac.dlut.services;

import java.util.ArrayList;

import eu.virac.dlut.models.Project;

public interface IProjectService {

	ArrayList<Project> selectAllProjects();
	
	ArrayList<Project> selectAllProjectsForOneEmployeeInSpecificMonth(int year, int month, int employeeId);
	
	ArrayList<Project> selectAllActiveProjects();
	
	Project selectOneProjectById(int projectid) throws Exception;
	
	String selectProjectCharacter(int projectId);
}
