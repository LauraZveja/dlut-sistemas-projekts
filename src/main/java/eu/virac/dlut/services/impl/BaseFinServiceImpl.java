package eu.virac.dlut.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.BaseFin;
import eu.virac.dlut.repos.IBaseFinRepo;
import eu.virac.dlut.services.IBaseFinService;


@Service
public class BaseFinServiceImpl implements IBaseFinService {

	@Autowired
	IBaseFinRepo baseFinRepo;
	
	
	@Override
	public ArrayList<BaseFin> selectAllBaseFin() {
		return (ArrayList<BaseFin>) baseFinRepo.findAll();
	}

//???
	@Override
	public ArrayList<BaseFin> findByDateAndEmployee(int year, int month, int employeeId) {
		ArrayList<BaseFin> res = baseFinRepo.findByDateAndEmployee(year, month, employeeId);
		if(res.size() == 0) {
			return new ArrayList<>();
		}
		return res;
	}

	@Override
	public BaseFin selectOneAcademicWorkLoadById(int baseFinId) throws Exception {
		if (baseFinRepo.existsById(baseFinId))
			return baseFinRepo.findById(baseFinId).get();
		else
			throw new Exception("Bāzes id nav pareizs");
	}
}
