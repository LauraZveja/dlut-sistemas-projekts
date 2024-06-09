package eu.virac.dlut.models.helpers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableResultEditingDTOTest {

    List<EmployeeAndHourDTO> results = new ArrayList<>();
    EmployeeAndHourDTO employeeAndHour = new EmployeeAndHourDTO();

    TableResultEditingDTO tableResultEditingDefault = new TableResultEditingDTO();
    TableResultEditingDTO tableResultEditingParameterized = new TableResultEditingDTO(results);

    @Test
    void testDefaultConstructor() {
        assertNotNull(tableResultEditingDefault);
        assertNotNull(tableResultEditingDefault.getResults());
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals(results, tableResultEditingParameterized.getResults());
    }

    @Test
    void testAddResults() {
        tableResultEditingDefault.addResults(employeeAndHour);
        assertTrue(tableResultEditingDefault.getResults().contains(employeeAndHour));
    }

    @Test
    void testSetAndGetMethods() {
        tableResultEditingDefault.setResults(results);
        assertEquals(results, tableResultEditingDefault.getResults());
    }

    @Test
    void testResultsListNotNull() {
        assertNotNull(tableResultEditingDefault.getResults());
    }

}