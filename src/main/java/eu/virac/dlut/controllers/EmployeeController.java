package eu.virac.dlut.controllers;

import eu.virac.dlut.models.helpers.EmployeeDTO;
import eu.virac.dlut.services.IEmployeeService;
import eu.virac.dlut.services.IUserManageService;
import eu.virac.dlut.utils.TokenValidationUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dlut/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;
    @Autowired
    private IUserManageService userManage;

    @PostMapping("/addNew")
    public ResponseEntity<?> addNewEmployee(@RequestHeader HttpHeaders headers, @RequestBody @Valid EmployeeDTO employeeDTO) {
        return TokenValidationUtil.handleRequest(userManage, headers, () -> {
            try {
                employeeService.insertEmployee(employeeDTO);
                return new ResponseEntity<>(employeeDTO, HttpStatusCode.valueOf(200));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
            }
        });
    }

    @GetMapping("/showAll")
    public ResponseEntity<?> showAllEmployees(@RequestHeader HttpHeaders headers) {
        return TokenValidationUtil.handleRequest(userManage, headers, () ->
                new ResponseEntity<>(employeeService.retrieveAllDataForEmployees(), HttpStatusCode.valueOf(200))
        );
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO, @RequestHeader HttpHeaders headers) {
        return TokenValidationUtil.handleRequest(userManage, headers, () -> {
            try {
                employeeService.updateEmployeeById(employeeDTO);
                return new ResponseEntity<>(employeeDTO, HttpStatusCode.valueOf(200));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
            }
        });
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployee(@RequestHeader HttpHeaders headers, @RequestBody @Valid EmployeeDTO employeeDTO) {
        return TokenValidationUtil.handleRequest(userManage, headers, () -> {
            try {
                employeeService.deleteEmployeeById(employeeDTO);
                return new ResponseEntity<>(employeeService.retrieveAllDataForEmployees(), HttpStatusCode.valueOf(200));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
            }
        });
    }
}
