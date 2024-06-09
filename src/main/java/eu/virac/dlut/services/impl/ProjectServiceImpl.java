package eu.virac.dlut.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.Project;
import eu.virac.dlut.repos.IProjectCharacterRepo;
import eu.virac.dlut.repos.IProjectRepo;
import eu.virac.dlut.services.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService{
	
	@Autowired
	IProjectRepo projectRepo;
	
	@Autowired
	IProjectCharacterRepo projCharRepo;
	
	
	@Override
	public ArrayList<Project> selectAllProjects() {
		return (ArrayList<Project>) projectRepo.findAll();
	}


	@Override
	public ArrayList<Project> selectAllProjectsForOneEmployeeInSpecificMonth(int year, int month,
			int employeeId) {
		ArrayList<Project> allProjectsForOneEmployee = projectRepo.getProjectsForEmployeeForSpecificDate(year, month, employeeId);
		ArrayList<Project> res = new ArrayList<>();
		for (Project p : allProjectsForOneEmployee) {
			if (p.isActive() == true)
				res.add(p);
		}
		return res;
	}

	@Override
	public ArrayList<Project> selectAllActiveProjects() {
		// TODO Auto-generated method stub
		return projectRepo.findByIsActiveTrue();
	}


	@Override
	public Project selectOneProjectById(int projectid) throws Exception {
		if (projectRepo.existsById(projectid))
			return projectRepo.findById(projectid).get();
		else
			throw new Exception("Id nav pareizs");
	}


	@Override
	public String selectProjectCharacter(int projectId) {
		String projCharacter = projCharRepo.findProjectCharacterByProjId(projectId);
		return projCharacter;
	}

}
