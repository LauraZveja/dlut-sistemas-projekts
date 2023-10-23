package eu.virac.dlut.services;

import java.util.ArrayList;

import eu.virac.dlut.models.AcademicWorkLoad;


public interface IAcademicWorkLoadService {

	ArrayList<AcademicWorkLoad> selectAllAcademicWorkLoad();

	ArrayList<AcademicWorkLoad> selectAcademicWorkLoadEmpolyee(int year, int month, int employeeId);

	AcademicWorkLoad selectOneAcademicWorkLoadById(int academicWorkLoadId) throws Exception;
}
