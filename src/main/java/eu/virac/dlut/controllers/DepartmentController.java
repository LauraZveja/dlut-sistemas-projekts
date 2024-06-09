package eu.virac.dlut.controllers;

import eu.virac.dlut.models.helpers.DepartmentDTO;
import eu.virac.dlut.services.IDepartmentService;
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
@RequestMapping("/dlut/department")
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;

    @Autowired
    IUserManageService userManage;

    @PostMapping("/addNew")
    public ResponseEntity<?> addNewDepartment(@RequestHeader HttpHeaders headers, @RequestBody @Valid DepartmentDTO departmentDTO) {
        return TokenValidationUtil.handleRequest(userManage, headers, () -> {
            try {
                departmentService.insertDepartment(departmentDTO);
                return new ResponseEntity<>(departmentDTO, HttpStatusCode.valueOf(200));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
            }
        });
    }

    @GetMapping("/showAll")
    public ResponseEntity<?> showAllDepartments(@RequestHeader HttpHeaders headers) {
        return TokenValidationUtil.handleRequest(userManage, headers, () ->
                new ResponseEntity<>(departmentService.retrieveAllDataForDepartments(), HttpStatusCode.valueOf(200))
        );
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDepartment(@RequestHeader HttpHeaders headers, @RequestBody @Valid DepartmentDTO departmentDTO) {
        return TokenValidationUtil.handleRequest(userManage, headers, () -> {
            try {
                departmentService.updateDepartmentById(departmentDTO);
                return new ResponseEntity<>(departmentDTO, HttpStatusCode.valueOf(200));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
            }
        });
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDepartment(@RequestHeader HttpHeaders headers, @RequestBody @Valid DepartmentDTO departmentDTO) {
        return TokenValidationUtil.handleRequest(userManage, headers, () -> {
            try {
                departmentService.deleteDepartmentById(departmentDTO);
                return new ResponseEntity<>(departmentService.retrieveAllDataForDepartments(), HttpStatusCode.valueOf(200));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
            }
        });
    }
}
