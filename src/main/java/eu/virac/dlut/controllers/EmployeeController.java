package eu.virac.dlut.controllers;

import eu.virac.dlut.models.helpers.EmployeeDTO;
import eu.virac.dlut.services.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dlut/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @PostMapping("/addNew")
    public ResponseEntity<?> addNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        try {
            employeeService.insertEmployee(employeeDTO);
            return new ResponseEntity<>(employeeDTO, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }
}
