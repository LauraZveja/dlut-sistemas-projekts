package eu.virac.dlut.services.impl;

import eu.virac.dlut.models.Department;
import eu.virac.dlut.models.helpers.DepartmentDTO;
import eu.virac.dlut.repos.IDepartmentRepo;
import eu.virac.dlut.services.IDepartmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    IDepartmentRepo departmentRepo;

    @Override
    public DepartmentDTO insertDepartment(DepartmentDTO departmentDTO) throws Exception {
        Department existingDepartment = departmentRepo.findByTitle(departmentDTO.getTitle());
        if (existingDepartment != null) {
            throw new Exception("Departaments ar šādu nosaukumu jau eksistē");
        }
        Department department = new Department(departmentDTO.getTitle(), departmentDTO.getAbbreviation());
        departmentRepo.save(department);
        departmentDTO.setIdDepartment(department.getIdDepartment());
        return departmentDTO;
    }

    @Override
    public ArrayList<DepartmentDTO> retrieveAllDataForDepartments() {
        ArrayList<DepartmentDTO> result = new ArrayList<>();
        ArrayList<Department> allDepartments = (ArrayList<Department>) departmentRepo.findAll();

        for (Department department : allDepartments) {
            if (!department.isDeleted()) {
                result.add(new DepartmentDTO(department.getIdDepartment(), department.getTitle(), department.getAbbreviation()));
            }
        }
        return result;
    }

    @Override
    public DepartmentDTO updateDepartmentById(DepartmentDTO departmentDTO) throws Exception {
        Department department = departmentRepo.findById(departmentDTO.getIdDepartment())
                .orElseThrow(() -> new EntityNotFoundException("Šāds departaments nav atrasts"));

        boolean isUpdated = false;

        if (departmentDTO.getTitle() != null && !departmentDTO.getTitle().equals(department.getTitle())) {
            department.setTitle(departmentDTO.getTitle());
            isUpdated = true;
        }
        if (departmentDTO.getAbbreviation() != null && !departmentDTO.getAbbreviation().equals(department.getAbbreviation())) {
            department.setAbbreviation(departmentDTO.getAbbreviation());
            isUpdated = true;
        }
        if (isUpdated) {
            departmentRepo.save(department);
        }

        return new DepartmentDTO(department.getIdDepartment(), department.getTitle(), department.getAbbreviation());
    }

    @Override
    public void deleteDepartmentById(DepartmentDTO departmentDTO) {
        Department department = departmentRepo.findById(departmentDTO.getIdDepartment())
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        department.setDeleted(true);
        departmentRepo.save(department);

    }
}
