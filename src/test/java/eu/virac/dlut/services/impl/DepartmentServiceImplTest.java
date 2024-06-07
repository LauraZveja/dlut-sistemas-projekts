package eu.virac.dlut.services.impl;

import eu.virac.dlut.models.Department;
import eu.virac.dlut.models.helpers.DepartmentDTO;
import eu.virac.dlut.repos.IDepartmentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceImplTest {

    @Mock
    IDepartmentRepo departmentRepo;

    @InjectMocks
    DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    DepartmentDTO departmentDTO = new DepartmentDTO();
    Department existingDepartment = new Department();
    Department department = new Department();
    ArrayList<Department> allDepartments = new ArrayList<>();
    Department department1 = new Department();
    Department department2 = new Department();

    public DepartmentServiceImplTest() {
        MockitoAnnotations.openMocks(this);

        // Initialize objects with Latvian values
        departmentDTO.setTitle("Jauns Departaments");
        departmentDTO.setAbbreviation("JD");

        existingDepartment.setTitle("Eksistējošs Departaments");

        department.idDepartment = 1;

        department1.idDepartment = 1;
        department1.setTitle("Departaments 1");
        department1.setAbbreviation("D1");

        department2.idDepartment = 2;
        department2.setTitle("Departaments 2");
        department2.setAbbreviation("D2");

        allDepartments.add(department1);
        allDepartments.add(department2);
    }

    @Test
    void testInsertDepartment() throws Exception {
        when(departmentRepo.findByTitle(departmentDTO.getTitle())).thenReturn(null);

        Department departmentToSave = new Department(departmentDTO.getTitle(), departmentDTO.getAbbreviation());
        departmentToSave.idDepartment = 0;
        when(departmentRepo.save(any(Department.class))).thenReturn(departmentToSave);

        DepartmentDTO result = departmentService.insertDepartment(departmentDTO);
        assertEquals(0, result.getIdDepartment());
        verify(departmentRepo, times(1)).save(any(Department.class));
    }

    @Test
    void testInsertDepartmentAlreadyExists() {
        when(departmentRepo.findByTitle(departmentDTO.getTitle())).thenReturn(existingDepartment);

        Exception exception = assertThrows(Exception.class, () -> {
            departmentService.insertDepartment(departmentDTO);
        });

        assertEquals("Departaments ar šādu nosaukumu jau eksistē", exception.getMessage());
        verify(departmentRepo, times(0)).save(any(Department.class));
    }

    @Test
    void testRetrieveAllDataForDepartments() {
        when(departmentRepo.findAll()).thenReturn(allDepartments);

        ArrayList<DepartmentDTO> result = departmentService.retrieveAllDataForDepartments();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateDepartmentById() throws Exception {
        departmentDTO.setIdDepartment(1);
        departmentDTO.setTitle("Jauns Nosaukums");
        departmentDTO.setAbbreviation("JN");

        department.setTitle("Vecs Nosaukums");
        department.setAbbreviation("VN");

        when(departmentRepo.findById(departmentDTO.getIdDepartment())).thenReturn(Optional.of(department));
        when(departmentRepo.save(any(Department.class))).thenReturn(department);

        DepartmentDTO result = departmentService.updateDepartmentById(departmentDTO);
        assertEquals("Jauns Nosaukums", result.getTitle());
        assertEquals("JN", result.getAbbreviation());
        verify(departmentRepo, times(1)).save(any(Department.class));
    }

    @Test
    void testUpdateDepartmentByIdNotFound() {
        departmentDTO.setIdDepartment(1);

        when(departmentRepo.findById(departmentDTO.getIdDepartment())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            departmentService.updateDepartmentById(departmentDTO);
        });

        assertEquals("Šāds departaments nav atrasts", exception.getMessage());
        verify(departmentRepo, times(0)).save(any(Department.class));
    }

    @Test
    void testDeleteDepartmentById() {
        departmentDTO.setIdDepartment(1);

        when(departmentRepo.findById(departmentDTO.getIdDepartment())).thenReturn(Optional.of(department));

        departmentService.deleteDepartmentById(departmentDTO);
        assertTrue(department.isDeleted());
        verify(departmentRepo, times(1)).save(department);
    }

    @Test
    void testDeleteDepartmentByIdNotFound() {
        departmentDTO.setIdDepartment(1);

        when(departmentRepo.findById(departmentDTO.getIdDepartment())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            departmentService.deleteDepartmentById(departmentDTO);
        });

        assertEquals("Department not found", exception.getMessage());
        verify(departmentRepo, times(0)).save(any(Department.class));
    }

}