package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IndirectVUASTest {

    String title = "VeA netiešās";
    String code = "VUA123";
    IndirectVUAS indirectVUAS = new IndirectVUAS(title, code);

    FinanceOrderWork financeOrderWork = new FinanceOrderWork();
    FinanceOrderVacation financeOrderVacation = new FinanceOrderVacation();
    FinancePlanning financePlanning = new FinancePlanning();

    @Test
    void testIndirectVUASCreation() {
        assertEquals(title, indirectVUAS.getTitle());
        assertEquals(code, indirectVUAS.getCode());
    }

    @Test
    void testSetTitle() {
        String newTitle = "Jauns nosaukums";
        indirectVUAS.setTitle(newTitle);
        assertEquals(newTitle, indirectVUAS.getTitle());
    }

    @Test
    void testSetCode() {
        String newCode = "JN456";
        indirectVUAS.setCode(newCode);
        assertEquals(newCode, indirectVUAS.getCode());
    }

    @Test
    void testFinOrderWorkColInitialization() {
        IndirectVUAS ivuas = new IndirectVUAS();
        ivuas.setFinOrderWorkCol(new ArrayList<>());
        assertNotNull(ivuas.getFinOrderWorkCol());
        assertTrue(ivuas.getFinOrderWorkCol().isEmpty());
    }

    @Test
    void testAddFinanceOrderWork() {
        indirectVUAS.setFinOrderWorkCol(new ArrayList<>());
        indirectVUAS.addFinanceOrderWorkToIndirectVUAS(financeOrderWork);
        assertTrue(indirectVUAS.getFinOrderWorkCol().contains(financeOrderWork));
    }

    @Test
    void testRemoveFinanceOrderWork() {
        indirectVUAS.setFinOrderWorkCol(new ArrayList<>());
        indirectVUAS.addFinanceOrderWorkToIndirectVUAS(financeOrderWork);
        indirectVUAS.removeFinanceOrderWorkFromIndirectVUAS(financeOrderWork);
        assertFalse(indirectVUAS.getFinOrderWorkCol().contains(financeOrderWork));
    }

    @Test
    void testFinOrderVacColInitialization() {
        IndirectVUAS ivuas = new IndirectVUAS();
        ivuas.setFinOrderVacCol(new ArrayList<>());
        assertNotNull(ivuas.getFinOrderVacCol());
        assertTrue(ivuas.getFinOrderVacCol().isEmpty());
    }

    @Test
    void testAddFinanceOrderVacation() {
        indirectVUAS.setFinOrderVacCol(new ArrayList<>());
        indirectVUAS.addFinanceOrderVacationToIndirectVUAS(financeOrderVacation);
        assertTrue(indirectVUAS.getFinOrderVacCol().contains(financeOrderVacation));
    }

    @Test
    void testRemoveFinanceOrderVacation() {
        indirectVUAS.setFinOrderVacCol(new ArrayList<>());
        indirectVUAS.addFinanceOrderVacationToIndirectVUAS(financeOrderVacation);
        indirectVUAS.removeFinanceOrderVacationFromIndirectVUAS(financeOrderVacation);
        assertFalse(indirectVUAS.getFinOrderVacCol().contains(financeOrderVacation));
    }

    @Test
    void testFinPlanningColInitialization() {
        IndirectVUAS ivuas = new IndirectVUAS();
        ivuas.setFinPlanningCol(new ArrayList<>());
        assertNotNull(ivuas.getFinPlanningCol());
        assertTrue(ivuas.getFinPlanningCol().isEmpty());
    }

    @Test
    void testAddFinancePlanning() {
        indirectVUAS.setFinPlanningCol(new ArrayList<>());
        indirectVUAS.addFinancePlanningToIndirectVUAS(financePlanning);
        assertTrue(indirectVUAS.getFinPlanningCol().contains(financePlanning));
    }

    @Test
    void testRemoveFinancePlanning() {
        indirectVUAS.setFinPlanningCol(new ArrayList<>());
        indirectVUAS.addFinancePlanningToIndirectVUAS(financePlanning);
        indirectVUAS.removeFinancePlanningFromIndirectVUAS(financePlanning);
        assertFalse(indirectVUAS.getFinPlanningCol().contains(financePlanning));
    }

    @Test
    void testIndirectVUASToString() {
        String expected = "IndirectVUAS()";
        assertEquals(expected, indirectVUAS.toString());
    }

}