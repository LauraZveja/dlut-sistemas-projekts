package eu.virac.dlut.services.impl;

import eu.virac.dlut.models.AcademicWorkLoad;
import eu.virac.dlut.repos.IAcademicWorkLoadRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AcademicWorkLoadServiceImplTest {

    @Mock
    IAcademicWorkLoadRepo academicWorkLoadRepo;

    @InjectMocks
    AcademicWorkLoadServiceImpl academicWorkLoadService = new AcademicWorkLoadServiceImpl();

    AcademicWorkLoad academicWorkLoad1 = new AcademicWorkLoad();
    AcademicWorkLoad academicWorkLoad2 = new AcademicWorkLoad();
    ArrayList<AcademicWorkLoad> allAcademicWorkLoads = new ArrayList<>();
    ArrayList<AcademicWorkLoad> academicWorkLoadsForEmployee = new ArrayList<>();

    int year = 2023;
    int month = 6;
    int employeeId = 1;

    public AcademicWorkLoadServiceImplTest() {
        MockitoAnnotations.openMocks(this);

        allAcademicWorkLoads.add(academicWorkLoad1);
        allAcademicWorkLoads.add(academicWorkLoad2);

        academicWorkLoadsForEmployee.add(academicWorkLoad1);
    }

    @Test
    void testSelectAllAcademicWorkLoad() {
        when(academicWorkLoadRepo.findAll()).thenReturn(allAcademicWorkLoads);

        ArrayList<AcademicWorkLoad> result = academicWorkLoadService.selectAllAcademicWorkLoad();
        assertEquals(2, result.size());
        verify(academicWorkLoadRepo, times(1)).findAll();
    }

    @Test
    void testSelectAcademicWorkLoadEmployee() {

        when(academicWorkLoadRepo.findByDateAndEmployee(year, month, employeeId)).thenReturn(academicWorkLoadsForEmployee);

        ArrayList<AcademicWorkLoad> result = academicWorkLoadService.selectAcademicWorkLoadEmpolyee(year, month, employeeId);
        assertEquals(1, result.size());
        verify(academicWorkLoadRepo, times(1)).findByDateAndEmployee(year, month, employeeId);
    }

    @Test
    void testSelectAcademicWorkLoadEmployeeReturnsEmpty() {

        when(academicWorkLoadRepo.findByDateAndEmployee(year, month, employeeId)).thenReturn(null);

        ArrayList<AcademicWorkLoad> result = academicWorkLoadService.selectAcademicWorkLoadEmpolyee(year, month, employeeId);
        assertTrue(result.isEmpty());
        verify(academicWorkLoadRepo, times(1)).findByDateAndEmployee(year, month, employeeId);
    }

    @Test
    void testSelectOneAcademicWorkLoadById() throws Exception {
        int academicWorkLoadId = 1;

        when(academicWorkLoadRepo.existsById(academicWorkLoadId)).thenReturn(true);
        when(academicWorkLoadRepo.findById(academicWorkLoadId)).thenReturn(Optional.of(academicWorkLoad1));

        AcademicWorkLoad result = academicWorkLoadService.selectOneAcademicWorkLoadById(academicWorkLoadId);
        assertEquals(academicWorkLoad1, result);
        verify(academicWorkLoadRepo, times(1)).existsById(academicWorkLoadId);
        verify(academicWorkLoadRepo, times(1)).findById(academicWorkLoadId);
    }

    @Test
    void testSelectOneAcademicWorkLoadByIdThrowsException() {
        int academicWorkLoadId = 1;

        when(academicWorkLoadRepo.existsById(academicWorkLoadId)).thenReturn(false);

        Exception exception = assertThrows(Exception.class, () -> {
            academicWorkLoadService.selectOneAcademicWorkLoadById(academicWorkLoadId);
        });

        assertEquals("Akadēmiskās slodzes id nav pareizs", exception.getMessage());
        verify(academicWorkLoadRepo, times(1)).existsById(academicWorkLoadId);
        verify(academicWorkLoadRepo, times(0)).findById(academicWorkLoadId);
    }

}