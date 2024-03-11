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

import java.io.ByteArrayInputStream;
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

        try {

            Workbook workbook = excelCreatorService.createFinSourceTableExcel(finSourceId, year, month);

            // Convert the workbook to a byte array, lai var nosūtīt kā response
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
            workbook.close();

            ByteArrayResource resource = new ByteArrayResource(byteArrayOutputStream.toByteArray());

            // Uzstāda headers, lai browser zinātu, ka failu vajag lejupielādēt
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"finSource-" + month + "-" + year + ".xlsx\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dlut/tabele/eksportet/darbinieks/excel/{year}/{month}/{id}")
    public ResponseEntity<?> createExcelEmployee(@PathVariable("year") int year, @PathVariable("month") int month,
                                                 @PathVariable("id") int employeeId) {

        try {
            Workbook workbook = excelCreatorService.createEmployeeExcel(employeeId, year, month);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close(); // Ensure the stream is closed after use
            ByteArrayResource resource = new ByteArrayResource(byteArrayOutputStream.toByteArray());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"employee-" + month + "-" + year + ".xlsx\"");
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));
        } catch (Exception e) {
            return new ResponseEntity<>("Error generating Excel file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
