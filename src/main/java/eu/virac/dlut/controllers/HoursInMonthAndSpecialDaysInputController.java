package eu.virac.dlut.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import eu.virac.dlut.models.HoursInMonth;
import eu.virac.dlut.repos.IHoursInMonthRepo;
import eu.virac.dlut.services.IHoursInMonthService;

@Controller
public class HoursInMonthAndSpecialDaysInputController {
	
	@Autowired
	IHoursInMonthService hoursInMonthService;
	
	@GetMapping("dlut/tabele/ievadit/darba-stundas") 
	String getWorkHoursInMonthInput (Model model) {
		String[] yearOptions = {"2020", "2021", "2022"};
		String[] monthOptions = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		
		model.addAttribute("years", yearOptions);
		model.addAttribute("month", monthOptions);
		
		return "input-work-hours";
	}

	@PostMapping("dlut/tabele/ievadit/darba-stundas") 
	String postWorkHoursInMonthInput (@RequestParam(value = "year") int year, 
			@RequestParam(value = "month") int month,
			@RequestParam(value = "workHoursInMonth") int workHoursInMonth, @Valid HoursInMonth hoursInMonth, BindingResult result) {

		if (!result.hasErrors()) {
			try {
				hoursInMonthService.insertHoursInMonth(year, month, workHoursInMonth);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/dlut/tabele/skatit/darba-stundas";
		} else {
			return "input-work-hours";
		
	}
	}


	@GetMapping("/dlut/tabele/skatit/darba-stundas") // localhost:8080/article/category/showAll
	public String getShowAllHoursInMonth(Model model) {


		ArrayList<HoursInMonth> h = hoursInMonthService.selectAllHoursInMonths();
		h.sort(Comparator.comparing(HoursInMonth::getYear).thenComparing(HoursInMonth::getMonth));
		model.addAttribute("workHours", h);

		return "show-all-hours-in-month";

	}
	
}
