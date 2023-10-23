package eu.virac.dlut.services.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.AcademicWorkLoad;
import eu.virac.dlut.models.BaseFin;
import eu.virac.dlut.models.Department;
import eu.virac.dlut.models.Employee;
import eu.virac.dlut.models.EmployeeTimeSheet;
import eu.virac.dlut.models.FinanceOrderWork;
import eu.virac.dlut.models.FinanceSource;
import eu.virac.dlut.models.FullTimeEquivalent;
import eu.virac.dlut.models.IndirectVUAS;
import eu.virac.dlut.models.OrderWork;
import eu.virac.dlut.models.Project;
import eu.virac.dlut.models.ProjectCategory;
import eu.virac.dlut.models.ProjectCharacter;
import eu.virac.dlut.models.helpers.EmployeeAndHourDTO;
import eu.virac.dlut.repos.IAcademicWorkLoadRepo;
import eu.virac.dlut.repos.IBaseFinRepo;
import eu.virac.dlut.repos.IEmployeeTimeSheetRepo;
import eu.virac.dlut.repos.IFinanceOrderWorkRepo;
import eu.virac.dlut.repos.IFullTimeEquivalentRepo;
import eu.virac.dlut.repos.IIndirectVUASRepo;
import eu.virac.dlut.repos.IProjectRepo;
import eu.virac.dlut.services.IAcademicWorkLoadService;
import eu.virac.dlut.services.IBaseFinService;
import eu.virac.dlut.services.IEmployeeService;
import eu.virac.dlut.services.IEmployeeTimeSheetService;
import eu.virac.dlut.services.IFinanceOrderWorkService;
import eu.virac.dlut.services.IHolidayPreholidayTransferedHolidayService;
import eu.virac.dlut.services.IIndirectVUASService;
import eu.virac.dlut.services.IOrderMissionService;
import eu.virac.dlut.services.IOrderVacationService;
import eu.virac.dlut.services.IProjectService;
import eu.virac.dlut.services.ITableExportService;

/**
 * @author elina
 *
 */
@Service
public class TableExportServiceImpl implements ITableExportService {

	@Autowired
	IBaseFinRepo baseFinRepo;

	@Autowired
	IAcademicWorkLoadRepo acWorkLoadRepo;

	@Autowired
	IIndirectVUASRepo indVuasRepo;

	@Autowired
	IEmployeeTimeSheetRepo emplTimeSheetRepo;

	@Autowired
	IFinanceOrderWorkRepo finOrdWorkRepo;

	@Autowired
	IProjectRepo projectRepo;

	@Autowired
	IEmployeeService emplService;

	@Autowired
	IEmployeeTimeSheetService emplTimeSheetService;

	@Autowired
	IFinanceOrderWorkService finOrdWorkService;

	@Autowired
	IProjectService projectService;

	@Autowired
	IAcademicWorkLoadService acadWorkLoadService;

	@Autowired
	IBaseFinService baseFinService;

	@Autowired
	IIndirectVUASService indirectVuasService;

	@Autowired
	IHolidayPreholidayTransferedHolidayService holidayService;

	@Autowired
	IOrderVacationService orderVacationService;
	
	@Autowired
	IFullTimeEquivalentRepo fullTimeEquivalentRepo;
	
	@Autowired
	IOrderMissionService orderMissionService;

	//lai izvēlētos visus aktīvos projektus, visu akadēmisko slodzi, bāzi un netiešās VeA, ko parādīt tabeļu eksportēšanas sākumā.
	@Override
	public ArrayList<FinanceSource> selectAllFinSources() {
		ArrayList<FinanceSource> allFinanceSource = new ArrayList<>();

		ArrayList<Project> allProjects = projectService.selectAllActiveProjects();
		for (Project temp : allProjects) {
			allFinanceSource.add(temp);
		}

		ArrayList<AcademicWorkLoad> allAcadWorkLoad = acadWorkLoadService.selectAllAcademicWorkLoad();
		for (AcademicWorkLoad temp : allAcadWorkLoad) {
			allFinanceSource.add(temp);
		}

		ArrayList<BaseFin> allBaseFin = baseFinService.selectAllBaseFin();
		for (BaseFin temp : allBaseFin) {
			allFinanceSource.add(temp);
		}

		ArrayList<IndirectVUAS> allIndirectVuas = indirectVuasService.selectAllIndirectVuas();
		for (IndirectVUAS temp : allIndirectVuas) {
			allFinanceSource.add(temp);
		}

		return allFinanceSource;
	}

	@Override
	public ArrayList<EmployeeAndHourDTO> selectEmployeesAndHoursForProjectForYearMonth(int projectId, int year,
			int month) {

		ArrayList<EmployeeAndHourDTO> allData = new ArrayList<>();

		ArrayList<Employee> emplAllInProject = emplService.selectAllEmployeesInOneProject(projectId);
		for (Employee eTemp : emplAllInProject) {
			ArrayList<Double> hoursForSpecificEmployee = emplTimeSheetService
					.selectHoursWorkedForEmployeeInProject(year, month, eTemp.getIdEmployee(), projectId);
			String emplPostionInProject = finOrdWorkService.selectPositionInProjectForEmployee(eTemp.getIdEmployee(),
					projectId);
			if (emplPostionInProject == null) {
				emplPostionInProject = "Nav ieraksta";
			}

			Map<Integer, String> hoursInDayForEmployee = uniteDaysOfMonthAndCorrespondingEmployeeHours(year, month,
					eTemp.getIdEmployee(), projectId);
			int sickDaysS = selectSickDaysSCountByDateAndEmployeeAndFinSource(year, month, eTemp.getIdEmployee(),
					projectId);
			int sickDaysSb = selectSickDaysSbCountByDateAndEmployeeAndFinSource(year, month, eTemp.getIdEmployee(),
					projectId);
			int vacationAnnualDays = selectAnnualVacationDaysAICountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), projectId);
			int missionWorkDays = selectMissionWorkKDCountByDateForEmployeeInFinSource(year, month,
					eTemp.getIdEmployee(), projectId);
			int missionEducationDays = selectMissionEducationKMCountByDateForEmployeeInFinSource(year, month,
					eTemp.getIdEmployee(), projectId);
			int parentalLeave = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), projectId); // AA
			int unpaidVacation = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), projectId); // AB
			int vacationEducation = selectVacationEducationDaysAMCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), projectId); // AM
			int vacationExtra = selectVacationExtraDaysAPCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), projectId); // AP
			int vacationCreative = selectVacationCreativeDaysARCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), projectId); // AR
			int voluntaryWork = selectVoluntaryWorkDaysBDCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), projectId); // BD
			int unjustifiedAbsence = selectUnjustifiedAbsenceNCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), projectId); // N

			double payPerHour = finOrdWorkService.selectPayPerHourForSpecificEmployeeInProject(eTemp.getIdEmployee(),
					projectId);
			
			double vacationHours =  selectVacationHoursInFinSourceDateEmployee(year, month, eTemp.getIdEmployee(), projectId);

			EmployeeAndHourDTO helperObject = new EmployeeAndHourDTO(eTemp.getIdEmployee(), eTemp.getName(),
					eTemp.getSurname(), emplPostionInProject, hoursForSpecificEmployee, hoursInDayForEmployee,
					sickDaysS, sickDaysSb, vacationAnnualDays, missionWorkDays, missionEducationDays, parentalLeave,
					unpaidVacation, vacationEducation, vacationExtra, vacationCreative, voluntaryWork,
					unjustifiedAbsence, payPerHour, vacationHours);
			allData.add(helperObject);
		}
		return allData;
	}

	@Override
	public ArrayList<EmployeeAndHourDTO> selectEmployeesAndHoursForBaseFinForYearMonth(int baseFinId, int year,
			int month) {

		ArrayList<EmployeeAndHourDTO> allData = new ArrayList<>();

		ArrayList<Employee> emplAllInBaseFin = emplService.selectAllEmployeesInBaseFin(baseFinId);
		for (Employee eTemp : emplAllInBaseFin) {
			ArrayList<Double> hoursForSpecificEmployee = emplTimeSheetService
					.selectHoursWorkedForEmployeeInBaseFin(year, month, eTemp.getIdEmployee(), baseFinId);

			Map<Integer, String> hoursInDayForEmployee = uniteDaysOfMonthAndCorrespondingEmployeeHours(year, month,
					eTemp.getIdEmployee(), baseFinId);
			int sickDaysS = selectSickDaysSCountByDateAndEmployeeAndFinSource(year, month, eTemp.getIdEmployee(),
					baseFinId);
			int sickDaysSb = selectSickDaysSbCountByDateAndEmployeeAndFinSource(year, month, eTemp.getIdEmployee(),
					baseFinId);
			int vacationAnnualDays = selectAnnualVacationDaysAICountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), baseFinId);
			int missionWorkDays = selectMissionWorkKDCountByDateForEmployeeInFinSource(year, month,
					eTemp.getIdEmployee(), baseFinId);
			int missionEducationDays = selectMissionEducationKMCountByDateForEmployeeInFinSource(year, month,
					eTemp.getIdEmployee(), baseFinId);
			int parentalLeave = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), baseFinId); // AA
			int unpaidVacation = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), baseFinId); // AB
			int vacationEducation = selectVacationEducationDaysAMCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), baseFinId); // AM
			int vacationExtra = selectVacationExtraDaysAPCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), baseFinId); // AP
			int vacationCreative = selectVacationCreativeDaysARCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), baseFinId); // AR
			int voluntaryWork = selectVoluntaryWorkDaysBDCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), baseFinId); // BD
			int unjustifiedAbsence = selectUnjustifiedAbsenceNCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), baseFinId); // N

			double payPerHour = finOrdWorkService.selectPayPerHourForSpecificEmployeeInFinSource(eTemp.getIdEmployee(),
					baseFinId);
			
			double vacationHours =  selectVacationHoursInFinSourceDateEmployee(year, month, eTemp.getIdEmployee(), baseFinId);

			EmployeeAndHourDTO helperObject = new EmployeeAndHourDTO(eTemp.getIdEmployee(), eTemp.getName(),
					eTemp.getSurname(), null, hoursForSpecificEmployee, hoursInDayForEmployee, sickDaysS, sickDaysSb,
					vacationAnnualDays, missionWorkDays, missionEducationDays, parentalLeave, unpaidVacation,
					vacationEducation, vacationExtra, vacationCreative, voluntaryWork, unjustifiedAbsence, payPerHour, vacationHours);
			allData.add(helperObject);
		}
		return allData;
	}

	@Override
	public ArrayList<EmployeeAndHourDTO> selectEmployeesAndHoursForAcademicWorkLoadForYearMonth(int academicWorkLoadId,
			int year, int month) {
		ArrayList<EmployeeAndHourDTO> allData = new ArrayList<>();

		ArrayList<Employee> emplAllInAcademicWorkLoad = emplService
				.selectAllEmployeesInAcademicWorkLoad(academicWorkLoadId);
		for (Employee eTemp : emplAllInAcademicWorkLoad) {
			ArrayList<Double> hoursForSpecificEmployee = emplTimeSheetService
					.selectHoursWorkedForEmployeeInAcademicWorkLoad(year, month, eTemp.getIdEmployee(),
							academicWorkLoadId);

			Map<Integer, String> hoursInDayForEmployee = uniteDaysOfMonthAndCorrespondingEmployeeHours(year, month,
					eTemp.getIdEmployee(), academicWorkLoadId);
			int sickDaysS = selectSickDaysSCountByDateAndEmployeeAndFinSource(year, month, eTemp.getIdEmployee(),
					academicWorkLoadId);
			int sickDaysSb = selectSickDaysSbCountByDateAndEmployeeAndFinSource(year, month, eTemp.getIdEmployee(),
					academicWorkLoadId);
			int vacationAnnualDays = selectAnnualVacationDaysAICountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), academicWorkLoadId);
			int missionWorkDays = selectMissionWorkKDCountByDateForEmployeeInFinSource(year, month,
					eTemp.getIdEmployee(), academicWorkLoadId);
			int missionEducationDays = selectMissionEducationKMCountByDateForEmployeeInFinSource(year, month,
					eTemp.getIdEmployee(), academicWorkLoadId);
			int parentalLeave = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), academicWorkLoadId); // AA
			int unpaidVacation = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), academicWorkLoadId); // AB
			int vacationEducation = selectVacationEducationDaysAMCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), academicWorkLoadId); // AM
			int vacationExtra = selectVacationExtraDaysAPCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), academicWorkLoadId); // AP
			int vacationCreative = selectVacationCreativeDaysARCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), academicWorkLoadId); // AR
			int voluntaryWork = selectVoluntaryWorkDaysBDCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), academicWorkLoadId); // BD
			int unjustifiedAbsence = selectUnjustifiedAbsenceNCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), academicWorkLoadId); // N

			double payPerHour = finOrdWorkService.selectPayPerHourForSpecificEmployeeInFinSource(eTemp.getIdEmployee(),
					academicWorkLoadId);
			
			
			double vacationHours =  selectVacationHoursInFinSourceDateEmployee(year, month, eTemp.getIdEmployee(), academicWorkLoadId);

			EmployeeAndHourDTO helperObject = new EmployeeAndHourDTO(eTemp.getIdEmployee(), eTemp.getName(),
					eTemp.getSurname(), null, hoursForSpecificEmployee, hoursInDayForEmployee, sickDaysS, sickDaysSb,
					vacationAnnualDays, missionWorkDays, missionEducationDays, parentalLeave, unpaidVacation,
					vacationEducation, vacationExtra, vacationCreative, voluntaryWork, unjustifiedAbsence, payPerHour, vacationHours);
			allData.add(helperObject);
		}
		return allData;
	}

	@Override
	public ArrayList<EmployeeAndHourDTO> selectEmployeesAndHoursForIndirectVuasForYearMonth(int indirectVuasId,
			int year, int month) {
		ArrayList<EmployeeAndHourDTO> allData = new ArrayList<>();

		ArrayList<Employee> emplAllInIndirectVuas = emplService.selectAllEmployeesInIndirectVuas(indirectVuasId);
		for (Employee eTemp : emplAllInIndirectVuas) {
			ArrayList<Double> hoursForSpecificEmployee = emplTimeSheetService
					.selectHoursWorkedForEmployeeInIndirectVuas(year, month, eTemp.getIdEmployee(), indirectVuasId);

			Map<Integer, String> hoursInDayForEmployee = uniteDaysOfMonthAndCorrespondingEmployeeHours(year, month,
					eTemp.getIdEmployee(), indirectVuasId);
			int sickDaysS = selectSickDaysSCountByDateAndEmployeeAndFinSource(year, month, eTemp.getIdEmployee(),
					indirectVuasId);
			int sickDaysSb = selectSickDaysSbCountByDateAndEmployeeAndFinSource(year, month, eTemp.getIdEmployee(),
					indirectVuasId);
			int vacationAnnualDays = selectAnnualVacationDaysAICountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), indirectVuasId);
			int missionWorkDays = selectMissionWorkKDCountByDateForEmployeeInFinSource(year, month,
					eTemp.getIdEmployee(), indirectVuasId);
			int missionEducationDays = selectMissionEducationKMCountByDateForEmployeeInFinSource(year, month,
					eTemp.getIdEmployee(), indirectVuasId);
			int parentalLeave = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), indirectVuasId); // AA
			int unpaidVacation = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), indirectVuasId); // AB
			int vacationEducation = selectVacationEducationDaysAMCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), indirectVuasId); // AM
			int vacationExtra = selectVacationExtraDaysAPCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), indirectVuasId); // AP
			int vacationCreative = selectVacationCreativeDaysARCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), indirectVuasId); // AR
			int voluntaryWork = selectVoluntaryWorkDaysBDCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), indirectVuasId); // BD
			int unjustifiedAbsence = selectUnjustifiedAbsenceNCountByDateAndEmployeeAndFinSource(year, month,
					eTemp.getIdEmployee(), indirectVuasId); // N

			double payPerHour = finOrdWorkService.selectPayPerHourForSpecificEmployeeInFinSource(eTemp.getIdEmployee(),
					indirectVuasId);
			
			double vacationHours =  selectVacationHoursInFinSourceDateEmployee(year, month, eTemp.getIdEmployee(), indirectVuasId);

			EmployeeAndHourDTO helperObject = new EmployeeAndHourDTO(eTemp.getIdEmployee(), eTemp.getName(),
					eTemp.getSurname(), null, hoursForSpecificEmployee, hoursInDayForEmployee, sickDaysS, sickDaysSb,
					vacationAnnualDays, missionWorkDays, missionEducationDays, parentalLeave, unpaidVacation,
					vacationEducation, vacationExtra, vacationCreative, voluntaryWork, unjustifiedAbsence, payPerHour, vacationHours);
			allData.add(helperObject);
		}
		return allData;
	}

	@Override
	public int determineDaysInMonth(int year, int month) {
//		int yearInt = parseStringToInteger(year);
//		int monthInt  = parseStringToInteger(month);
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth(); // 28-31
		return daysInMonth;
	}

	
	//vienam darbiniekam visi finansu avoti
	//gads, meenesis, darbinieka id
	//darbinieks - darba riikojums - finance_order_work un tur ari id_project/id_base_fin/id_ac_work_load/id_indirectvuas
	//employee_time_sheet ir id_fin_order_work;
	
	/*
	@Override
	public ArrayList<Project> selectAllProjectsOneEmployeeInYearAndMonth(int year, int month, int employeeId) {
		//ArrayList<FinanceSource> allProjects = new ArrayList<>();
		ArrayList<Project> res = projectService.selectAllProjectsForOneEmployeeInSpecificMonth(year, month, employeeId);
		
//		for(FinanceSource fs : res) 
//			allProjects.add(fs);
		
		return res;
	}
	*/
	
	@Override
	public ArrayList<FinanceSource> selectAllOtherFinanceSourcesForOneEmployeeInSpecificYearAndMonth(int year, int month, int employeeId) {
		ArrayList<FinanceSource> allFinSources = new ArrayList<>();

		ArrayList<Project> proj = projectService.selectAllProjectsForOneEmployeeInSpecificMonth(year, month, employeeId);
		for(FinanceSource fs : proj) 
			allFinSources.add(fs);
			
		ArrayList<BaseFin> allBaseFin = baseFinService.findByDateAndEmployee(year, month, employeeId);
		for(FinanceSource fs : allBaseFin)
			allFinSources.add(fs);
		
		ArrayList<AcademicWorkLoad> allAcademicWorkLoad = acadWorkLoadService.selectAcademicWorkLoadEmpolyee(year, month, employeeId);
		for(FinanceSource fs : allAcademicWorkLoad) 
			allFinSources.add(fs);
		
		ArrayList<IndirectVUAS> allIndirectVuas = indirectVuasService.selectIndirectVuasEmpolyee(year, month, employeeId);
		for(FinanceSource fs : allIndirectVuas)
			allFinSources.add(fs);
		
		return allFinSources;
	}
	
	/*
	@Override
	public ArrayList<EmployeeAndHourDTO> selectDataEmployeeInProjectsInMonthAndYear(int year, int month, int employeeId) throws Exception {
		ArrayList<EmployeeAndHourDTO> results = new ArrayList<>();
		
		ArrayList<Project> list = selectAllProjectsOneEmployeeInYearAndMonth(year, month, employeeId);
		
		for (Project temp : list) {
				EmployeeAndHourDTO oneRes = makeDTOForEmployeeInProjectsInSpecificMonth(year, month, employeeId, temp.getIdFinSource());
				results.add(oneRes);
	}
		return results;
	}
	*/
	
	@Override
	public ArrayList<EmployeeAndHourDTO> selectNecessaryDataForEmployeeInAllOtherFinanceSourcesInOneMonth(int year, int month, int employeeId) throws Exception {
		ArrayList<FinanceSource> finSources = selectAllOtherFinanceSourcesForOneEmployeeInSpecificYearAndMonth(year, month, employeeId);
		
		ArrayList<EmployeeAndHourDTO> results = new ArrayList<>();
		
		for (FinanceSource fsTemp : finSources) {
			if(fsTemp instanceof Project) {
				EmployeeAndHourDTO oneRes = makeDTOForEmployeeInProjectsInSpecificMonth(year, month, employeeId, fsTemp.getIdFinSource());
				results.add(oneRes);
			}
			
			if(fsTemp instanceof BaseFin) {
				EmployeeAndHourDTO oneRes = makeDTOInBaseFinForSpecificMonthForEmployee(year, month, employeeId, fsTemp.getIdFinSource());
				results.add(oneRes);
			}
			
			if(fsTemp instanceof AcademicWorkLoad) {
				EmployeeAndHourDTO oneRes = makeDTOForAcademicWorkLoadForEmpolyeeForDate(year, month, employeeId, fsTemp.getIdFinSource());
				results.add(oneRes);
			}
			
			if(fsTemp instanceof IndirectVUAS) {
				EmployeeAndHourDTO oneRes = makeDTOForEmpolyeeAndIndirectVuasForMonthAndYear(year, month, employeeId, fsTemp.getIdFinSource());
				results.add(oneRes);
			}
		}
		return results;
	}
	
	public EmployeeAndHourDTO makeDTOForEmployeeInProjectsInSpecificMonth(int year, int month, int employeeId, int projectId) throws Exception {
		Employee e = emplService.selectOneEmployeeById(employeeId);
		Project project = projectService.selectOneProjectById(projectId);
		
		ArrayList<Double> hoursForSpecificEmployee = emplTimeSheetService
				.selectHoursWorkedForEmployeeInProject(year, month, employeeId, projectId);
		String emplPostionInProject = finOrdWorkService.selectPositionInProjectForEmployee(employeeId,
				projectId);
		if (emplPostionInProject == null) {
			emplPostionInProject = "Nav ieraksta";
		}

		Map<Integer, String> hoursInDayForEmployee = uniteDaysOfMonthAndCorrespondingEmployeeHours(year, month,
				employeeId, projectId);
		int sickDaysS = selectSickDaysSCountByDateAndEmployeeAndFinSource(year, month, employeeId,
				projectId);
		int sickDaysSb = selectSickDaysSbCountByDateAndEmployeeAndFinSource(year, month, employeeId,
				projectId);
		int vacationAnnualDays = selectAnnualVacationDaysAICountByDateAndEmployeeAndFinSource(year, month,
				employeeId, projectId);
		int missionWorkDays = selectMissionWorkKDCountByDateForEmployeeInFinSource(year, month,
				employeeId, projectId);
		int missionEducationDays = selectMissionEducationKMCountByDateForEmployeeInFinSource(year, month,
				employeeId, projectId);
		int parentalLeave = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
				employeeId, projectId); // AA
		int unpaidVacation = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
				employeeId, projectId); // AB
		int vacationEducation = selectVacationEducationDaysAMCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, projectId); // AM
		int vacationExtra = selectVacationExtraDaysAPCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, projectId); // AP
		int vacationCreative = selectVacationCreativeDaysARCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, projectId); // AR
		int voluntaryWork = selectVoluntaryWorkDaysBDCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, projectId); // BD
		int unjustifiedAbsence = selectUnjustifiedAbsenceNCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, projectId); // N

//		double payPerHour = finOrdWorkService.selectPayPerHourForSpecificEmployeeInProject(employeeId,
//				projectId);
		double payPerHour = finOrdWorkService.selectPayPerHourForSpecificEmployeeInFinSource(employeeId, projectId);
		
		double vacationHours =  selectVacationHoursInFinSourceDateEmployee(year, month, employeeId, projectId);
		
		String projectCharacter = projectService.selectProjectCharacter(projectId);

		EmployeeAndHourDTO helperObject = new EmployeeAndHourDTO(employeeId, e.getName(),
				e.getSurname(), emplPostionInProject, hoursForSpecificEmployee, hoursInDayForEmployee,
				sickDaysS, sickDaysSb, vacationAnnualDays, missionWorkDays, missionEducationDays, parentalLeave,
				unpaidVacation, vacationEducation, vacationExtra, vacationCreative, voluntaryWork,
				unjustifiedAbsence, payPerHour, vacationHours, project.getTitle(), project.getCode(), projectCharacter, projectId);
		
		return helperObject;
	}
	
		
	@Override
	public EmployeeAndHourDTO makeDTOInBaseFinForSpecificMonthForEmployee(int year, int month, int employeeId, int baseFinId) throws Exception {
		Employee e = emplService.selectOneEmployeeById(employeeId);
		BaseFin baseFin = baseFinService.selectOneAcademicWorkLoadById(baseFinId);
		
		ArrayList<Double> hoursForSpecificEmployee = emplTimeSheetService
				.selectHoursWorkedForEmployeeInBaseFin(year, month, employeeId, baseFinId);

		Map<Integer, String> hoursInDayForEmployee = uniteDaysOfMonthAndCorrespondingEmployeeHours(year, month,
				employeeId, baseFinId);
		int sickDaysS = selectSickDaysSCountByDateAndEmployeeAndFinSource(year, month, employeeId,
				baseFinId);
		int sickDaysSb = selectSickDaysSbCountByDateAndEmployeeAndFinSource(year, month, employeeId,
				baseFinId);
		int vacationAnnualDays = selectAnnualVacationDaysAICountByDateAndEmployeeAndFinSource(year, month,
				employeeId, baseFinId);
		int missionWorkDays = selectMissionWorkKDCountByDateForEmployeeInFinSource(year, month,
				employeeId, baseFinId);
		int missionEducationDays = selectMissionEducationKMCountByDateForEmployeeInFinSource(year, month,
				employeeId, baseFinId);
		int parentalLeave = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
				employeeId, baseFinId); // AA
		int unpaidVacation = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
				employeeId, baseFinId); // AB
		int vacationEducation = selectVacationEducationDaysAMCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, baseFinId); // AM
		int vacationExtra = selectVacationExtraDaysAPCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, baseFinId); // AP
		int vacationCreative = selectVacationCreativeDaysARCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, baseFinId); // AR
		int voluntaryWork = selectVoluntaryWorkDaysBDCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, baseFinId); // BD
		int unjustifiedAbsence = selectUnjustifiedAbsenceNCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, baseFinId); // N

		double payPerHour = finOrdWorkService.selectPayPerHourForSpecificEmployeeInFinSource(employeeId,
				baseFinId);
		
		double vacationHours =  selectVacationHoursInFinSourceDateEmployee(year, month, employeeId, baseFinId);

		EmployeeAndHourDTO helperObject = new EmployeeAndHourDTO(employeeId, e.getName(),
				e.getSurname(), null, hoursForSpecificEmployee, hoursInDayForEmployee, sickDaysS, sickDaysSb,
				vacationAnnualDays, missionWorkDays, missionEducationDays, parentalLeave, unpaidVacation,
				vacationEducation, vacationExtra, vacationCreative, voluntaryWork, unjustifiedAbsence, payPerHour, vacationHours, baseFin.getTitle(), baseFin.getCode(), null, baseFinId);
		
		return helperObject;
	}
	
	@Override
	public EmployeeAndHourDTO makeDTOForAcademicWorkLoadForEmpolyeeForDate(int year, int month, int employeeId, int academicWorkLoadId) throws Exception{
		Employee e = emplService.selectOneEmployeeById(employeeId);
		AcademicWorkLoad acWorkLoad = acadWorkLoadService.selectOneAcademicWorkLoadById(academicWorkLoadId);
		
		ArrayList<Double> hoursForSpecificEmployee = emplTimeSheetService
				.selectHoursWorkedForEmployeeInAcademicWorkLoad(year, month, employeeId,
						academicWorkLoadId);

		Map<Integer, String> hoursInDayForEmployee = uniteDaysOfMonthAndCorrespondingEmployeeHours(year, month,
				employeeId, academicWorkLoadId);
		int sickDaysS = selectSickDaysSCountByDateAndEmployeeAndFinSource(year, month, employeeId,
				academicWorkLoadId);
		int sickDaysSb = selectSickDaysSbCountByDateAndEmployeeAndFinSource(year, month, employeeId,
				academicWorkLoadId);
		int vacationAnnualDays = selectAnnualVacationDaysAICountByDateAndEmployeeAndFinSource(year, month,
				employeeId, academicWorkLoadId);
		int missionWorkDays = selectMissionWorkKDCountByDateForEmployeeInFinSource(year, month,
				employeeId, academicWorkLoadId);
		int missionEducationDays = selectMissionEducationKMCountByDateForEmployeeInFinSource(year, month,
				employeeId, academicWorkLoadId);
		int parentalLeave = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
				employeeId, academicWorkLoadId); // AA
		int unpaidVacation = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
				employeeId, academicWorkLoadId); // AB
		int vacationEducation = selectVacationEducationDaysAMCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, academicWorkLoadId); // AM
		int vacationExtra = selectVacationExtraDaysAPCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, academicWorkLoadId); // AP
		int vacationCreative = selectVacationCreativeDaysARCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, academicWorkLoadId); // AR
		int voluntaryWork = selectVoluntaryWorkDaysBDCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, academicWorkLoadId); // BD
		int unjustifiedAbsence = selectUnjustifiedAbsenceNCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, academicWorkLoadId); // N

		double payPerHour = finOrdWorkService.selectPayPerHourForSpecificEmployeeInFinSource(employeeId,
				academicWorkLoadId);
		
		
		double vacationHours =  selectVacationHoursInFinSourceDateEmployee(year, month,employeeId, academicWorkLoadId);

		EmployeeAndHourDTO helperObject = new EmployeeAndHourDTO(employeeId, e.getName(),
				e.getSurname(), null, hoursForSpecificEmployee, hoursInDayForEmployee, sickDaysS, sickDaysSb,
				vacationAnnualDays, missionWorkDays, missionEducationDays, parentalLeave, unpaidVacation,
				vacationEducation, vacationExtra, vacationCreative, voluntaryWork, unjustifiedAbsence, payPerHour, vacationHours, acWorkLoad.getTitle(), acWorkLoad.getCode(), null, academicWorkLoadId);
		
		return helperObject;
	}
	
	@Override
	public EmployeeAndHourDTO makeDTOForEmpolyeeAndIndirectVuasForMonthAndYear(int year, int month, int employeeId, int indirectVuasId) throws Exception {
		Employee e = emplService.selectOneEmployeeById(employeeId);
		IndirectVUAS indVuas = indirectVuasService.selectOneIndirectVuasById(indirectVuasId);
		
		ArrayList<Double> hoursForSpecificEmployee = emplTimeSheetService
				.selectHoursWorkedForEmployeeInIndirectVuas(year, month, employeeId, indirectVuasId);

		Map<Integer, String> hoursInDayForEmployee = uniteDaysOfMonthAndCorrespondingEmployeeHours(year, month,
				employeeId, indirectVuasId);
		int sickDaysS = selectSickDaysSCountByDateAndEmployeeAndFinSource(year, month, employeeId,
				indirectVuasId);
		int sickDaysSb = selectSickDaysSbCountByDateAndEmployeeAndFinSource(year, month, employeeId,
				indirectVuasId);
		int vacationAnnualDays = selectAnnualVacationDaysAICountByDateAndEmployeeAndFinSource(year, month,
				employeeId, indirectVuasId);
		int missionWorkDays = selectMissionWorkKDCountByDateForEmployeeInFinSource(year, month,
				employeeId, indirectVuasId);
		int missionEducationDays = selectMissionEducationKMCountByDateForEmployeeInFinSource(year, month,
				employeeId, indirectVuasId);
		int parentalLeave = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
				employeeId, indirectVuasId); // AA
		int unpaidVacation = selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(year, month,
				employeeId, indirectVuasId); // AB
		int vacationEducation = selectVacationEducationDaysAMCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, indirectVuasId); // AM
		int vacationExtra = selectVacationExtraDaysAPCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, indirectVuasId); // AP
		int vacationCreative = selectVacationCreativeDaysARCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, indirectVuasId); // AR
		int voluntaryWork = selectVoluntaryWorkDaysBDCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, indirectVuasId); // BD
		int unjustifiedAbsence = selectUnjustifiedAbsenceNCountByDateAndEmployeeAndFinSource(year, month,
				employeeId, indirectVuasId); // N

		double payPerHour = finOrdWorkService.selectPayPerHourForSpecificEmployeeInFinSource(employeeId,
				indirectVuasId);
		
		double vacationHours =  selectVacationHoursInFinSourceDateEmployee(year, month, employeeId, indirectVuasId);

		EmployeeAndHourDTO helperObject = new EmployeeAndHourDTO(employeeId, e.getName(),
				e.getSurname(), null, hoursForSpecificEmployee, hoursInDayForEmployee, sickDaysS, sickDaysSb,
				vacationAnnualDays, missionWorkDays, missionEducationDays, parentalLeave, unpaidVacation,
				vacationEducation, vacationExtra, vacationCreative, voluntaryWork, unjustifiedAbsence, payPerHour, vacationHours, indVuas.getTitle(), indVuas.getCode(), null, indirectVuasId);
		return helperObject;
	}


	@Override
	public Map<Integer, String> uniteDaysOfMonthAndCorrespondingEmployeeHours(int year, int month, int employeeId,
			int finSourceId) {

		Map<Integer, String> daysWithHours = new LinkedHashMap<Integer, String>();
		int daysInMonth = determineDaysInMonth(year, month);
		for (int i = 1; i <= daysInMonth; i++) {
			LocalDate dtemp = LocalDate.of(year, month, i);

			ArrayList<Double> hours = emplTimeSheetRepo.getEmployeeHoursWorkedOnSpecificDay(year, month, i, employeeId,
					finSourceId);
			if (hours.size() == 0) {
				daysWithHours.put(i, "0");
			} else {
				if (hours.size() > 1) {
					double hSum = 0.000;
					for (double hTemp : hours) {
						hSum += hTemp;
					}
					daysWithHours.put(i, String.valueOf(hSum));
				} else
					daysWithHours.put(i, String.valueOf(hours.get(0)));
			}
			if (((dtemp.getDayOfWeek() == DayOfWeek.SATURDAY || dtemp.getDayOfWeek() == DayOfWeek.SUNDAY) && (daysWithHours.get(i) == "0" || daysWithHours.get(i) == "0.0" || daysWithHours.get(i) == "0.000"))
					|| holidayService.isTransferedHoliday(i, month, year)
					|| emplTimeSheetService.isDayRecuperation(year, daysInMonth, employeeId, finSourceId))
				daysWithHours.put(i, "BR");
			if (orderVacationService.isAnnualVacationDay(i, month, year, employeeId))
				daysWithHours.put(i, "AI");
			if (orderMissionService.isWorkMissionDay(i, month, year, employeeId))
				daysWithHours.put(i, "KD");
			if(emplTimeSheetService.isSickDayS(i, month, year, employeeId, finSourceId))
				daysWithHours.put(i, "S");
			if (holidayService.isHoliday(i, month, year))
				daysWithHours.put(i, "SV");
		}
			
		return daysWithHours;
	}

	@Override
	public int selectSickDaysSCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId, int finSourceId) {
		ArrayList<EmployeeTimeSheet> listOfEntries = emplTimeSheetRepo
				.getByYearAndMonthAndSickLeaveSForEmployeeFinSource(year, month, employeeId, finSourceId);
		int countSickLeaveS = 0;
		for (EmployeeTimeSheet temp : listOfEntries) {
			if (temp.getSickLeave().contentEquals("S"))
				countSickLeaveS++;
		}
		return countSickLeaveS;
	}

	@Override
	public int selectSickDaysSbCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId) {
		ArrayList<EmployeeTimeSheet> listOfEntries = emplTimeSheetRepo
				.getByYearAndMonthAndSickLeaveSbForEmployeeFinSource(year, month, employeeId, finSourceId);
		int countSickLeaveS = 0;
		for (EmployeeTimeSheet temp : listOfEntries) {
			if (temp.getSickLeave().contentEquals("Sb"))
				countSickLeaveS++;
		}
		return countSickLeaveS;
	}

	@Override
	public int selectMissionWorkKDCountByDateForEmployeeInFinSource(int year, int month, int employeeId,
			int finSourceId) {
		ArrayList<EmployeeTimeSheet> listOfEntries = emplTimeSheetRepo
				.getByYearAndMonthAndMissionEmployeeFinSource(year, month, employeeId, finSourceId);
		int countMissionWorkKD = 0;
		for (EmployeeTimeSheet temp : listOfEntries) {
			if (temp.getOrdMission().getDesignation().contentEquals("KD"))
				countMissionWorkKD++;
		}
		return countMissionWorkKD;
	}

	@Override
	public int selectMissionEducationKMCountByDateForEmployeeInFinSource(int year, int month, int employeeId,
			int finSourceId) {
		ArrayList<EmployeeTimeSheet> listOfEntries = emplTimeSheetRepo
				.getByYearAndMonthAndMissionEmployeeFinSource(year, month, employeeId, finSourceId);
		int countMissionEducationKM = 0;
		for (EmployeeTimeSheet temp : listOfEntries) {
			if (temp.getOrdMission().getDesignation().contentEquals("KM"))
				countMissionEducationKM++;
		}
		return countMissionEducationKM;
	}

	
	/*
	 * Atlasa, cik dienas ir ikgadējais atbaļinājums. 
	 * No EmployeeTimeSheet tabulas atlasa ierakstus, kas atbilst datumam.
	 * Tālāk atlasa ierakstus, lai finansējuma avots nav null 
	 * un lai finansējuma avota id atbilstu finansējumaAvota id, kas ir parametrs.
	 */
	@Override
	public int selectAnnualVacationDaysAICountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId) {
		ArrayList<EmployeeTimeSheet> listOfEntries = emplTimeSheetRepo
				.getByYearAndMonthAndOrderVacationForEmployeeFinSource(year, month);

		int countAnnualVacationAI = 0;
		for (EmployeeTimeSheet temp : listOfEntries) {
			if ((temp.getFinOrdWork().getAcWorkLoad() != null && temp.getFinOrdWork().getAcWorkLoad().getIdFinSource() == finSourceId)
					|| (temp.getFinOrdWork().getBaseFin() != null && temp.getFinOrdWork().getBaseFin().getIdFinSource() == finSourceId)
					|| (temp.getFinOrdWork().getIndVUAS() != null &&  temp.getFinOrdWork().getIndVUAS().getIdFinSource() == finSourceId)
					|| (temp.getFinOrdWork().getProject() != null && temp.getFinOrdWork().getProject().getIdFinSource() == finSourceId)){
				if ((temp.getFinOrdWork().getOrdWork().getEmployee().getIdEmployee() == employeeId)
						&& temp.getOrdVacation().getDesignation().contentEquals("AI")) {
					countAnnualVacationAI++;
				}
			}
		}
		return countAnnualVacationAI;
	}

	@Override
	public int selectParentalLeaveDaysAACountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId) {
		ArrayList<EmployeeTimeSheet> listOfEntries = emplTimeSheetRepo.getByYearAndMonthAndOrderOther(year, month);
		int countParentalLeaveAA = 0;
		for (EmployeeTimeSheet temp : listOfEntries) {
			if (temp.getFinOrdWork().getProject().getIdFinSource() == finSourceId
					&& temp.getFinOrdWork().getOrdWork().getEmployee().getIdEmployee() == employeeId
					&& temp.getOrdOther().getDesignation().contentEquals("AA"))
				countParentalLeaveAA++;
		}
		return countParentalLeaveAA;
	}

	@Override
	public int selectUnpaidVacationDaysABCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId) {
		ArrayList<EmployeeTimeSheet> listOfEntries = emplTimeSheetRepo
				.getByYearAndMonthAndOrderVacationForEmployeeFinSource(year, month);
		int countUnpaidVacationAB = 0;
		for (EmployeeTimeSheet temp : listOfEntries) {
			if (temp.getFinOrdWork().getProject().getIdFinSource() == finSourceId
					&& temp.getFinOrdWork().getOrdWork().getEmployee().getIdEmployee() == employeeId
					&& temp.getOrdVacation().getDesignation().contentEquals("AB"))
				countUnpaidVacationAB++;
		}
		return countUnpaidVacationAB;
	}

	@Override
	public int selectVacationEducationDaysAMCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId) {
		ArrayList<EmployeeTimeSheet> listOfEntries = emplTimeSheetRepo.getByYearAndMonthAndOrderOther(year, month);
		int countVacationEducationAM = 0;
		for (EmployeeTimeSheet temp : listOfEntries) {
			if (temp.getFinOrdWork().getProject().getIdFinSource() == finSourceId
					&& temp.getFinOrdWork().getOrdWork().getEmployee().getIdEmployee() == employeeId
					&& temp.getOrdOther().getDesignation().contentEquals("AM"))
				countVacationEducationAM++;
		}
		return countVacationEducationAM;
	}

	@Override
	public int selectVacationExtraDaysAPCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId) {
		ArrayList<EmployeeTimeSheet> listOfEntries = emplTimeSheetRepo.getByYearAndMonthAndOrderOther(year, month);
		int countVacationExtraAP = 0;
		for (EmployeeTimeSheet temp : listOfEntries) {
			if (temp.getFinOrdWork().getProject().getIdFinSource() == finSourceId
					&& temp.getFinOrdWork().getOrdWork().getEmployee().getIdEmployee() == employeeId
					&& temp.getOrdOther().getDesignation().contentEquals("AP"))
				countVacationExtraAP++;
		}
		return countVacationExtraAP;
	}

	@Override
	public int selectVacationCreativeDaysARCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId) {
		ArrayList<EmployeeTimeSheet> listOfEntries = emplTimeSheetRepo.getByYearAndMonthAndOrderOther(year, month);
		int countVacationCreativeAR = 0;
		for (EmployeeTimeSheet temp : listOfEntries) {
			if (temp.getFinOrdWork().getProject().getIdFinSource() == finSourceId
					&& temp.getFinOrdWork().getOrdWork().getEmployee().getIdEmployee() == employeeId
					&& temp.getOrdOther().getDesignation().contentEquals("AR"))
				countVacationCreativeAR++;
		}
		return countVacationCreativeAR;
	}

	@Override
	public int selectVoluntaryWorkDaysBDCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId) {
		ArrayList<EmployeeTimeSheet> listOfEntries = emplTimeSheetRepo.getByYearAndMonthAndOrderOther(year, month);
		int countVoluntaryWorkBD = 0;
		for (EmployeeTimeSheet temp : listOfEntries) {
			if (temp.getFinOrdWork().getProject().getIdFinSource() == finSourceId
					&& temp.getFinOrdWork().getOrdWork().getEmployee().getIdEmployee() == employeeId
					&& temp.getOrdOther().getDesignation().contentEquals("BD"))
				countVoluntaryWorkBD++;
		}
		return countVoluntaryWorkBD;
	}

	@Override
	public int selectUnjustifiedAbsenceNCountByDateAndEmployeeAndFinSource(int year, int month, int employeeId,
			int finSourceId) {
		ArrayList<EmployeeTimeSheet> listOfEntries = emplTimeSheetRepo
				.getByYearAndMonthAndUnjustifAbsenceForEmployeeInFinSource(year, month, employeeId, finSourceId);
		int unjustAbsenceNCount = 0;
		for (EmployeeTimeSheet temp : listOfEntries) {
			if (temp.getRemarks().contentEquals("N"))
				unjustAbsenceNCount++;
		}
		return unjustAbsenceNCount;
	}

	@Override
	public boolean areSickDaysSZero(ArrayList<EmployeeAndHourDTO> selectedResults) {
		for (EmployeeAndHourDTO temp : selectedResults) {
			if (temp.getSickDaysS() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean areSickSbZero(ArrayList<EmployeeAndHourDTO> selectedResults) {
		for (EmployeeAndHourDTO temp : selectedResults) {
			if (temp.getSickDaysSb() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean areAnnualVacationAIZero(ArrayList<EmployeeAndHourDTO> selectedResults) {
		for (EmployeeAndHourDTO temp : selectedResults) {
			if (temp.getVacationAnnualDays() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean areUnpaidVacationZero(ArrayList<EmployeeAndHourDTO> selectedResults) {
		for (EmployeeAndHourDTO temp : selectedResults) {
			if (temp.getUnpaidVacation() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean areParentalLeaveDaysZero(ArrayList<EmployeeAndHourDTO> selectedResults) {
		for (EmployeeAndHourDTO temp : selectedResults) {
			if (temp.getParentalLeave() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean areVacationEducationDaysForAllZero(ArrayList<EmployeeAndHourDTO> selectedResults) {
		for (EmployeeAndHourDTO temp : selectedResults) {
			if (temp.getVacationEducation() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean areExtraVacationDaysAllZero(ArrayList<EmployeeAndHourDTO> selectedResults) {
		for (EmployeeAndHourDTO temp : selectedResults) {
			if (temp.getVacationExtra() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean areCreativeVacationDaysForAllZero(ArrayList<EmployeeAndHourDTO> selectedResults) {
		for (EmployeeAndHourDTO temp : selectedResults) {
			if (temp.getVacationCreative() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean areVolunatryWorkAllDaysZero(ArrayList<EmployeeAndHourDTO> selectedResults) {
		for (EmployeeAndHourDTO temp : selectedResults) {
			if (temp.getVoluntaryWork() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean areWorkMissionDaysZero(ArrayList<EmployeeAndHourDTO> selectedResults) {
		for (EmployeeAndHourDTO temp : selectedResults) {
			if (temp.getMissionWorkDays() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean areDaysOfMissionEducationZero(ArrayList<EmployeeAndHourDTO> selectedResults) {
		for (EmployeeAndHourDTO temp : selectedResults) {
			if (temp.getMissionEducationDays() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean areUnjustifiedAbsenceDaysZero(ArrayList<EmployeeAndHourDTO> selectedResults) {
		for (EmployeeAndHourDTO temp : selectedResults) {
			if (temp.getUnjustifiedAbsence() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public double selectVacationHoursInFinSourceDateEmployee(int year, int month, int employeeId, int finSourceId) {
		double resDouble = 0;
		FullTimeEquivalent res = fullTimeEquivalentRepo.findByYearAndMonthAndEmployeeIdEmployeeAndFinanceSourceIdFinSource(year, month, employeeId, finSourceId);
		if(res == null)
			return resDouble; 
		else
			return res.getVacationHours();
		
	}
	
	
	
	/*
	 * Tiek veidota Map datu struktura, kura apkopo visas viena darbinieka nostradatas stundas viena menesi
	 */
	@Override
	public Map<Integer, Double> allHoursOneEmployeeOnDate(int year, int month, int employeeId) {
		Map<Integer, Double> dayAndHours= new LinkedHashMap<Integer, Double>();
		
		int daysInMonth = determineDaysInMonth(year, month);
		for (int i = 1; i <= daysInMonth; i++) {
			LocalDate dtemp = LocalDate.of(year, month, i);
			ArrayList<Double> hours = emplTimeSheetRepo.getWorkedHoursOneEmployeeOnSpecificDate(year, month, i,
					employeeId);
			if (hours.size() == 0) 
				dayAndHours.put(i, 0.0);
			else {
				Double sumHoursOnDay = 0.0;
				for (Double d : hours) {
					sumHoursOnDay +=d;
				}
				dayAndHours.put(i, sumHoursOnDay);
		}
		
	}
		return dayAndHours;
	}
	
//	@Override
//	public int countAnnulVacationDaysInAllFinSources(ArrayList<EmployeeAndHourDTO> resList) {
//		int countAIDays = 0;
//		for (EmployeeAndHourDTO temp : resList) {
//				countAIDays += temp.getVacationAnnualDays();
//		}
//		return countAIDays;
//	}
	
	@Override
	public double sumAllHoursWorked(Map<Integer, Double> resMap) {
		double sumAllHoursWorked = resMap.values().stream().mapToDouble(d -> d).sum();

		return sumAllHoursWorked;
	}
	
//	@Override
//	public int sumAllDaysWorked(ArrayList<EmployeeAndHourDTO> resList) {
//		int sumAllDaysWorked = 0;
//		for (EmployeeAndHourDTO temp : resList) {
//			sumAllDaysWorked += temp.getHours().size();
//	}
//		return sumAllDaysWorked;
//	}
	

}
