package eu.virac.dlut.services.impl;

import eu.virac.dlut.models.BaseFin;
import eu.virac.dlut.repos.IBaseFinRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BaseFinServiceImplTest {

    @Mock
    IBaseFinRepo baseFinRepo;

    @InjectMocks
    BaseFinServiceImpl baseFinService = new BaseFinServiceImpl();

    BaseFin baseFin1 = new BaseFin();
    BaseFin baseFin2 = new BaseFin();
    ArrayList<BaseFin> allBaseFins = new ArrayList<>();
    ArrayList<BaseFin> baseFinsForEmployee = new ArrayList<>();

    int year = 2023;
    int month = 6;
    int employeeId = 1;
    int baseFinId = 1;

    public BaseFinServiceImplTest() {
        MockitoAnnotations.openMocks(this);

        allBaseFins.add(baseFin1);
        allBaseFins.add(baseFin2);

        baseFinsForEmployee.add(baseFin1);
    }

    @Test
    void testSelectAllBaseFin() {
        when(baseFinRepo.findAll()).thenReturn(allBaseFins);

        ArrayList<BaseFin> result = baseFinService.selectAllBaseFin();
        assertEquals(2, result.size());
        verify(baseFinRepo, times(1)).findAll();
    }

    @Test
    void testFindByDateAndEmployee() {

        when(baseFinRepo.findByDateAndEmployee(year, month, employeeId)).thenReturn(baseFinsForEmployee);

        ArrayList<BaseFin> result = baseFinService.findByDateAndEmployee(year, month, employeeId);
        assertEquals(1, result.size());
        verify(baseFinRepo, times(1)).findByDateAndEmployee(year, month, employeeId);
    }

    @Test
    void testFindByDateAndEmployeeReturnsEmpty() {

        when(baseFinRepo.findByDateAndEmployee(year, month, employeeId)).thenReturn(new ArrayList<>());

        ArrayList<BaseFin> result = baseFinService.findByDateAndEmployee(year, month, employeeId);
        assertTrue(result.isEmpty());
        verify(baseFinRepo, times(1)).findByDateAndEmployee(year, month, employeeId);
    }

    @Test
    void testSelectOneBaseFinById() throws Exception {

        when(baseFinRepo.existsById(baseFinId)).thenReturn(true);
        when(baseFinRepo.findById(baseFinId)).thenReturn(Optional.of(baseFin1));

        BaseFin result = baseFinService.selectOneAcademicWorkLoadById(baseFinId);
        assertEquals(baseFin1, result);
        verify(baseFinRepo, times(1)).existsById(baseFinId);
        verify(baseFinRepo, times(1)).findById(baseFinId);
    }

    @Test
    void testSelectOneBaseFinByIdThrowsException() {

        when(baseFinRepo.existsById(baseFinId)).thenReturn(false);

        Exception exception = assertThrows(Exception.class, () -> {
            baseFinService.selectOneAcademicWorkLoadById(baseFinId);
        });

        assertEquals("Bāzes id nav pareizs", exception.getMessage());
        verify(baseFinRepo, times(1)).existsById(baseFinId);
        verify(baseFinRepo, times(0)).findById(baseFinId);
    }

}