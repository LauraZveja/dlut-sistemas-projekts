package eu.virac.dlut.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.virac.dlut.services.IExcelCreatorService;

@Controller
public class ExcelTableController {

	@Autowired
	IExcelCreatorService excelCreatorService;

	@Autowired
	TableExportController tableExportController;

	@GetMapping("/dlut/tabele/eksportet/finansejuma-avots/excel/{year}/{month}/{id}")
	public String createExcelFinanceSource(@PathVariable("year") int year, @PathVariable("month") int month,
			@PathVariable("id") int finSourceId) {
		try {
			excelCreatorService.createFinSourceTableExcel("C:\\Users\\elina\\Desktop\\finSource-"+month+"-"+year+".xlsx",
					finSourceId, year, month);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // nomainiet uz savu direktoriju failiem excel saglabāšanai
		return "excel-fin-source";
	}
	
	@GetMapping("/dlut/tabele/eksportet/darbinieks/excel/{year}/{month}/{id}")
	public String createExcelEmployee(@PathVariable("year") int year, @PathVariable("month") int month,
			@PathVariable("id") int employeeId){
		try {
			excelCreatorService.createEmployeeExcel("C:\\Users\\elina\\Desktop\\employee-"+month+"-"+year+".xlsx",
					employeeId, year, month);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // nomainiet uz savu direktoriju failiem excel saglabāšanai
		return "excel-fin-source";
	}

}
