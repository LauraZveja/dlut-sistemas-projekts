package eu.virac.dlut.controllers;

import java.util.ArrayList;

import eu.virac.dlut.models.helpers.HoursInMonthDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import eu.virac.dlut.services.IHoursInMonthService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dlut/table")
public class HoursInMonthAndSpecialDaysInputController {

    @Autowired
    IHoursInMonthService hoursInMonthService;

    @PostMapping("/insertHoursInMonth")
    public ResponseEntity<?> insertHoursInMonth(@RequestBody @Valid HoursInMonthDTO hoursInMonthDTO) {
        try {
            hoursInMonthService.insertHoursInMonthDTO(hoursInMonthDTO);
            return new ResponseEntity<>(hoursInMonthDTO, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/showHoursInYear/{year}")
    public ResponseEntity<?> getShowAllHoursInYear(@PathVariable int year) {

        try {
            ArrayList<HoursInMonthDTO> hoursInMonthDTOs = hoursInMonthService.selectAllHoursInYear(year);
            return new ResponseEntity<>(hoursInMonthDTOs, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployee(@RequestBody @Valid  HoursInMonthDTO hoursInMonthDTO) {
        try {
            int year = hoursInMonthDTO.getYear();
            hoursInMonthService.deleteHoursInMonthDTO(hoursInMonthDTO);
            return new ResponseEntity<>(hoursInMonthService.selectAllHoursInYear(year), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/showHoursInMonth/{year}/{month}")
    public ResponseEntity<?> getShowHoursInMonth(@PathVariable int year, @PathVariable int month) {

        try {
            HoursInMonthDTO hoursInMonthDTO = hoursInMonthService.selectHoursInMonthByYearAndMonthDTO(year, month);
            return new ResponseEntity<>(hoursInMonthDTO, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/updateHoursInMonth")
    public ResponseEntity<?> updateHoursInMonth(@RequestBody @Valid HoursInMonthDTO hoursInMonthDTO) {
        try {
            hoursInMonthService.updateHoursInMonthDTO(hoursInMonthDTO);
            return new ResponseEntity<>(hoursInMonthDTO, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

}
