package eu.virac.dlut.controllers;

import eu.virac.dlut.models.helpers.EmployeeDTO;
import eu.virac.dlut.services.IEmployeeService;
import eu.virac.dlut.services.IUserManageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dlut/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;
    @Autowired
    private IUserManageService userManage;

    @PostMapping("/addNew")
    public ResponseEntity<?> addNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        try {
            employeeService.insertEmployee(employeeDTO);
            return new ResponseEntity<>(employeeDTO, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/showAll")
    public ResponseEntity<?> showAllEmployees(@RequestHeader HttpHeaders headers) {
        if (userManage.isUserTokenOk(headers.getFirst("token"))) {
            return new ResponseEntity<ArrayList<EmployeeDTO>>(employeeService.retrieveAllDataForEmployees(), HttpStatusCode.valueOf(200));
        } else
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        try {
            employeeService.updateEmployeeById(employeeDTO);
            return new ResponseEntity<>(employeeDTO, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        try {
            employeeService.deleteEmployeeById(employeeDTO);
            return new ResponseEntity<>(employeeService.retrieveAllDataForEmployees(), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }
}
