package eu.virac.dlut.controllers;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import eu.virac.dlut.services.IExcelCreatorService;

import java.io.ByteArrayOutputStream;

@Controller
public class ExcelTableController {

    @Autowired
    IExcelCreatorService excelCreatorService;

    @Autowired
    TableExportController tableExportController;

    @GetMapping("/dlut/tabele/eksportet/finansejuma-avots/excel/{year}/{month}/{id}")
    public ResponseEntity<InputStreamResource> createExcelFinanceSource(@PathVariable("year") int year, @PathVariable("month") int month,
                                                                        @PathVariable("id") int finSourceId) {

        try (Workbook workbook = excelCreatorService.createFinSourceTableExcel(finSourceId, year, month);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            ByteArrayResource resource = new ByteArrayResource(byteArrayOutputStream.toByteArray());
            return ResponseEntity.ok()
                    .contentLength(resource.contentLength())
                    .body(new InputStreamResource(resource.getInputStream()));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/dlut/tabele/eksportet/darbinieks/excel/{year}/{month}/{id}")
    public ResponseEntity<Object> createExcelEmployee(@PathVariable("year") int year, @PathVariable("month") int month,
                                                 @PathVariable("id") int employeeId) {

        try (Workbook workbook = excelCreatorService.createEmployeeExcel(employeeId, year, month);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            workbook.write(byteArrayOutputStream);
            ByteArrayResource resource = new ByteArrayResource(byteArrayOutputStream.toByteArray());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"employee-" + month + "-" + year + ".xlsx\"");
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .body(new InputStreamResource(resource.getInputStream()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating Excel file");
        }
    }
}