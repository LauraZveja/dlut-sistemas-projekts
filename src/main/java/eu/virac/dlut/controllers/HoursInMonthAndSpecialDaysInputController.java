package eu.virac.dlut.controllers;

import java.util.ArrayList;

import eu.virac.dlut.models.helpers.HoursInMonthDTO;
import eu.virac.dlut.services.IUserManageService;
import eu.virac.dlut.utils.TokenValidationUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @Autowired
    IUserManageService userManage;

    @PostMapping("/insertHoursInMonth")
    public ResponseEntity<?> insertHoursInMonth(@RequestHeader HttpHeaders headers, @RequestBody @Valid HoursInMonthDTO hoursInMonthDTO) {
        return TokenValidationUtil.handleRequest(userManage, headers, () -> {
            try {
                hoursInMonthService.insertHoursInMonthDTO(hoursInMonthDTO);
                return new ResponseEntity<>(hoursInMonthDTO, HttpStatusCode.valueOf(200));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
            }
        });
    }

    @GetMapping("/showHoursInYear/{year}")
    public ResponseEntity<?> getShowAllHoursInYear(@RequestHeader HttpHeaders headers, @PathVariable int year) {
        return TokenValidationUtil.handleRequest(userManage, headers, () -> {
            try {
                ArrayList<HoursInMonthDTO> hoursInMonthDTOs = hoursInMonthService.selectAllHoursInYear(year);
                return new ResponseEntity<>(hoursInMonthDTOs, HttpStatusCode.valueOf(200));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
            }
        });
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHoursInMonth(@RequestHeader HttpHeaders headers, @RequestBody @Valid HoursInMonthDTO hoursInMonthDTO) {
        return TokenValidationUtil.handleRequest(userManage, headers, () -> {
            try {
                int year = hoursInMonthDTO.getYear();
                hoursInMonthService.deleteHoursInMonthDTO(hoursInMonthDTO);
                return new ResponseEntity<>(hoursInMonthService.selectAllHoursInYear(year), HttpStatusCode.valueOf(200));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
            }
        });
    }

    @GetMapping("/showHoursInMonth/{year}/{month}")
    public ResponseEntity<?> getShowHoursInMonth(@RequestHeader HttpHeaders headers, @PathVariable int year, @PathVariable int month) {
        return TokenValidationUtil.handleRequest(userManage, headers, () -> {
            try {
                HoursInMonthDTO hoursInMonthDTO = hoursInMonthService.selectHoursInMonthByYearAndMonthDTO(year, month);
                return new ResponseEntity<>(hoursInMonthDTO, HttpStatusCode.valueOf(200));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
            }
        });
    }

    @PutMapping("/updateHoursInMonth")
    public ResponseEntity<?> updateHoursInMonth(@RequestHeader HttpHeaders headers, @RequestBody @Valid HoursInMonthDTO hoursInMonthDTO) {
        return TokenValidationUtil.handleRequest(userManage, headers, () -> {
            try {
                hoursInMonthService.updateHoursInMonthDTO(hoursInMonthDTO);
                return new ResponseEntity<>(hoursInMonthDTO, HttpStatusCode.valueOf(200));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
            }
        });
    }

    @PostMapping("/insertHoursInYear")
    public ResponseEntity<?> insertHoursInYear(@RequestHeader HttpHeaders headers, @RequestBody @Valid ArrayList<HoursInMonthDTO> hoursInYearDTO) {
        return TokenValidationUtil.handleRequest(userManage, headers, () -> {
            try {
                ArrayList<HoursInMonthDTO> insertedHours = hoursInMonthService.insertHoursInYear(new ArrayList<>(hoursInYearDTO));
                return new ResponseEntity<>(insertedHours, HttpStatusCode.valueOf(200));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
            }
        });
    }

}
