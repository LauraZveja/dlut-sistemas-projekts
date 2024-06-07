package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BasicActivitiesItemsTest {

    BasicActivitiesItems basicActivitiesItems = new BasicActivitiesItems("Nosaukums");

    @Test
    void testBasicActivitiesItemsCreation() {
        assertEquals("Nosaukums", basicActivitiesItems.getTitle());
    }

    @Test
    void testSetTitle() {
        basicActivitiesItems.setTitle("Jauns Nosaukums");
        assertEquals("Jauns Nosaukums", basicActivitiesItems.getTitle());
    }

    @Test
    void testBasicActivitiesItemsToString() {
        String expected = "BasicActivitiesItems(idBasActItems=0, title=Nosaukums)";
        assertEquals(expected, basicActivitiesItems.toString());
    }

    @Test
    void testBasicActivitiesColInitialization() {
        BasicActivitiesItems bai = new BasicActivitiesItems();
        bai.setBasicActivitiesCol(new ArrayList<>());
        assertNotNull(bai.getBasicActivitiesCol());
        assertTrue(bai.getBasicActivitiesCol().isEmpty());
    }

    @Test
    void testAddBasicActivity() {
        BasicActivitiesItems bai = new BasicActivitiesItems();
        bai.setBasicActivitiesCol(new ArrayList<>());
        BasicActivities ba = new BasicActivities();
        bai.getBasicActivitiesCol().add(ba);
        assertTrue(bai.getBasicActivitiesCol().contains(ba));
    }

    @Test
    void testRemoveBasicActivity() {
        BasicActivitiesItems bai = new BasicActivitiesItems();
        bai.setBasicActivitiesCol(new ArrayList<>());
        BasicActivities ba = new BasicActivities();
        bai.getBasicActivitiesCol().add(ba);
        bai.getBasicActivitiesCol().remove(ba);
        assertFalse(bai.getBasicActivitiesCol().contains(ba));
    }

}