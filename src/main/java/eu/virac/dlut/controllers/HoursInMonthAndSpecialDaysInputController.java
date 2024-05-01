package eu.virac.dlut.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import eu.virac.dlut.models.helpers.HoursInMonthDTO;
import jakarta.validation.Valid;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import eu.virac.dlut.models.HoursInMonth;
import eu.virac.dlut.repos.IHoursInMonthRepo;
import eu.virac.dlut.services.IHoursInMonthService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dlut/table")
public class HoursInMonthAndSpecialDaysInputController {
	
	@Autowired
	IHoursInMonthService hoursInMonthService;
	
//	@GetMapping("dlut/tabele/ievadit/darba-stundas")
//	String getWorkHoursInMonthInput (Model model) {
//		String[] yearOptions = {"2020", "2021", "2022"};
//		String[] monthOptions = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
//
//		model.addAttribute("years", yearOptions);
//		model.addAttribute("month", monthOptions);
//
//		return "input-work-hours";
//	}

	@PostMapping("/insertHoursInMonth")
	public ResponseEntity<?> insertHoursInMonth(@RequestBody @Valid HoursInMonthDTO hoursInMonthDTO) {
		try {
			hoursInMonthService.insertHoursInMonthDTO(hoursInMonthDTO);
			return new ResponseEntity<>(hoursInMonthDTO, HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
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
