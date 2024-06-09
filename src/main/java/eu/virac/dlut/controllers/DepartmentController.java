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

    private final IDepartmentService departmentService;
    private final IUserManageService userManage;

    @Autowired
    public DepartmentController(IDepartmentService departmentService, IUserManageService userManage) {
        this.departmentService = departmentService;
        this.userManage = userManage;
    }

    @PostMapping("/addNew")
    public ResponseEntity<Object> addNewDepartment(@RequestHeader HttpHeaders headers, @RequestBody @Valid DepartmentDTO departmentDTO) {
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
    public ResponseEntity<Object> showAllDepartments(@RequestHeader HttpHeaders headers) {
        return TokenValidationUtil.handleRequest(userManage, headers, () ->
                new ResponseEntity<>(departmentService.retrieveAllDataForDepartments(), HttpStatusCode.valueOf(200))
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateDepartment(@RequestHeader HttpHeaders headers, @RequestBody @Valid DepartmentDTO departmentDTO) {
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
    public ResponseEntity<Object> deleteDepartment(@RequestHeader HttpHeaders headers, @RequestBody @Valid DepartmentDTO departmentDTO) {
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
