package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DayStatusTest {

    DayStatus dayStatus = new DayStatus("Svētku diena");

    @Test
    void testDayStatusCreation() {
        assertEquals("Svētku diena", dayStatus.getDayTitle());
    }

    @Test
    void testSetDayTitle() {
        dayStatus.setDayTitle("Jauns Nosaukums");
        assertEquals("Jauns Nosaukums", dayStatus.getDayTitle());
    }

    @Test
    void testDayStatusToString() {
        String expected = "DayStatus(idDayStatus=0, dayTitle=Svētku diena)";
        assertEquals(expected, dayStatus.toString());
    }

    @Test
    void testHolidaysInitialization() {
        DayStatus ds = new DayStatus();
        ds.setHolidays(new ArrayList<>());
        assertNotNull(ds.getHolidays());
        assertTrue(ds.getHolidays().isEmpty());
    }

    @Test
    void testAddHoliday() {
        DayStatus ds = new DayStatus();
        ds.setHolidays(new ArrayList<>());
        HolidayPreholidayTransferedHoliday holiday = new HolidayPreholidayTransferedHoliday();
        ds.addHolidayPreholidayTransferedHolidayToDayStatus(holiday);
        assertTrue(ds.getHolidays().contains(holiday));
    }

    @Test
    void testRemoveHoliday() {
        DayStatus ds = new DayStatus();
        ds.setHolidays(new ArrayList<>());
        HolidayPreholidayTransferedHoliday holiday = new HolidayPreholidayTransferedHoliday();
        ds.addHolidayPreholidayTransferedHolidayToDayStatus(holiday);
        ds.removeHolidayPreholidayTransferedHolidayFromDayStatus(holiday);
        assertFalse(ds.getHolidays().contains(holiday));
    }

}