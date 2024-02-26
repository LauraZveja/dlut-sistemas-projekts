package eu.virac.dlut.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.virac.dlut.services.IExcelCreatorService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ExcelTableController {

	@Autowired
	IExcelCreatorService excelCreatorService;

	@Autowired
	TableExportController tableExportController;

	@GetMapping("/dlut/tabele/eksportet/finansejuma-avots/excel/{year}/{month}/{id}")
	public ResponseEntity<?> createExcelFinanceSource(@PathVariable("year") int year, @PathVariable("month") int month,
													  @PathVariable("id") int finSourceId) {
		try {
			String filePath = "C:\\Users\\laura\\Desktop\\finSource-" + month + "-" + year + ".xlsx";
			excelCreatorService.createFinSourceTableExcel(filePath, finSourceId, year, month);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Excel fails izveidots veiksmigi.");
			response.put("filePath", filePath);
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", "Excel fails nav izveidots.");
			errorResponse.put("message", e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(500));
		}
	}

	@GetMapping("/dlut/tabele/eksportet/darbinieks/excel/{year}/{month}/{id}")
	public ResponseEntity<?> createExcelEmployee(@PathVariable("year") int year, @PathVariable("month") int month,
												 @PathVariable("id") int employeeId) {
		try {
			String filePath = "C:\\Users\\laura\\Desktop\\employee-" + month + "-" + year + ".xlsx";
			excelCreatorService.createEmployeeExcel(filePath, employeeId, year, month);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Excel fails izveidots veiksmigi.");
			response.put("filePath", filePath);
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", "Excel fails nav izveidots.");
			errorResponse.put("message", e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(500));
		}
	}

}
