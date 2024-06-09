package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FinanceSourceTypeTest {

    FinanceSourceType financeSourceType = new FinanceSourceType("Pētnieciskais");
    FinanceOrderWork financeOrderWork = new FinanceOrderWork();

    @Test
    void testFinanceSourceTypeCreation() {
        assertEquals("Pētnieciskais", financeSourceType.getType());
    }

    @Test
    void testSetType() {
        financeSourceType.setType("Akadēmiskais");
        assertEquals("Akadēmiskais", financeSourceType.getType());
    }

    @Test
    void testFinOrderWorkColInitialization() {
        FinanceSourceType fst = new FinanceSourceType();
        fst.setFinOrderWorkCol(new ArrayList<>());
        assertNotNull(fst.getFinOrderWorkCol());
        assertTrue(fst.getFinOrderWorkCol().isEmpty());
    }

    @Test
    void testAddFinanceOrderWork() {
        financeSourceType.setFinOrderWorkCol(new ArrayList<>());
        financeSourceType.addFinanceOrderWorkToFinanceSourceType(financeOrderWork);
        assertTrue(financeSourceType.getFinOrderWorkCol().contains(financeOrderWork));
    }

    @Test
    void testRemoveFinanceOrderWork() {
        financeSourceType.setFinOrderWorkCol(new ArrayList<>());
        financeSourceType.addFinanceOrderWorkToFinanceSourceType(financeOrderWork);
        financeSourceType.removeFinanceOrderWorkFromFinanceSourceType(financeOrderWork);
        assertFalse(financeSourceType.getFinOrderWorkCol().contains(financeOrderWork));
    }

    @Test
    void testFinanceSourceTypeToString() {
        String expected = "FinanceSourceType(idFinSourceType=0, type=Pētnieciskais)";
        assertEquals(expected, financeSourceType.toString());
    }

}