package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicActivitiesTest {

    FinanceOrderWork finOrdWork = new FinanceOrderWork();
    BasicActivitiesItems basActItem = new BasicActivitiesItems();
    BasicActivities basicActivities = new BasicActivities(finOrdWork, basActItem);

    @Test
    void testBasicActivitiesCreation() {
        assertEquals(finOrdWork, basicActivities.getFinOrdWork());
        assertEquals(basActItem, basicActivities.getBasActItem());
    }

    @Test
    void testSetFinOrdWork() {
        FinanceOrderWork newFinOrdWork = new FinanceOrderWork();
        basicActivities.setFinOrdWork(newFinOrdWork);
        assertEquals(newFinOrdWork, basicActivities.getFinOrdWork());
    }

    @Test
    void testSetBasActItem() {
        BasicActivitiesItems newBasActItem = new BasicActivitiesItems();
        basicActivities.setBasActItem(newBasActItem);
        assertEquals(newBasActItem, basicActivities.getBasActItem());
    }

    @Test
    void testBasicActivitiesToString() {
        String expected = "BasicActivities(idBaseAct=0, finOrdWork=" + finOrdWork.toString() + ", basActItem=" + basActItem.toString() + ")";
        assertEquals(expected, basicActivities.toString());
    }

}