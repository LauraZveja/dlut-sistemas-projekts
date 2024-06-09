package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FinanceOrderWorkTest {

    Project project = new Project();
    AcademicWorkLoad academicWorkLoad = new AcademicWorkLoad();
    BaseFin baseFin = new BaseFin();
    IndirectVUAS indirectVUAS = new IndirectVUAS();
    OrderWork orderWork = new OrderWork();
    LocalDate endDate = LocalDate.of(2024, 12, 31);
    FinanceSourceType financeSourceType = new FinanceSourceType();

    FinanceOrderWork financeOrderWorkWithProject = new FinanceOrderWork(
            project, orderWork, endDate, 160, 1.0, 25.0, financeSourceType, "Projektu vadītājs");
    FinanceOrderWork financeOrderWorkWithAcademicWorkLoad = new FinanceOrderWork(
            academicWorkLoad, orderWork, endDate, 160, 1.0, 25.0, financeSourceType, "Pētnieks");
    FinanceOrderWork financeOrderWorkWithBaseFin = new FinanceOrderWork(
            baseFin, orderWork, endDate, 160, 1.0, 25.0, financeSourceType, "Asistents");
    FinanceOrderWork financeOrderWorkWithIndirectVUAS = new FinanceOrderWork(
            indirectVUAS, orderWork, endDate, 160, 1.0, 25.0, financeSourceType, "Inženieris");
    FinanceOrderWork financeOrderWork = new FinanceOrderWork();
    BasicActivities basicActivity = new BasicActivities();
    EmployeeTimeSheet employeeTimeSheet = new EmployeeTimeSheet();

    @Test
    void testFinanceOrderWorkCreationWithProject() {
        assertEquals(project, financeOrderWorkWithProject.getProject());
        assertEquals(orderWork, financeOrderWorkWithProject.getOrdWork());
        assertEquals(endDate, financeOrderWorkWithProject.getEndDateProjAsPerContract());
        assertEquals(160, financeOrderWorkWithProject.getMaxHours());
        assertEquals(1.0, financeOrderWorkWithProject.getWorkLoad());
        assertEquals(25.0, financeOrderWorkWithProject.getPayPerHour());
        assertEquals(financeSourceType, financeOrderWorkWithProject.getFinSourceType());
        assertEquals("Projektu vadītājs", financeOrderWorkWithProject.getPositionInProject());
    }

    @Test
    void testFinanceOrderWorkCreationWithAcademicWorkLoad() {
        assertEquals(academicWorkLoad, financeOrderWorkWithAcademicWorkLoad.getAcWorkLoad());
        assertEquals(orderWork, financeOrderWorkWithAcademicWorkLoad.getOrdWork());
        assertEquals(endDate, financeOrderWorkWithAcademicWorkLoad.getEndDateProjAsPerContract());
        assertEquals(160, financeOrderWorkWithAcademicWorkLoad.getMaxHours());
        assertEquals(1.0, financeOrderWorkWithAcademicWorkLoad.getWorkLoad());
        assertEquals(25.0, financeOrderWorkWithAcademicWorkLoad.getPayPerHour());
        assertEquals(financeSourceType, financeOrderWorkWithAcademicWorkLoad.getFinSourceType());
        assertEquals("Pētnieks", financeOrderWorkWithAcademicWorkLoad.getPositionInProject());
    }

    @Test
    void testFinanceOrderWorkCreationWithBaseFin() {
        assertEquals(baseFin, financeOrderWorkWithBaseFin.getBaseFin());
        assertEquals(orderWork, financeOrderWorkWithBaseFin.getOrdWork());
        assertEquals(endDate, financeOrderWorkWithBaseFin.getEndDateProjAsPerContract());
        assertEquals(160, financeOrderWorkWithBaseFin.getMaxHours());
        assertEquals(1.0, financeOrderWorkWithBaseFin.getWorkLoad());
        assertEquals(25.0, financeOrderWorkWithBaseFin.getPayPerHour());
        assertEquals(financeSourceType, financeOrderWorkWithBaseFin.getFinSourceType());
        assertEquals("Asistents", financeOrderWorkWithBaseFin.getPositionInProject());
    }

    @Test
    void testFinanceOrderWorkCreationWithIndirectVUAS() {
        assertEquals(indirectVUAS, financeOrderWorkWithIndirectVUAS.getIndVUAS());
        assertEquals(orderWork, financeOrderWorkWithIndirectVUAS.getOrdWork());
        assertEquals(endDate, financeOrderWorkWithIndirectVUAS.getEndDateProjAsPerContract());
        assertEquals(160, financeOrderWorkWithIndirectVUAS.getMaxHours());
        assertEquals(1.0, financeOrderWorkWithIndirectVUAS.getWorkLoad());
        assertEquals(25.0, financeOrderWorkWithIndirectVUAS.getPayPerHour());
        assertEquals(financeSourceType, financeOrderWorkWithIndirectVUAS.getFinSourceType());
        assertEquals("Inženieris", financeOrderWorkWithIndirectVUAS.getPositionInProject());
    }

    @Test
    void testSettersAndGetters() {

        financeOrderWork.setProject(project);
        financeOrderWork.setAcWorkLoad(academicWorkLoad);
        financeOrderWork.setBaseFin(baseFin);
        financeOrderWork.setIndVUAS(indirectVUAS);
        financeOrderWork.setOrdWork(orderWork);
        financeOrderWork.setEndDateProjAsPerContract(endDate);
        financeOrderWork.setMaxHours(160);
        financeOrderWork.setWorkLoad(1.0);
        financeOrderWork.setPayPerHour(25.0);
        financeOrderWork.setFinSourceType(financeSourceType);
        financeOrderWork.setPositionInProject("Amats");

        assertEquals(project, financeOrderWork.getProject());
        assertEquals(academicWorkLoad, financeOrderWork.getAcWorkLoad());
        assertEquals(baseFin, financeOrderWork.getBaseFin());
        assertEquals(indirectVUAS, financeOrderWork.getIndVUAS());
        assertEquals(orderWork, financeOrderWork.getOrdWork());
        assertEquals(endDate, financeOrderWork.getEndDateProjAsPerContract());
        assertEquals(160, financeOrderWork.getMaxHours());
        assertEquals(1.0, financeOrderWork.getWorkLoad());
        assertEquals(25.0, financeOrderWork.getPayPerHour());
        assertEquals(financeSourceType, financeOrderWork.getFinSourceType());
        assertEquals("Amats", financeOrderWork.getPositionInProject());
    }

    @Test
    void testAddAndRemoveBasicActivities() {
        financeOrderWorkWithProject.addBasicActivitiesToFinanceOrderWork(basicActivity);
        assertTrue(financeOrderWorkWithProject.getBasicArtivitiesCol().contains(basicActivity));
        financeOrderWorkWithProject.removeBasicActivitiesFromFinanceOrderWork(basicActivity);
        assertFalse(financeOrderWorkWithProject.getBasicArtivitiesCol().contains(basicActivity));
    }

    @Test
    void testAddAndRemoveEmployeeTimeSheet() {
        financeOrderWorkWithProject.addEmployeeTimeSheetToFinanceOrderWork(employeeTimeSheet);
        assertTrue(financeOrderWorkWithProject.getEmplTimeSheets().contains(employeeTimeSheet));
        financeOrderWorkWithProject.removeEmployeeTimeSheetsFromFinanceOrderWork(employeeTimeSheet);
        assertFalse(financeOrderWorkWithProject.getEmplTimeSheets().contains(employeeTimeSheet));
    }

    @Test
    void testFinanceOrderWorkToString() {
        String expected = "FinanceOrderWork(idFinOrdWork=0, project=null, acWorkLoad=null, baseFin=null, indVUAS=null, ordWork=null, endDateProjAsPerContract=null, maxHours=0, workLoad=0.0, payPerHour=0.0, finSourceType=null, positionInProject=null)";
        assertEquals(expected, financeOrderWork.toString());
    }

}