package eu.virac.dlut.models.helpers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YearMonthEmployeeSelectionDTOTest {

    List<String> years = List.of("2023", "2024");
    List<String> months = List.of("Janvāris", "Februāris");
    List<EmployeeDTO> employees = List.of(new EmployeeDTO("Jānis", "Bērziņš"), new EmployeeDTO("Anna", "Ozola"));

    YearMonthEmployeeSelectionDTO yearMonthEmployeeSelectionDefault = new YearMonthEmployeeSelectionDTO();
    YearMonthEmployeeSelectionDTO yearMonthEmployeeSelectionParameterized = new YearMonthEmployeeSelectionDTO(years, months, employees);

    @Test
    void testDefaultConstructor() {
        assertNotNull(yearMonthEmployeeSelectionDefault);
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals(years, yearMonthEmployeeSelectionParameterized.getYears());
        assertEquals(months, yearMonthEmployeeSelectionParameterized.getMonths());
        assertEquals(employees, yearMonthEmployeeSelectionParameterized.getEmployees());
    }

    @Test
    void testSetAndGetMethods() {
        yearMonthEmployeeSelectionDefault.setYears(years);
        yearMonthEmployeeSelectionDefault.setMonths(months);
        yearMonthEmployeeSelectionDefault.setEmployees(employees);

        assertEquals(years, yearMonthEmployeeSelectionDefault.getYears());
        assertEquals(months, yearMonthEmployeeSelectionDefault.getMonths());
        assertEquals(employees, yearMonthEmployeeSelectionDefault.getEmployees());
    }

    @Test
    void testToString() {
        String expected = "YearMonthEmployeeSelectionDTO(years=[2023, 2024], months=[Janvāris, Februāris], employees=[EmployeeDTO(name=Jānis, surname=Bērziņš, position=null, isElected=false, workContractNoDate=null, departmentName=null, idEmployee=0), EmployeeDTO(name=Anna, surname=Ozola, position=null, isElected=false, workContractNoDate=null, departmentName=null, idEmployee=0)])";
        assertEquals(expected, yearMonthEmployeeSelectionParameterized.toString());
    }

}