package eu.virac.dlut.controllers;

import java.util.ArrayList;
import java.util.Map;

import jakarta.validation.Valid;

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
import eu.virac.dlut.models.HoursInMonth;
import eu.virac.dlut.models.Project;
import java.util.logging.Level;
import java.util.logging.Logger;
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
	private static final Logger logger = Logger.getLogger(TableExportController.class.getName());

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
			logger.log(Level.WARNING, "Error: ", e1);
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
			return "no-results";
		}

	}

}
