package eu.virac.dlut.services;

import eu.virac.dlut.models.FinanceSource;

public interface IFinanceSourceService {
	
	FinanceSource selectOneFinanceSourceById(int id) throws Exception;

}
