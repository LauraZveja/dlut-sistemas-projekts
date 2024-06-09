package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseFinTest {

    BaseFin baseFin = new BaseFin("Nosaukums", "NM123");

    @Test
    void testBaseFinCreation() {
        assertEquals("Nosaukums", baseFin.getTitle());
        assertEquals("NM123", baseFin.getCode());
    }

    @Test
    void testAddFinancePlanningToBaseFin() {
        FinancePlanning fp = new FinancePlanning();
        baseFin.addFinancePlanningToBaseFin(fp);
        assertNotNull(baseFin.getFinPlanningCol());
        assertTrue(baseFin.getFinPlanningCol().contains(fp));
    }

    @Test
    void testRemoveFinancePlanningFromBaseFin() {
        FinancePlanning fp = new FinancePlanning();
        baseFin.addFinancePlanningToBaseFin(fp);
        baseFin.removeFinancePlanningFromBaseFin(fp);
        assertNotNull(baseFin.getFinPlanningCol());
        assertFalse(baseFin.getFinPlanningCol().contains(fp));
    }

    @Test
    void testAddFinanceOrderVacationToBaseFin() {
        FinanceOrderVacation fov = new FinanceOrderVacation();
        baseFin.addFinanceOrderVacationToBaseFin(fov);
        assertNotNull(baseFin.getFinOrderVacCol());
        assertTrue(baseFin.getFinOrderVacCol().contains(fov));
    }

    @Test
    void testRemoveFinanceOrderVacationFromBaseFin() {
        FinanceOrderVacation fov = new FinanceOrderVacation();
        baseFin.addFinanceOrderVacationToBaseFin(fov);
        baseFin.removeFinanceOrderVacationFromBaseFin(fov);
        assertNotNull(baseFin.getFinOrderVacCol());
        assertFalse(baseFin.getFinOrderVacCol().contains(fov));
    }

    @Test
    void testAddFinanceOrderWorkToBaseFin() {
        FinanceOrderWork fow = new FinanceOrderWork();
        baseFin.addFinanceOrderWorkToBaseFin(fow);
        assertNotNull(baseFin.getFinOrderWorkCol());
        assertTrue(baseFin.getFinOrderWorkCol().contains(fow));
    }

    @Test
    void testRemoveFinanceOrderWorkFromBaseFin() {
        FinanceOrderWork fow = new FinanceOrderWork();
        baseFin.addFinanceOrderWorkToBaseFin(fow);
        baseFin.removeFinanceOrderWorkFromBaseFin(fow);
        assertNotNull(baseFin.getFinOrderWorkCol());
        assertFalse(baseFin.getFinOrderWorkCol().contains(fow));
    }

    @Test
    void testFinPlanningColInitialization() {
        BaseFin bf = new BaseFin();
        assertNotNull(bf.getFinPlanningCol());
        assertTrue(bf.getFinPlanningCol().isEmpty());
    }

    @Test
    void testFinOrderVacColInitialization() {
        BaseFin bf = new BaseFin();
        assertNotNull(bf.getFinOrderVacCol());
        assertTrue(bf.getFinOrderVacCol().isEmpty());
    }

    @Test
    void testFinOrderWorkColInitialization() {
        BaseFin bf = new BaseFin();
        assertNotNull(bf.getFinOrderWorkCol());
        assertTrue(bf.getFinOrderWorkCol().isEmpty());
    }

    @Test
    void testBaseFinToString() {
        String expected = "BaseFin()";
        assertEquals(expected, baseFin.toString());
    }

}