package eu.virac.dlut.services;

import java.util.ArrayList;

import eu.virac.dlut.models.BaseFin;

public interface IBaseFinService {

	ArrayList<BaseFin> selectAllBaseFin();

	ArrayList<BaseFin> findByDateAndEmployee(int year, int month, int employeeId);

	BaseFin selectOneAcademicWorkLoadById(int baseFinId) throws Exception;
	
}
