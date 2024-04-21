package eu.virac.dlut.services;

import eu.virac.dlut.models.helpers.DepartmentDTO;

import java.util.ArrayList;

public interface IDepartmentService {

    DepartmentDTO insertDepartment(DepartmentDTO departmentDTO) throws Exception;

    ArrayList<DepartmentDTO> retrieveAllDataForDepartments();

    DepartmentDTO updateDepartmentById(DepartmentDTO departmentDTO) throws Exception;

    void deleteDepartmentById(DepartmentDTO departmentDTO);
} 
