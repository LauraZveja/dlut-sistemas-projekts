package eu.virac.dlut.controllers;

import eu.virac.dlut.models.helpers.DepartmentDTO;
import eu.virac.dlut.services.IDepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dlut/department")
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;

    @PostMapping("/addNew")
    public ResponseEntity<?> addNewDepartment(@RequestBody @Valid DepartmentDTO DepartmentDTO) {
        try {
            departmentService.insertDepartment(DepartmentDTO);
            return new ResponseEntity<>(DepartmentDTO, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/showAll")
    public ResponseEntity<ArrayList<DepartmentDTO>> showAllDepartments() {
        return new ResponseEntity<ArrayList<DepartmentDTO>>(departmentService.retrieveAllDataForDepartments(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDepartment(@RequestBody @Valid DepartmentDTO DepartmentDTO) {
        try {
            departmentService.updateDepartmentById(DepartmentDTO);
            return new ResponseEntity<>(DepartmentDTO, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDepartment(@RequestBody @Valid DepartmentDTO DepartmentDTO) {
        try {
            departmentService.deleteDepartmentById(DepartmentDTO);
            return new ResponseEntity<>(departmentService.retrieveAllDataForDepartments(), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }
}
