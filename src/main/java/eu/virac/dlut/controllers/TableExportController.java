package eu.virac.dlut.controllers;

import java.util.ArrayList;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.virac.dlut.models.AcademicWorkLoad;
import eu.virac.dlut.models.BaseFin;
import eu.virac.dlut.models.Employee;
import eu.virac.dlut.models.FinanceSource;
import eu.virac.dlut.models.FullTimeEquivalent;
import eu.virac.dlut.models.HoursInMonth;
import eu.virac.dlut.models.Project;
import eu.virac.dlut.models.helpers.EmployeeAndHourDTO;
import eu.virac.dlut.repos.IEmployeeTimeSheetRepo;
import eu.virac.dlut.services.IEmployeeService;
import eu.virac.dlut.services.IEmployeeTimeSheetService;
import eu.virac.dlut.services.IFinanceOrderWorkService;
import eu.virac.dlut.services.IFinanceSourceService;
import eu.virac.dlut.services.IHoursInMonthService;
import eu.virac.dlut.services.IProjectService;
import eu.virac.dlut.services.ITableExportService;

@Controller
public class TableExportController {

	@Autowired
	ITableExportService tableExportService;

	@Autowired
	IEmployeeTimeSheetService emplTimeSheetService;

	@Autowired
	IProjectService projectService;

	@Autowired
	IEmployeeService employeeService;

	@Autowired
	IEmployeeTimeSheetRepo emplTimeSheetRepo;

	@Autowired
	IFinanceOrderWorkService finOrdWorkService;

	@Autowired
	IFinanceSourceService financeSourceService;

	@Autowired
	IHoursInMonthService hoursInMonthService;

	@GetMapping("dlut/tabele/eksportet/darbinieks-finansejuma-avots/izvele")
	public String getTableExportEmployeeAndFinanceSource(FinanceSource finSource, Employee employee, Model model) {

		ArrayList<String> yearOptions = emplTimeSheetService.selectYearsFromTimeSheets();
		String[] monthOptions = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		model.addAttribute("years", yearOptions);
		model.addAttribute("month", monthOptions);
		model.addAttribute("finSources", tableExportService.selectAllFinSources());
		model.addAttribute("employees", employeeService.selectAllEmployees());

		return "table-export-employee-finance-source";
	}

	@PostMapping("dlut/tabele/eksportet/darbinieks-finansejuma-avots/izvele")
	public String postTableExportEmployeeAndFinanceSource(@RequestParam(value = "year") int year,
			@RequestParam(value = "month") int month, @RequestParam(value = "finSource") int finSourceId,
			@RequestParam(value = "employee") int employeeId, @Valid EmployeeAndHourDTO emplHourDTO,
			BindingResult res) {

		return "redirect:" + year + "/" + month + "/" + employeeId + "/" + finSourceId;
	}

	@GetMapping("dlut/tabele/eksportet/darbinieks-finansejuma-avots/{year}/{month}/{idempl}/{idfs}")
	public String getTableExportEmployeeAndFinanceSourceResults(@PathVariable("year") int year,
			@PathVariable("month") int month, @PathVariable("idempl") int employeeId,
			@PathVariable("idfs") int finSourceId, Model model) {

		double workHoursThisMonth = 0.0;
		try {
			HoursInMonth h = hoursInMonthService.selectHoursInMonthByYearAndMonth(year, month);
			workHoursThisMonth = h.getHoursInMonth();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// mēneša attēlojumam virsrakstā
		Map<Integer, String> monthNumberAndName = new HashedMap<>();
		monthNumberAndName.put(1, "janvāris");
		monthNumberAndName.put(2, "februāris");
		monthNumberAndName.put(3, "marts");
		monthNumberAndName.put(4, "aprīlis");
		monthNumberAndName.put(5, "maijs");
		monthNumberAndName.put(6, "jūnijs");
		monthNumberAndName.put(7, "jūlijs");
		monthNumberAndName.put(8, "augusts");
		monthNumberAndName.put(9, "septembris");
		monthNumberAndName.put(10, "oktobris");
		monthNumberAndName.put(11, "novembris");
		monthNumberAndName.put(12, "decembris");
		
		EmployeeAndHourDTO emplData = new EmployeeAndHourDTO();

		try {

			Employee employee = employeeService.selectOneEmployeeById(employeeId);
			FinanceSource financeSource = financeSourceService.selectOneFinanceSourceById(finSourceId);

			if (financeSource instanceof Project)
				emplData = tableExportService.makeDTOForEmployeeInProjectsInSpecificMonth(year, month, employeeId, finSourceId);
			else if (financeSource instanceof BaseFin)
				emplData = tableExportService.makeDTOInBaseFinForSpecificMonthForEmployee(year, month, employeeId, finSourceId);
			else if (financeSource instanceof AcademicWorkLoad)
				emplData = tableExportService.makeDTOForAcademicWorkLoadForEmpolyeeForDate(year, month, employeeId, finSourceId);
			else
				emplData = tableExportService.makeDTOForEmpolyeeAndIndirectVuasForMonthAndYear(year, month, employeeId, finSourceId);
			
			if (emplData != null) {
			
			model.addAttribute("empl", employee);
			model.addAttribute("finSource", financeSource);
			model.addAttribute("year", year);
			model.addAttribute("month", monthNumberAndName.get(month));
			model.addAttribute("monthNumber", month);
			model.addAttribute("emplData", emplData);
			model.addAttribute("workHoursInMonth", workHoursThisMonth);
			
			if (emplData.getSickDaysS() != 0)
				model.addAttribute("s", false);
			
			if (emplData.getSickDaysSb() != 0)
				model.addAttribute("sb", false);
			
			if (emplData.getVacationAnnualDays() != 0)
				model.addAttribute("ai", false);

			if (emplData.getUnpaidVacation() != 0)
				model.addAttribute("ab", false);

			if (emplData.getParentalLeave() != 0)
				model.addAttribute("aa", false);

			if (emplData.getVacationEducation() != 0)
				model.addAttribute("am", false);

			if (emplData.getVacationExtra() != 0)
				model.addAttribute("ap", false);

			if (emplData.getVacationCreative() != 0)
				model.addAttribute("ar", false);

			if (emplData.getVoluntaryWork() != 0)
				model.addAttribute("bd", false);
			
			if (emplData.getMissionWorkDays() != 0)
				model.addAttribute("kd", false);
			
			if (emplData.getMissionEducationDays() != 0)
				model.addAttribute("km", false);
			
			if (emplData.getUnjustifiedAbsence() != 0)
				model.addAttribute("n", false);
		

			return "table-export-employee-finance-source-results";
			} else {
				return "no-results";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "no-results";
		}

	}
		
		
//		if(finSourceId==-1 && employeeId==-1 || finSourceId!=-1 && employeeId!=-1) {
//			return "no-results";
//		}else if(finSourceId!=-1 && employeeId==-1) { //search for finance source
	/*		ArrayList<EmployeeAndHourDTO> list = new ArrayList<>();

			FinanceSource finSource = financeSourceService.selectOneFinanceSourceById(finSourceId);

			if (finSource instanceof Project)
				list = tableExportService.selectEmployeesAndHoursForProjectForYearMonth(finSource.getIdFinSource(),
						year, month);
			else if (finSource instanceof BaseFin)
				list = tableExportService.selectEmployeesAndHoursForBaseFinForYearMonth(finSource.getIdFinSource(),
						year, month);
			else if (finSource instanceof AcademicWorkLoad)
				list = tableExportService.selectEmployeesAndHoursForAcademicWorkLoadForYearMonth(
						finSource.getIdFinSource(), year, month);
			else
				list = tableExportService.selectEmployeesAndHoursForIndirectVuasForYearMonth(finSource.getIdFinSource(),
						year, month);
			
			int numberOfDaysInMonth = tableExportService.determineDaysInMonth(year, month);

			boolean areSDaysAllZero = tableExportService.areSickDaysSZero(list);
			boolean areSbAllZero = tableExportService.areSickSbZero(list);
			boolean countAnnualVacAI = tableExportService.areAnnualVacationAIZero(list);
			boolean unpaidVacationCountAB = tableExportService.areUnpaidVacationZero(list);
			boolean parentalLeaveAA = tableExportService.areParentalLeaveDaysZero(list);
			boolean educationVacationAMZero = tableExportService.areVacationEducationDaysForAllZero(list);
			boolean areExtraVacationAPZero = tableExportService.areExtraVacationDaysAllZero(list);
			boolean areCreatveVacationARDaysZero = tableExportService.areCreativeVacationDaysForAllZero(list);
			boolean areVoluntaryWorkBDAllZero = tableExportService.areVolunatryWorkAllDaysZero(list);
			boolean areMissionWorkAllDaysZero = tableExportService.areWorkMissionDaysZero(list);
			boolean areEducationWorkDaysAllZero = tableExportService.areDaysOfMissionEducationZero(list);
			boolean areUnjustifiedAbsenceAllDaysZero = tableExportService.areUnjustifiedAbsenceDaysZero(list);

			if (list.size() > 0) {
				model.addAttribute("emplList", list);
				model.addAttribute("finSource", finSource);
				model.addAttribute("year", year);
				model.addAttribute("month", monthNumberAndName.get(month));
				model.addAttribute("monthNumber", month);

				if (areSDaysAllZero == false)
					model.addAttribute("s", areSDaysAllZero);

				if (areSbAllZero == false)
					model.addAttribute("sb", areSbAllZero);

				if (countAnnualVacAI == false)
					model.addAttribute("ai", countAnnualVacAI);

				if (unpaidVacationCountAB == false)
					model.addAttribute("ab", unpaidVacationCountAB);

				if (parentalLeaveAA == false)
					model.addAttribute("aa", parentalLeaveAA);

				if (educationVacationAMZero == false)
					model.addAttribute("am", educationVacationAMZero);

				if (areExtraVacationAPZero == false)
					model.addAttribute("ap", areExtraVacationAPZero);

				if (areCreatveVacationARDaysZero == false)
					model.addAttribute("ar", areCreatveVacationARDaysZero);

				if (areVoluntaryWorkBDAllZero == false)
					model.addAttribute("bd", areVoluntaryWorkBDAllZero);
				
				if (areMissionWorkAllDaysZero == false)
					model.addAttribute("kd", areMissionWorkAllDaysZero);
				
				if (areEducationWorkDaysAllZero == false)
					model.addAttribute("km", areEducationWorkDaysAllZero);
				
				if (areUnjustifiedAbsenceAllDaysZero == false)
					model.addAttribute("n", areUnjustifiedAbsenceAllDaysZero);

				model.addAttribute("workHoursInMonth", workHoursInMonthd);
								

				return "table-export-fin-source-results";
			} else {
				return "no-results";
			}
		}
	else if (finSourceId==-1 && employeeId!=-1){
	
			
		Employee employee = employeeService.selectOneEmployeeById(employeeId);
			
			//ArrayList<EmployeeAndHourDTO> list = tableExportService.selectOneEmployeeAndAllProjects(employee, year, month);
			ArrayList<EmployeeAndHourDTO> list = tableExportService.selectNecessaryDataForEmployeeInAllOtherFinanceSourcesInOneMonth(year, month, employeeId);
			ArrayList<EmployeeAndHourDTO> listPr = tableExportService.selectDataEmployeeInProjectsInMonthAndYear(year, month, employeeId);
			Map<Integer, Double> hoursResult = tableExportService.allHoursOneEmployeeOnDate(year, month, employeeId);
			
			boolean areSDaysAllZero = tableExportService.areSickDaysSZero(list);
			boolean areSbAllZero = tableExportService.areSickSbZero(list);
			boolean countAnnualVacAI = tableExportService.areAnnualVacationAIZero(list);
			boolean unpaidVacationCountAB = tableExportService.areUnpaidVacationZero(list);
			boolean parentalLeaveAA = tableExportService.areParentalLeaveDaysZero(list);
			boolean educationVacationAMZero = tableExportService.areVacationEducationDaysForAllZero(list);
			boolean areExtraVacationAPZero = tableExportService.areExtraVacationDaysAllZero(list);
			boolean areCreatveVacationARDaysZero = tableExportService.areCreativeVacationDaysForAllZero(list);
			boolean areVoluntaryWorkBDAllZero = tableExportService.areVolunatryWorkAllDaysZero(list);
			boolean areMissionWorkAllDaysZero = tableExportService.areWorkMissionDaysZero(list);
			boolean areEducationWorkDaysAllZero = tableExportService.areDaysOfMissionEducationZero(list);
			
			if (list.size() == 0) {
				return "no-results";
			}
			else {
			model.addAttribute("empl", employee);
			model.addAttribute("list", list);
			model.addAttribute("listPr", listPr);
			model.addAttribute("year", year);
			model.addAttribute("month", monthNumberAndName.get(month));
			model.addAttribute("workHoursInMonth", workHoursInMonthd);
			model.addAttribute("hoursRes", hoursResult);
			if (areSDaysAllZero == false)
				model.addAttribute("s", areSDaysAllZero);

			if (areSbAllZero == false)
				model.addAttribute("sb", areSbAllZero);

			if (countAnnualVacAI == false)
				model.addAttribute("ai", countAnnualVacAI);

			if (unpaidVacationCountAB == false)
				model.addAttribute("ab", unpaidVacationCountAB);

			if (parentalLeaveAA == false)
				model.addAttribute("aa", parentalLeaveAA);

			if (educationVacationAMZero == false)
				model.addAttribute("am", educationVacationAMZero);

			if (areExtraVacationAPZero == false)
				model.addAttribute("ap", areExtraVacationAPZero);

			if (areCreatveVacationARDaysZero == false)
				model.addAttribute("ar", areCreatveVacationARDaysZero);

			if (areVoluntaryWorkBDAllZero == false)
				model.addAttribute("bd", areVoluntaryWorkBDAllZero);
			
			if (areMissionWorkAllDaysZero == false)
				model.addAttribute("kd", areMissionWorkAllDaysZero);
			
			if (areEducationWorkDaysAllZero == false)
				model.addAttribute("km", areEducationWorkDaysAllZero);
				return "table-export-employee-results";
			}
		}else {
			return "no-results";
		}
		
	}
	

	

//	@GetMapping("dlut/tabele/eksportet/finansejumaAvots")
//	public String getTableExportFinSourcePage(FinanceSource finSource, Model model) {
//		ArrayList<String> yearOptions = emplTimeSheetService.selectYearsFromTimeSheets();
//		ArrayList<FinanceSource> allFinSources = tableExportService.selectAllFinSources();
//		System.out.println(yearOptions.get(0));//tikai pārbaudei
//		System.out.println(yearOptions.get(1));//tikai pārbaudei
//		String[] monthOptions = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
//		model.addAttribute("years", yearOptions);
//		model.addAttribute("month", monthOptions);
//		model.addAttribute("finSources", allFinSources);
//
//		return "table-export-fin-source";
//	}
//	
//	@PostMapping("dlut/tabele/eksportet/finansejumaAvots")
//		String postTableExportFinSourcePage(@RequestParam(value = "year") int year, 
//				@RequestParam(value = "month") int month,
//				@RequestParam(value = "finSource") FinanceSource finSource, 
//				Model model, @Valid EmployeeAndHourDTO emplHourDTO, BindingResult res) {
//		
//		ArrayList<EmployeeAndHourDTO> list = new ArrayList<>();
//		
//		if(finSource instanceof Project) 
//			list = tableExportService.selectEmployeesAndHoursForProjectForYearMonth(finSource.getIdFinSource(), year, month);
//		else if (finSource instanceof BaseFin)
//			list = tableExportService.selectEmployeesAndHoursForBaseFinForYearMonth(finSource.getIdFinSource(), year, month);
//		else if (finSource instanceof AcademicWorkLoad)
//			list = tableExportService.selectEmployeesAndHoursForAcademicWorkLoadForYearMonth(finSource.getIdFinSource(), year, month);
//		else 
//			list = tableExportService.selectEmployeesAndHoursForIndirectVuasForYearMonth(finSource.getIdFinSource(), year, month);
//		
//		Map<Integer, String> monthNumberAndName = new HashedMap<>();
//		monthNumberAndName.put(1, "janvāris");
//		monthNumberAndName.put(2, "februāris");
//		monthNumberAndName.put(3, "marts");
//		monthNumberAndName.put(4, "aprīlis");
//		monthNumberAndName.put(5, "maijs");
//		monthNumberAndName.put(6, "jūnijs");
//		monthNumberAndName.put(7, "jūlijs");
//		monthNumberAndName.put(8, "augusts");
//		monthNumberAndName.put(9, "septembris");
//		monthNumberAndName.put(10, "oktobris");
//		monthNumberAndName.put(11, "novembris");
//		monthNumberAndName.put(12, "decembris");
//		
//		
//		if(list != null) {
//		model.addAttribute("emplList", list);
//		model.addAttribute("finSource", finSource);
//		model.addAttribute("year", year);
//		model.addAttribute("month", monthNumberAndName.get(month));
//		System.out.println("Nostrādāja");//tikai pārbaudei
//		System.out.println(list.size());//tikai pārbaudei
//			return "table-export-fin-source-results";
//		} else {
//			return "no-results";
//		}
//	}

	
//	@GetMapping("dlut/tabele/eksportet/darbinieks")
//	public String getTableExportEmployeePage(Employee employee, Model model) {
//		ArrayList<String> yearOptions = emplTimeSheetService.selectYearsFromTimeSheets();
//		String[] monthOptions = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
//		model.addAttribute("years", yearOptions);
//		model.addAttribute("month", monthOptions);
//		model.addAttribute("employees", employeeService.selectAllEmployees());
//		return "table-export-employee";
//	}
//	
//	@PostMapping("/tabele/eksportet/darbinieks")
//	String postTableExportEmployee(@RequestParam(value = "year") String year, 
//			@RequestParam(value = "month") String month,
//			@RequestParam(value = "employee") Employee empl, 
//			Model model, @Valid EmployeeAndHourDTO emplHourDTO, BindingResult res) {
//	
//	
//	ArrayList<EmployeeAndHourDTO> list = tableExportService.selectOneEmployeeAndAllProjects(empl, year, month);
//	
//	if(list.get(0).getHoursInMonth() == null) {
//		return "no-results";
//	}
//	
//	if(list != null) {
//	model.addAttribute("empl", empl);
//	model.addAttribute("projectList", list);
//	model.addAttribute("year", year);
//	model.addAttribute("month", month);
//	System.out.println("Nostrādāja");//tikai pārbaudei
//	System.out.println(list.size());//tikai pārbaudei
//		return "table-export-employee-results";
//	} else {
//		return "no-results";
//	}
//}
	*/

}
