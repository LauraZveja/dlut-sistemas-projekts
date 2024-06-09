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

    private final IEmployeeService employeeService;
    private final IUserManageService userManage;

    @Autowired
    public EmployeeController(IEmployeeService employeeService, IUserManageService userManage) {
        this.employeeService = employeeService;
        this.userManage = userManage;
    }

    @PostMapping("/addNew")
    public ResponseEntity<Object> addNewEmployee(@RequestHeader HttpHeaders headers, @RequestBody @Valid EmployeeDTO employeeDTO) {
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
    public ResponseEntity<Object> showAllEmployees(@RequestHeader HttpHeaders headers) {
        return TokenValidationUtil.handleRequest(userManage, headers, () ->
                new ResponseEntity<>(employeeService.retrieveAllDataForEmployees(), HttpStatusCode.valueOf(200))
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO, @RequestHeader HttpHeaders headers) {
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
    public ResponseEntity<Object> deleteEmployee(@RequestHeader HttpHeaders headers, @RequestBody @Valid EmployeeDTO employeeDTO) {
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
