package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FinanceSourceTest {

    FinanceSource financeSource = new FinanceSource("Finansējuma avots", "FA123");
    FinanceSource fs = new FinanceSource();
    FullTimeEquivalent fullTimeEquivalent = new FullTimeEquivalent();

    @Test
    void testFinanceSourceCreation() {
        assertEquals("Finansējuma avots", financeSource.getTitle());
        assertEquals("FA123", financeSource.getCode());
    }

    @Test
    void testSetTitle() {
        financeSource.setTitle("Jauns nosaukums");
        assertEquals("Jauns nosaukums", financeSource.getTitle());
    }

    @Test
    void testSetCode() {
        financeSource.setCode("JN456");
        assertEquals("JN456", financeSource.getCode());
    }

    @Test
    void testFullTimeEquivalentInitialization() {
        fs.setFullTimeEquivalent(new ArrayList<>());
        assertNotNull(fs.getFullTimeEquivalent());
        assertTrue(fs.getFullTimeEquivalent().isEmpty());
    }

    @Test
    void testAddFullTimeEquivalent() {
        financeSource.setFullTimeEquivalent(new ArrayList<>());
        financeSource.getFullTimeEquivalent().add(fullTimeEquivalent);
        assertTrue(financeSource.getFullTimeEquivalent().contains(fullTimeEquivalent));
    }

    @Test
    void testRemoveFullTimeEquivalent() {
        financeSource.setFullTimeEquivalent(new ArrayList<>());
        financeSource.getFullTimeEquivalent().add(fullTimeEquivalent);
        financeSource.getFullTimeEquivalent().remove(fullTimeEquivalent);
        assertFalse(financeSource.getFullTimeEquivalent().contains(fullTimeEquivalent));
    }

    @Test
    void testFinanceSourceToString() {
        String expected = "FinanceSource(idFinSource=0, title=Finansējuma avots, code=FA123)";
        assertEquals(expected, financeSource.toString());
    }

}