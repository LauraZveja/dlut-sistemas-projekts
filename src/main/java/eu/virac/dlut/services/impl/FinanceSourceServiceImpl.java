package eu.virac.dlut.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.FinanceSource;
import eu.virac.dlut.repos.IFinanceSourceRepo;
import eu.virac.dlut.services.IFinanceSourceService;

@Service
public class FinanceSourceServiceImpl implements IFinanceSourceService{

	@Autowired
	IFinanceSourceRepo financeSourceRepo;
	
	@Override
	public FinanceSource selectOneFinanceSourceById(int id) throws Exception {
			if (financeSourceRepo.existsById(id))
				return financeSourceRepo.findById(id).get();
			else
				throw new Exception("Id nav pareizs");
		}

}
