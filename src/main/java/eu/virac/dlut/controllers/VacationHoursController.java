package eu.virac.dlut.controllers;

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

import eu.virac.dlut.models.Employee;
import eu.virac.dlut.models.FinanceSource;
import eu.virac.dlut.models.FullTimeEquivalent;
import eu.virac.dlut.models.helpers.EmployeeAndHourDTO;
import eu.virac.dlut.repos.IEmployeeRepo;
import eu.virac.dlut.repos.IFinanceSourceRepo;
import eu.virac.dlut.repos.IFullTimeEquivalentRepo;
import eu.virac.dlut.services.IFullTimeEquivalentService;


@Controller
public class VacationHoursController {
	
	@Autowired
	IFullTimeEquivalentService fullTimeEquivalentService;
	
	@Autowired
	IFullTimeEquivalentRepo fullTimeEquivalentRepo;
	
	@Autowired
	IEmployeeRepo employeeRepo;
	
	@Autowired
	IFinanceSourceRepo financeSourceRepo;

	@GetMapping("/dlut/tabele/eksportet/finansejuma-avots/atvalinajuma-stundas/{year}/{month}/{idfs}/{idempl}")
	public String getVacationHoursUpdate(@PathVariable("year") int year, @PathVariable("month") int month,
			@PathVariable("idfs") int finSourceId, @PathVariable("idempl") int emplId,
			FullTimeEquivalent fullTimeEquivalent, Model model) {

		// mēneša attēlojumam burtos
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
		
		Employee e = employeeRepo.findById(emplId).get();
		FinanceSource fs = financeSourceRepo.findById(finSourceId).get();
		model.addAttribute("employee", e);
		model.addAttribute("finSource", fs);
		model.addAttribute("year", year);
		model.addAttribute("month", month ); //cipars
		model.addAttribute("monthName", monthNumberAndName.get(month)); //nosaukums vārdiem
		
		
		FullTimeEquivalent fullTimeEq = fullTimeEquivalentRepo.findByYearAndMonthAndEmployeeIdEmployeeAndFinanceSourceIdFinSource(year, month, emplId, finSourceId);
		if (fullTimeEq != null) {
			model.addAttribute("fte", fullTimeEq);
			return "update-vacation-hours";
		} else  {
			fullTimeEq = new FullTimeEquivalent();
			model.addAttribute("fte", fullTimeEq);
			return "update-vacation-hours";
		}
	}

	@PostMapping("/dlut/tabele/eksportet/finansejuma-avots/atvalinajuma-stundas/{year}/{month}/{idfs}/{idempl}")
	public String postVacationHoursUpdate(@PathVariable("year") int year, @PathVariable("month") int month,
			@PathVariable("idfs") int finSourceId, @PathVariable("idempl") int emplId, Model model, @Valid FullTimeEquivalent fte, BindingResult res) {
		if (!res.hasErrors()) {
			//fullTimeEquivalentService.updateVacationHoursByYearMonthFinSourceEmployee(year, month, finSourceId, emplId, fte);
		fullTimeEquivalentService.updateVacationHoursByYearMonthFinSourceEmployeeFromEditEmployee(year, month, emplId, finSourceId, fte.getVacationHours());
			
				return "redirect:/dlut/tabele/eksportet/finansejuma-avots/"+ year + "/" + month + "/" + finSourceId;
		}
			else
				return "update-vacation-hours";
	}
}
