package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcademicWorkloadTest {

    AcademicWorkLoad acWorkLoad = new AcademicWorkLoad("Nosaukums", "NM123");

    @Test
    void testAcademicWorkLoadCreation() {
        assertEquals("Nosaukums", acWorkLoad.getTitle());
        assertEquals("NM123", acWorkLoad.getCode());
    }

    @Test
    void testAddFinancePlanningToAcademicWorkLoad() {
        FinancePlanning fp = new FinancePlanning();
        acWorkLoad.addFinancePlanningToAcademicWorkLoad(fp);
        assertNotNull(acWorkLoad.getFinPlanningCol());
        assertTrue(acWorkLoad.getFinPlanningCol().contains(fp));
    }

    @Test
    void testRemoveFinancePlanningFromAcademicWorkLoad() {
        FinancePlanning fp = new FinancePlanning();
        acWorkLoad.addFinancePlanningToAcademicWorkLoad(fp);
        acWorkLoad.removeFinancePlanningFromAcademicWorkLoad(fp);
        assertNotNull(acWorkLoad.getFinPlanningCol());
        assertFalse(acWorkLoad.getFinPlanningCol().contains(fp));
    }

    @Test
    void testAddFinanceOrderVacationToAcademicWorkLoad() {
        FinanceOrderVacation fov = new FinanceOrderVacation();
        acWorkLoad.addFinanceOrderVacationToAcademicWorkLoad(fov);
        assertNotNull(acWorkLoad.getFinOrderVacCol());
        assertTrue(acWorkLoad.getFinOrderVacCol().contains(fov));
    }

    @Test
    void testRemoveFinanceOrderVacationFromAcademicWorkLoad() {
        FinanceOrderVacation fov = new FinanceOrderVacation();
        acWorkLoad.addFinanceOrderVacationToAcademicWorkLoad(fov);
        acWorkLoad.removeFinanceOrderVacationFromAcademicWorkLoad(fov);
        assertNotNull(acWorkLoad.getFinOrderVacCol());
        assertFalse(acWorkLoad.getFinOrderVacCol().contains(fov));
    }

    @Test
    void testAddFinanceOrderWorkToAcademicWorkLoad() {
        FinanceOrderWork fow = new FinanceOrderWork();
        acWorkLoad.addFinanceOrderWorkToAcademicWorkLoad(fow);
        assertNotNull(acWorkLoad.getFinOrderWorkCol());
        assertTrue(acWorkLoad.getFinOrderWorkCol().contains(fow));
    }

    @Test
    void testRemoveFinanceOrderWorkFromAcademicWorkLoad() {
        FinanceOrderWork fow = new FinanceOrderWork();
        acWorkLoad.addFinanceOrderWorkToAcademicWorkLoad(fow);
        acWorkLoad.removeFinanceOrderWorkFromAcademicWorkLoad(fow);
        assertNotNull(acWorkLoad.getFinOrderWorkCol());
        assertFalse(acWorkLoad.getFinOrderWorkCol().contains(fow));
    }

    @Test
    void testFinPlanningColInitialization() {
        AcademicWorkLoad acwl = new AcademicWorkLoad();
        assertNotNull(acwl.getFinPlanningCol());
        assertTrue(acwl.getFinPlanningCol().isEmpty());
    }

    @Test
    void testFinOrderVacColInitialization() {
        AcademicWorkLoad acwl = new AcademicWorkLoad();
        assertNotNull(acwl.getFinOrderVacCol());
        assertTrue(acwl.getFinOrderVacCol().isEmpty());
    }

    @Test
    void testFinOrderWorkColInitialization() {
        AcademicWorkLoad acwl = new AcademicWorkLoad();
        assertNotNull(acwl.getFinOrderWorkCol());
        assertTrue(acwl.getFinOrderWorkCol().isEmpty());
    }

    @Test
    void testAcademicWorkLoadToString() {
        String expected = "AcademicWorkLoad()";
        assertEquals(expected, acWorkLoad.toString());
    }

}