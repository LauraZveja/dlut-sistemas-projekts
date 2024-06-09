package eu.virac.dlut.models.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HoursInMonthDTOTest {

    int idHoursInMonth = 1;
    int year = 2024;
    int month = 6;
    double hoursInMonth = 160.0;

    HoursInMonthDTO hoursInMonthDefault = new HoursInMonthDTO();
    HoursInMonthDTO hoursInMonthParameterized = new HoursInMonthDTO(idHoursInMonth, year, month, hoursInMonth);

    @Test
    void testDefaultConstructor() {
        assertNotNull(hoursInMonthDefault);
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals(idHoursInMonth, hoursInMonthParameterized.getIdHoursInMonth());
        assertEquals(year, hoursInMonthParameterized.getYear());
        assertEquals(month, hoursInMonthParameterized.getMonth());
        assertEquals(hoursInMonth, hoursInMonthParameterized.getHoursInMonth());
    }

    @Test
    void testSetAndGetMethods() {
        hoursInMonthDefault.setIdHoursInMonth(idHoursInMonth);
        hoursInMonthDefault.setYear(year);
        hoursInMonthDefault.setMonth(month);
        hoursInMonthDefault.setHoursInMonth(hoursInMonth);

        assertEquals(idHoursInMonth, hoursInMonthDefault.getIdHoursInMonth());
        assertEquals(year, hoursInMonthDefault.getYear());
        assertEquals(month, hoursInMonthDefault.getMonth());
        assertEquals(hoursInMonth, hoursInMonthDefault.getHoursInMonth());
    }

    @Test
    void testToString() {
        String expected = "HoursInMonthDTO(idHoursInMonth=1, year=2024, month=6, hoursInMonth=160.0)";
        assertEquals(expected, hoursInMonthParameterized.toString());
    }

}