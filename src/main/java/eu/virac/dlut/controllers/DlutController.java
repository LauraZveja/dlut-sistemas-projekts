package eu.virac.dlut.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DlutController {

	@GetMapping("/dlut")
	public String getHome() {
		return "home";
	}
	
	
}
