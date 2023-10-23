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
import eu.virac.dlut.models.FinanceSource;
import eu.virac.dlut.models.HoursInMonth;
import eu.virac.dlut.models.Project;
import eu.virac.dlut.models.helpers.EmployeeAndHourDTO;
import eu.virac.dlut.services.IEmployeeTimeSheetService;
import eu.virac.dlut.services.IFinanceSourceService;
import eu.virac.dlut.services.IHoursInMonthService;
import eu.virac.dlut.services.ITableExportService;

@Controller
public class FinanceSourceTableExportController {

	@Autowired
	ITableExportService tableExportService;

	@Autowired
	IEmployeeTimeSheetService emplTimeSheetService;

	@Autowired
	IFinanceSourceService financeSourceService;
	
	@Autowired
	IHoursInMonthService hoursInMonthService;

	@GetMapping("dlut/tabele/eksportet/finansejuma-avots/izvele")
	public String getFinanceSourceTableExport(FinanceSource finSource, Model model) {

		ArrayList<String> yearOptions = emplTimeSheetService.selectYearsFromTimeSheets();
		String[] monthOptions = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		model.addAttribute("years", yearOptions);
		model.addAttribute("month", monthOptions);
		model.addAttribute("finSources", tableExportService.selectAllFinSources());

		return "table-export-finance-source";
	}

	@PostMapping("dlut/tabele/eksportet/finansejuma-avots/izvele")
	public String postFinanceSourceTableExport(@RequestParam(value = "year") int year,
			@RequestParam(value = "month") int month, @RequestParam(value = "finSource") int finSourceId, Model model,
			@Valid EmployeeAndHourDTO emplHourDTO, BindingResult res) {
//		return "redirect: dlut/tabele/eksportet/finansejuma-avots/" + year + "/" + month + "/" + finSourceId + "/"
//				+ workHoursInMonth;
		return "redirect:" + year + "/" + month + "/" + finSourceId;
	}

	@GetMapping("dlut/tabele/eksportet/finansejuma-avots/{year}/{month}/{idfs}")
	public String getFinanceSourceTableExportResults(@PathVariable("year") int year, @PathVariable("month") int month,
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

		ArrayList<EmployeeAndHourDTO> list = new ArrayList<>();

		try {
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

				model.addAttribute("workHoursInMonth", workHoursThisMonth);

				return "table-export-finance-source-results";
			} else {
				return "no-results";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "no-results";
		}
	
	}
}
