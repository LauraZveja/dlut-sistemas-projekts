package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HoursInMonthTest {


    int year = 2024;
    int newYear = 2025;
    int month = 6;
    int newMonth = 7;
    double hoursInMonth = 160.0;
    double newHoursInMonth = 170.0;

    HoursInMonth hoursInMonthInstance = new HoursInMonth(year, month, hoursInMonth);

    @Test
    void testHoursInMonthCreation() {
        assertEquals(year, hoursInMonthInstance.getYear());
        assertEquals(month, hoursInMonthInstance.getMonth());
        assertEquals(hoursInMonth, hoursInMonthInstance.getHoursInMonth());
    }

    @Test
    void testSetYear() {
        hoursInMonthInstance.setYear(newYear);
        assertEquals(newYear, hoursInMonthInstance.getYear());
    }

    @Test
    void testSetMonth() {
        hoursInMonthInstance.setMonth(newMonth);
        assertEquals(newMonth, hoursInMonthInstance.getMonth());
    }

    @Test
    void testSetHoursInMonth() {
        hoursInMonthInstance.setHoursInMonth(newHoursInMonth);
        assertEquals(newHoursInMonth, hoursInMonthInstance.getHoursInMonth());
    }

    @Test
    void testHoursInMonthToString() {
        String expected = "HoursInMonth(idHoursInMonth=0, year=2024, month=6, hoursInMonth=160.0)";
        assertEquals(expected, hoursInMonthInstance.toString());
    }

}