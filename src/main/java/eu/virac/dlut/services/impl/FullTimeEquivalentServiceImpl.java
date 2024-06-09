package eu.virac.dlut.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.Employee;
import eu.virac.dlut.models.FinanceSource;
import eu.virac.dlut.models.FullTimeEquivalent;
import eu.virac.dlut.repos.IEmployeeRepo;
import eu.virac.dlut.repos.IFinanceSourceRepo;
import eu.virac.dlut.repos.IFullTimeEquivalentRepo;
import eu.virac.dlut.services.IFullTimeEquivalentService;

@Service
public class FullTimeEquivalentServiceImpl implements IFullTimeEquivalentService {

	@Autowired
	IFullTimeEquivalentRepo fullTimeEquivalentRepo;
	
	@Autowired
	IEmployeeRepo employeeRepo;
	
	@Autowired
	IFinanceSourceRepo financeSourceRepo;
	@Override
	public boolean updateVacationHoursByYearMonthFinSourceEmployeeFromEditEmployee(int year, int month, int employeeId, int finSourceId,
			double vacationHours) {
		
			FullTimeEquivalent fte = fullTimeEquivalentRepo.findByYearAndMonthAndEmployeeIdEmployeeAndFinanceSourceIdFinSource(year, month, employeeId,
					finSourceId);
			if (fte != null) 
			{
				fte.setVacationHours(vacationHours);
				fullTimeEquivalentRepo.save(fte);
				return true;
			}
			else {
				Employee e = employeeRepo.findById(employeeId).get();
				FinanceSource fs = financeSourceRepo.findById(finSourceId).get();
				FullTimeEquivalent newFte = new FullTimeEquivalent(year, month, e, fs);
				newFte.setVacationHours(vacationHours);
				fullTimeEquivalentRepo.save(newFte);
				return true;
			}
			
		}
		
}
