package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinanceOrderVacationTest {

    Project project = new Project();
    AcademicWorkLoad academicWorkLoad = new AcademicWorkLoad();
    BaseFin baseFin = new BaseFin();
    IndirectVUAS indirectVUAS = new IndirectVUAS();
    OrderVacation orderVacation = new OrderVacation();

    FinanceOrderVacation financeOrderVacationWithProject = new FinanceOrderVacation(project, orderVacation);
    FinanceOrderVacation financeOrderVacationWithAcademicWorkLoad = new FinanceOrderVacation(academicWorkLoad, orderVacation);
    FinanceOrderVacation financeOrderVacationWithBaseFin = new FinanceOrderVacation(baseFin, orderVacation);
    FinanceOrderVacation financeOrderVacationWithIndirectVUAS = new FinanceOrderVacation(indirectVUAS, orderVacation);

    @Test
    void testFinanceOrderVacationCreationWithProject() {
        assertEquals(project, financeOrderVacationWithProject.getProject());
        assertEquals(orderVacation, financeOrderVacationWithProject.getOrdVacation());
    }

    @Test
    void testFinanceOrderVacationCreationWithAcademicWorkLoad() {
        assertEquals(academicWorkLoad, financeOrderVacationWithAcademicWorkLoad.getAcWorkLoad());
        assertEquals(orderVacation, financeOrderVacationWithAcademicWorkLoad.getOrdVacation());
    }

    @Test
    void testFinanceOrderVacationCreationWithBaseFin() {
        assertEquals(baseFin, financeOrderVacationWithBaseFin.getBaseFin());
        assertEquals(orderVacation, financeOrderVacationWithBaseFin.getOrdVacation());
    }

    @Test
    void testFinanceOrderVacationCreationWithIndirectVUAS() {
        assertEquals(indirectVUAS, financeOrderVacationWithIndirectVUAS.getIndVUAS());
        assertEquals(orderVacation, financeOrderVacationWithIndirectVUAS.getOrdVacation());
    }

    @Test
    void testSettersAndGetters() {
        FinanceOrderVacation financeOrderVacation = new FinanceOrderVacation();

        financeOrderVacation.setProject(project);
        financeOrderVacation.setAcWorkLoad(academicWorkLoad);
        financeOrderVacation.setBaseFin(baseFin);
        financeOrderVacation.setIndVUAS(indirectVUAS);
        financeOrderVacation.setOrdVacation(orderVacation);

        assertEquals(project, financeOrderVacation.getProject());
        assertEquals(academicWorkLoad, financeOrderVacation.getAcWorkLoad());
        assertEquals(baseFin, financeOrderVacation.getBaseFin());
        assertEquals(indirectVUAS, financeOrderVacation.getIndVUAS());
        assertEquals(orderVacation, financeOrderVacation.getOrdVacation());
    }

    @Test
    void testFinanceOrderVacationToString() {
        String expected = "FinanceOrderVacation(idFinOrdVac=0, project=null, acWorkLoad=null, baseFin=null, indVUAS=null, ordVacation=null)";
        FinanceOrderVacation financeOrderVacation = new FinanceOrderVacation();
        assertEquals(expected, financeOrderVacation.toString());
    }

}