package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HolidayPreholidayTransferedHolidayTest {

    LocalDate date = LocalDate.of(2024, 12, 25);
    LocalDate newDate = LocalDate.of(2025, 1, 1);
    DayStatus dayStatus = new DayStatus("Svētku diena");
    DayStatus newDayStatus = new DayStatus("Pirmssvētku diena");

    HolidayPreholidayTransferedHoliday holiday = new HolidayPreholidayTransferedHoliday(date, dayStatus);

    @Test
    void testHolidayPreholidayTransferedHolidayCreation() {
        assertEquals(date, holiday.getDate());
        assertEquals(dayStatus, holiday.getDayStatus());
    }

    @Test
    void testSetDate() {
        holiday.setDate(newDate);
        assertEquals(newDate, holiday.getDate());
    }

    @Test
    void testSetDayStatus() {
        holiday.setDayStatus(newDayStatus);
        assertEquals(newDayStatus, holiday.getDayStatus());
    }

    @Test
    void testHolidayPreholidayTransferedHolidayToString() {
        String expected = "HolidayPreholidayTransferedHoliday(idDay=0, date=2024-12-25, dayStatus=DayStatus(idDayStatus=0, dayTitle=Svētku diena))";
        assertEquals(expected, holiday.toString());
    }

}