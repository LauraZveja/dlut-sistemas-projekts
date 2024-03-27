package eu.virac.dlut.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import eu.virac.dlut.models.helpers.*;
import jakarta.validation.Valid;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import eu.virac.dlut.models.Employee;
import eu.virac.dlut.models.EmployeeTimeSheet;
import eu.virac.dlut.models.FinanceOrderWork;
import eu.virac.dlut.models.FinanceSource;
import eu.virac.dlut.models.FullTimeEquivalent;
import eu.virac.dlut.models.HoursInMonth;
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
    public ResponseEntity<YearMonthEmployeeSelectionDTO> getTableExport() {

        ArrayList<String> yearOptions = emplTimeSheetService.selectYearsFromTimeSheets();
        List<String> monthOptions = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        List<EmployeeDTO> employeeDTOs = employeeService.selectAllEmployees()
                .stream()
                .map(employee -> new EmployeeDTO(employee.getName(), employee.getSurname()))
                .collect(Collectors.toList());

        YearMonthEmployeeSelectionDTO yearMonthEmployeeSelectionDTO = new YearMonthEmployeeSelectionDTO(yearOptions, monthOptions, employeeDTOs);
        //iespējams modeļu klasēs nāksies izmantot @JsonIgnore, lai neaiziet rekursijā
        return new ResponseEntity<YearMonthEmployeeSelectionDTO>(yearMonthEmployeeSelectionDTO, HttpStatusCode.valueOf(200));
    }

    @PostMapping("dlut/tabele/eksportet/darbinieks/izvele")
    public ResponseEntity<YearMonthEmployeeSelectionDTO> postTableExport(@Valid @RequestBody YearMonthEmployeeSelectionDTO yearMonthEmployeeSelectionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(yearMonthEmployeeSelectionDTO);
        }

        return new ResponseEntity<YearMonthEmployeeSelectionDTO>(yearMonthEmployeeSelectionDTO, HttpStatusCode.valueOf(200));
    }

    @GetMapping("dlut/tabele/eksportet/darbinieks/{year}/{month}/{idempl}")
    public ResponseEntity<?> getFinanceSourceTableExportResults(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("idempl") int employeeId) {

        Map<Integer, String> monthNumberAndName = new HashedMap<>();
        // mēneša attēlojumam virsrakstā
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

            HoursInMonth hoursInMonth = hoursInMonthService.selectHoursInMonthByYearAndMonth(year, month);
            double workHoursThisMonth = (hoursInMonth != null) ? hoursInMonth.getHoursInMonth() : 0.0;

            List<EmployeeAndHourDTO> employeeHoursList = tableExportService.selectNecessaryDataForEmployeeInAllOtherFinanceSourcesInOneMonth(year, month, employeeId);
            if (employeeHoursList.isEmpty()) {
                return ResponseEntity.noContent().build(); // No content found
            }
            Employee employee = employeeService.selectOneEmployeeById(employeeId);
            EmployeeDTO employeeDTO = new EmployeeDTO(employee.getName(), employee.getSurname());

            ArrayList<EmployeeAndHourDTO> list = tableExportService
                    .selectNecessaryDataForEmployeeInAllOtherFinanceSourcesInOneMonth(year, month, employeeId);

            Map<Integer, Double> hoursResult = tableExportService.allHoursOneEmployeeOnDate(year, month, employeeId);

            EmployeeSummaryResponseDTO responseDTO = new EmployeeSummaryResponseDTO(
                    employeeDTO,
                    new ArrayList<>(employeeHoursList),
                    workHoursThisMonth,
                    monthNumberAndName,
                    tableExportService.areSickDaysSZero(list),
                    tableExportService.areSickSbZero(list),
                    tableExportService.areAnnualVacationAIZero(list),
                    tableExportService.areUnpaidVacationZero(list),
                    tableExportService.areParentalLeaveDaysZero(list),
                    tableExportService.areVacationEducationDaysForAllZero(list),
                    tableExportService.areExtraVacationDaysAllZero(list),
                    tableExportService.areCreativeVacationDaysForAllZero(list),
                    tableExportService.areVolunatryWorkAllDaysZero(list),
                    tableExportService.areWorkMissionDaysZero(list),
                    tableExportService.areDaysOfMissionEducationZero(list),
                    tableExportService.sumAllHoursWorked(hoursResult)
            );

            return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(200));

        } catch (Exception e) {
            return new ResponseEntity<>("Error generating results: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //rediģēšanai
    @GetMapping("dlut/tabele/rediget/darbinieks/{year}/{month}/{idempl}")
    public ResponseEntity<?> getEmployeeTableExportResultsForEditing(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("idempl") int employeeId) {

        List<EmployeeAndHourDTO> results = new ArrayList<>();
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
            HoursInMonth hoursInMonth = hoursInMonthService.selectHoursInMonthByYearAndMonth(year, month);
            double workHoursThisMonth = (hoursInMonth != null) ? hoursInMonth.getHoursInMonth() : 0.0;


            ArrayList<EmployeeAndHourDTO> employeeHoursList = tableExportService.selectNecessaryDataForEmployeeInAllOtherFinanceSourcesInOneMonth(year, month, employeeId);
            if (employeeHoursList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }


            Employee employee = employeeService.selectOneEmployeeById(employeeId);
            EmployeeDTO employeeDTO = new EmployeeDTO(employee.getName(), employee.getSurname());

            Map<Integer, Double> hoursResult = tableExportService.allHoursOneEmployeeOnDate(year, month, employeeId);

            EmployeeSummaryResponseDTO responseDTO = new EmployeeSummaryResponseDTO(
                    employeeDTO,
                    new ArrayList<>(employeeHoursList),
                    workHoursThisMonth,
                    monthNumberAndName,
                    tableExportService.areSickDaysSZero(employeeHoursList),
                    tableExportService.areSickSbZero(employeeHoursList),
                    tableExportService.areAnnualVacationAIZero(employeeHoursList),
                    tableExportService.areUnpaidVacationZero(employeeHoursList),
                    tableExportService.areParentalLeaveDaysZero(employeeHoursList),
                    tableExportService.areVacationEducationDaysForAllZero(employeeHoursList),
                    tableExportService.areExtraVacationDaysAllZero(employeeHoursList),
                    tableExportService.areCreativeVacationDaysForAllZero(employeeHoursList),
                    tableExportService.areVolunatryWorkAllDaysZero(employeeHoursList),
                    tableExportService.areWorkMissionDaysZero(employeeHoursList),
                    tableExportService.areDaysOfMissionEducationZero(employeeHoursList),
                    tableExportService.sumAllHoursWorked(hoursResult)
            );

            return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>("Error generating results: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
