package eu.virac.dlut.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.AcademicWorkLoad;
import eu.virac.dlut.repos.IAcademicWorkLoadRepo;
import eu.virac.dlut.services.IAcademicWorkLoadService;

@Service
public class AcademicWorkLoadServiceImpl implements IAcademicWorkLoadService {

	@Autowired
	IAcademicWorkLoadRepo academWorkLoadRepo;
	
	
	@Override
	public ArrayList<AcademicWorkLoad> selectAllAcademicWorkLoad() {
		return (ArrayList<AcademicWorkLoad>) academWorkLoadRepo.findAll();
	}


	@Override
	public ArrayList<AcademicWorkLoad> selectAcademicWorkLoadEmpolyee(int year, int month, int employeeId) {
		ArrayList<AcademicWorkLoad> res = academWorkLoadRepo.findByDateAndEmployee(year, month, employeeId);
		if(res == null)
			return new ArrayList<AcademicWorkLoad>();
		return res;
	}


	@Override
	public AcademicWorkLoad selectOneAcademicWorkLoadById(int academicWorkLoadId) throws Exception {
		if (academWorkLoadRepo.existsById(academicWorkLoadId))
			return academWorkLoadRepo.findById(academicWorkLoadId).get();
		else
			throw new Exception("Akadēmiskās slodzes id nav pareizs");
	}
}
