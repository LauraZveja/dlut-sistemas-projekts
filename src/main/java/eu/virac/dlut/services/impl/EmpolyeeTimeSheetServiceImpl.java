package eu.virac.dlut.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.EmployeeTimeSheet;
import eu.virac.dlut.models.FinanceOrderWork;
import eu.virac.dlut.repos.IEmployeeRepo;
import eu.virac.dlut.repos.IEmployeeTimeSheetRepo;
import eu.virac.dlut.repos.IFinanceOrderWorkRepo;
import eu.virac.dlut.services.IEmployeeTimeSheetService;

@Service
public class EmpolyeeTimeSheetServiceImpl implements IEmployeeTimeSheetService {

	@Autowired
	IEmployeeTimeSheetRepo emplTimeSheetRepo;
	
	@Autowired
	IFinanceOrderWorkRepo financeOrderWorkRepo;

	@Override
	public ArrayList<Double> selectHoursWorkedForEmployeeInProject(int year, int month, int employeeId, int projectId) {
		ArrayList<Double> res = emplTimeSheetRepo.getHoursWorkedForEmployeeInProject(year, month, employeeId,
				projectId);
		return res;
	}

	@Override
	public ArrayList<String> selectYearsFromTimeSheets() {
		ArrayList<String> res = emplTimeSheetRepo.getDistinctYearsFromTimeSheet();
		return res;
	}

	@Override
	public EmployeeTimeSheet selectEmployeeTimeSheetByFinaceOrderWorkId(int finOrdWorkId) {
		EmployeeTimeSheet res = emplTimeSheetRepo.findByFinOrdWorkIdFinOrdWork(finOrdWorkId);
		return res;
	}

	@Override
	// public Float selectHoursWorkedForEmployeeOnSpecificDay(String year, String
	// month, String day,
	public ArrayList<Double> selectHoursWorkedForEmployeeOnSpecificDay(String year, String month, String day,
			int employeeId, int projectId) {
		// Float res = emplTimeSheetRepo.getHoursWorkedOnSpecificDayForEmployee(year,
		// month, day, employeeId, projectId);
		ArrayList<Double> res = emplTimeSheetRepo.getHoursWorkedOnSpecificDayForEmployeeProject(year, month, day,
				employeeId, projectId);
		return res;
	}

	@Override
	public int selectDaysForEmployeeWithSickLeaveInProject(String year, String month, int employeeId, int projectId) {
		int res = emplTimeSheetRepo.getSickDaysByDateAndEmployeeAndProject(year, month, employeeId, projectId);
		return res;
	}

	@Override
	public int selectVacationDaysInProjectForOneEmployee(String year, String month, int employeeId, int projectId) {
		int res = emplTimeSheetRepo.getVacationTimeBySpecificDateAndEmployeeAndProject(year, month, employeeId,
				projectId);
		return res;
	}

	@Override
	public ArrayList<Double> selectHoursWorkedForEmployeeInBaseFin(int year, int month, int idEmployee, int baseFinId) {
		ArrayList<Double> res = emplTimeSheetRepo.getHoursWorkedForEmployeeInBaseFin(year, month, idEmployee,
				baseFinId);
		return res;
	}

	@Override
	public ArrayList<Double> selectHoursWorkedForEmployeeInAcademicWorkLoad(int year, int month, int idEmployee,
			int academicWorkLoadId) {
		ArrayList<Double> res = emplTimeSheetRepo.getHoursWorkedForEmployeeInacademicWorkLoad(year, month, idEmployee,
				academicWorkLoadId);
		return res;
	}

	@Override
	public ArrayList<Double> selectHoursWorkedForEmployeeInIndirectVuas(int year, int month, int idEmployee,
			int indirectVuasId) {
		ArrayList<Double> res = emplTimeSheetRepo.getHoursWorkedForEmployeeInIndirectVuas(year, month, idEmployee,
				indirectVuasId);
		return res;
	}

	//pārbaude vai diena ir ieraksīta kā atpūtas diena
	@Override
	public boolean isDayRecuperation(int year, int month, int idEmployee, int finSourceId) {
		EmployeeTimeSheet ets = emplTimeSheetRepo.findByDateAndRemarkRecuperation(year, month, idEmployee, finSourceId);
		if (ets != null)
			return true;

		return false;
	}

	@Override
	public void updateEntry(int year, int month, int day, int idEmployee, int finSourceId, double hoursDouble) {
		EmployeeTimeSheet ets = emplTimeSheetRepo.getAllByYearAndMonthAndDayForEmployeeInFinSource(year, month, day, idEmployee, finSourceId);
		ets.setHoursWorkedDayInFinSource(hoursDouble);
		emplTimeSheetRepo.save(ets);
	
	}
	
	@Override
	public void saveNewFromEditEmployeeTable(int year, int month, int day, int employeeId, int financeSourceId,
			double hoursDouble, String position) {
		FinanceOrderWork fow;
		if (!position.isEmpty()) {
			fow = financeOrderWorkRepo.getFinanceOrderWorkByEmployeeIdAndFinSourceTitleAndPosition(employeeId,
					financeSourceId, position);
		} else
			fow = financeOrderWorkRepo.getByEmployeeIdAndFinanceSourceTitle(employeeId, financeSourceId);

		EmployeeTimeSheet etsNew = new EmployeeTimeSheet();
		etsNew.setYearMonthDay(LocalDate.of(year, month, day));
		etsNew.setFinOrdWork(fow);
		etsNew.setHoursWorkedDayInFinSource(hoursDouble);
		etsNew.setActivities(null);
		etsNew.setSickLeave(null);
		etsNew.setEditableForEmployee(false);

		emplTimeSheetRepo.save(etsNew);

	}
	
		
	//pārbaude vai diena ir ieraksīta kā slimības dienas S
	@Override
	public boolean isSickDayS(int day, int month, int year, int employeeId, int finSourceId) {
		EmployeeTimeSheet ets = emplTimeSheetRepo.findByDateAndSickLeaveS(day, month, year, employeeId, finSourceId);
		if (ets != null)
			return true;

		return false;
	}


}
