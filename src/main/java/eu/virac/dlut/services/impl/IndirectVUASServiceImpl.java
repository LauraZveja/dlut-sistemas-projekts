package eu.virac.dlut.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.AcademicWorkLoad;
import eu.virac.dlut.models.IndirectVUAS;
import eu.virac.dlut.repos.IIndirectVUASRepo;
import eu.virac.dlut.services.IIndirectVUASService;

@Service
public class IndirectVUASServiceImpl implements IIndirectVUASService{
	
	@Autowired
	IIndirectVUASRepo indirectVuasRepo;

	@Override
	public ArrayList<IndirectVUAS> selectAllIndirectVuas() {
		return (ArrayList<IndirectVUAS>) indirectVuasRepo.findAll();
	}

	@Override
	public ArrayList<IndirectVUAS> selectIndirectVuasEmpolyee(int year, int month, int employeeId) {
		ArrayList<IndirectVUAS> res = indirectVuasRepo.findByDateAndEmployee(year, month, employeeId);
		if(res == null)
			return new ArrayList<IndirectVUAS>();
		return res;
	}

	@Override
	public IndirectVUAS selectOneIndirectVuasById(int indirectVuasId) throws Exception {
		if (indirectVuasRepo.existsById(indirectVuasId))
			return indirectVuasRepo.findById(indirectVuasId).get();
		else
			throw new Exception("Netiešo VeA id nav pareizs");
	}

}
