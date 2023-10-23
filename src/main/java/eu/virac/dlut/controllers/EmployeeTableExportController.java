package eu.virac.dlut.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.virac.dlut.models.Employee;
import eu.virac.dlut.models.EmployeeTimeSheet;
import eu.virac.dlut.models.FinanceOrderWork;
import eu.virac.dlut.models.FinanceSource;
import eu.virac.dlut.models.FullTimeEquivalent;
import eu.virac.dlut.models.HoursInMonth;
import eu.virac.dlut.models.helpers.EmployeeAndHourDTO;
import eu.virac.dlut.models.helpers.TableResultEditingDTO;
import eu.virac.dlut.repos.IEmployeeRepo;
import eu.virac.dlut.repos.IFinanceOrderWorkRepo;
import eu.virac.dlut.services.IEmployeeService;
import eu.virac.dlut.services.IEmployeeTimeSheetService;
import eu.virac.dlut.services.IFullTimeEquivalentService;
import eu.virac.dlut.services.IHoursInMonthService;
import eu.virac.dlut.services.ITableExportService;

@Controller
public class EmployeeTableExportController {

	@Autowired
	ITableExportService tableExportService;

	@Autowired
	IEmployeeTimeSheetService emplTimeSheetService;

	@Autowired
	IEmployeeService employeeService;
	
	@Autowired
	IHoursInMonthService hoursInMonthService;
	
	@Autowired
	IFinanceOrderWorkRepo financeOrderWorkRepo;
	
	@Autowired
	IFullTimeEquivalentService fullTimeEquivalentService;
	
	@Autowired
	IEmployeeRepo employeeRepo;
	
	@GetMapping("dlut/tabele/eksportet/darbinieks/izvele")
	public String getTableExport(Employee employee, Model model) {

		ArrayList<String> yearOptions = emplTimeSheetService.selectYearsFromTimeSheets();
		String[] monthOptions = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		model.addAttribute("years", yearOptions);
		model.addAttribute("month", monthOptions);
		model.addAttribute("employees", employeeService.selectAllEmployees());

		return "table-export-employee";
	}

	@PostMapping("dlut/tabele/eksportet/darbinieks/izvele")
	public String postFinanceSourceTableExport(@RequestParam(value = "year") int year,
			@RequestParam(value = "month") int month, @RequestParam(value = "employee") int employeeId, Model model,
			@Valid EmployeeAndHourDTO emplHourDTO, BindingResult res) {

		return "redirect:" + year + "/" + month + "/" + employeeId;
	}

	@GetMapping("dlut/tabele/eksportet/darbinieks/{year}/{month}/{idempl}")
	public String getFinanceSourceTableExportResults(@PathVariable("year") int year, @PathVariable("month") int month,
			@PathVariable("idempl") int employeeId, Model model) {

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

		try {
			Employee employee = employeeService.selectOneEmployeeById(employeeId);

			// ArrayList<EmployeeAndHourDTO> list =
			// tableExportService.selectOneEmployeeAndAllProjects(employee, year, month);
			ArrayList<EmployeeAndHourDTO> list = tableExportService
					.selectNecessaryDataForEmployeeInAllOtherFinanceSourcesInOneMonth(year, month, employeeId);
//			ArrayList<EmployeeAndHourDTO> listPr = tableExportService.selectDataEmployeeInProjectsInMonthAndYear(year,
//					month, employeeId);
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
			
			double allHoursWorked = tableExportService.sumAllHoursWorked(hoursResult);

			if ((list.size() > 0)) {

				model.addAttribute("empl", employee);
				model.addAttribute("list", list);
				model.addAttribute("year", year);
				model.addAttribute("month", monthNumberAndName.get(month));
				model.addAttribute("monthNumber", month);
				model.addAttribute("workHoursInMonth", workHoursThisMonth);
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
				
				model.addAttribute("allHWorked", allHoursWorked);
				

				return "table-export-employee-results";
			} else {
				return "no-results";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "no-results";
		}
	}
	
//rediģēšanai
	@GetMapping("dlut/tabele/rediget/darbinieks/{year}/{month}/{idempl}")
	public String getEmployeeTableExportResultsForEditing(@PathVariable("year") int year, @PathVariable("month") int month,
			@PathVariable("idempl") int employeeId, Model model) {

		List<EmployeeAndHourDTO> results = new ArrayList<>();
		
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

		try {
			Employee employee = employeeService.selectOneEmployeeById(employeeId);

		
			ArrayList<EmployeeAndHourDTO> list = tableExportService
					.selectNecessaryDataForEmployeeInAllOtherFinanceSourcesInOneMonth(year, month, employeeId);
			
			for(EmployeeAndHourDTO temp: list) {
				results.add(temp);
			}
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
			
			double allHoursWorked = tableExportService.sumAllHoursWorked(hoursResult);

			if ((list.size() > 0)) {

				model.addAttribute("empl", employee);
				model.addAttribute("list", list);
				model.addAttribute("year", year);
				model.addAttribute("month", monthNumberAndName.get(month));
				model.addAttribute("monthNumber", month);
				model.addAttribute("workHoursInMonth", workHoursThisMonth);
				model.addAttribute("hoursRes", hoursResult);
				
				model.addAttribute("form", new TableResultEditingDTO(results));
				
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
				
				model.addAttribute("allHWorked", allHoursWorked);
				

				return "table-edit-employee";
			} else {
				return "no-results";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "no-results";
		}
	}

	@PostMapping("dlut/tabele/rediget/darbinieks/{year}/{month}/{idempl}")
	public String postEmployeeTableExportResultsForEditing(@PathVariable("year") int year,
			@PathVariable("month") int month, @PathVariable("idempl") int employeeId,
			@ModelAttribute TableResultEditingDTO form) {

		try {
			ArrayList<EmployeeAndHourDTO> oldList = tableExportService
					.selectNecessaryDataForEmployeeInAllOtherFinanceSourcesInOneMonth(year, month, employeeId);

			for (EmployeeAndHourDTO temp : form.getResults()) {
				for (EmployeeAndHourDTO oldTemp : oldList) {

					if (temp.getFinanceSourceId() == oldTemp.getFinanceSourceId()) {

						Map<Integer, String> mapSpec = temp.getHoursInMonth();
						Map<Integer, String> oldMap = oldTemp.getHoursInMonth();

						for (Map.Entry<Integer, String> e : mapSpec.entrySet()) {
							for (Map.Entry<Integer, String> oldE : oldMap.entrySet()) {

								if (e.getKey().equals(oldE.getKey())) {
									if (!e.getValue().equals(oldE.getValue())) {

										double hoursDouble = Double.parseDouble(e.getValue());

										if ((oldE.getValue().contentEquals("0"))
												|| (oldE.getValue().contentEquals("BR"))) {
											emplTimeSheetService.saveNewFromEditEmployeeTable(year, month, e.getKey(),
													employeeId, temp.getFinanceSourceId(), hoursDouble,
													temp.getPosition());
											System.out.println(temp.getPosition());
											System.out.println(hoursDouble);
										} else
											emplTimeSheetService.updateEntry(year, month, e.getKey(), employeeId,
													temp.getFinanceSourceId(), hoursDouble);
									}
								}

							}
						}
						if (temp.getVacationHours() != oldTemp.getVacationHours()) {

							fullTimeEquivalentService.updateVacationHoursByYearMonthFinSourceEmployeeFromEditEmployee(
									year, month, employeeId, temp.getFinanceSourceId(), temp.getVacationHours());
						}
					}
				}
			}

			return "redirect:/dlut/tabele/eksportet/darbinieks/" + year + "/" + month + "/" + employeeId;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "no-results";
		}
	}

}
