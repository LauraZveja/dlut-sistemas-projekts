package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FinancePlanningTest {

    Project project = new Project();
    AcademicWorkLoad academicWorkLoad = new AcademicWorkLoad();
    BaseFin baseFin = new BaseFin();
    IndirectVUAS indirectVUAS = new IndirectVUAS();
    Employee employee = new Employee();
    LocalDate yearMonth = LocalDate.of(2024, 6, 1);

    FinancePlanning financePlanningWithProject = new FinancePlanning(
            project, employee, yearMonth, 160.0, 1.0, 25.0);
    FinancePlanning financePlanningWithAcademicWorkLoad = new FinancePlanning(
            academicWorkLoad, employee, yearMonth, 160.0, 1.0, 25.0);
    FinancePlanning financePlanningWithBaseFin = new FinancePlanning(
            baseFin, employee, yearMonth, 160.0, 1.0, 25.0);
    FinancePlanning financePlanningWithIndirectVUAS = new FinancePlanning(
            indirectVUAS, employee, yearMonth, 160.0, 1.0, 25.0);

    @Test
    void testFinancePlanningCreationWithProject() {
        assertEquals(project, financePlanningWithProject.getProject());
        assertEquals(employee, financePlanningWithProject.getEmployee());
        assertEquals(yearMonth, financePlanningWithProject.getYearMonth());
        assertEquals(160.0, financePlanningWithProject.getHoursCertainMonth());
        assertEquals(1.0, financePlanningWithProject.getWorkLoadCertainMonth());
        assertEquals(25.0, financePlanningWithProject.getPerHour());
    }

    @Test
    void testFinancePlanningCreationWithAcademicWorkLoad() {
        assertEquals(academicWorkLoad, financePlanningWithAcademicWorkLoad.getAcWorkLoad());
        assertEquals(employee, financePlanningWithAcademicWorkLoad.getEmployee());
        assertEquals(yearMonth, financePlanningWithAcademicWorkLoad.getYearMonth());
        assertEquals(160.0, financePlanningWithAcademicWorkLoad.getHoursCertainMonth());
        assertEquals(1.0, financePlanningWithAcademicWorkLoad.getWorkLoadCertainMonth());
        assertEquals(25.0, financePlanningWithAcademicWorkLoad.getPerHour());
    }

    @Test
    void testFinancePlanningCreationWithBaseFin() {
        assertEquals(baseFin, financePlanningWithBaseFin.getBaseFin());
        assertEquals(employee, financePlanningWithBaseFin.getEmployee());
        assertEquals(yearMonth, financePlanningWithBaseFin.getYearMonth());
        assertEquals(160.0, financePlanningWithBaseFin.getHoursCertainMonth());
        assertEquals(1.0, financePlanningWithBaseFin.getWorkLoadCertainMonth());
        assertEquals(25.0, financePlanningWithBaseFin.getPerHour());
    }

    @Test
    void testFinancePlanningCreationWithIndirectVUAS() {
        assertEquals(indirectVUAS, financePlanningWithIndirectVUAS.getIndVUAS());
        assertEquals(employee, financePlanningWithIndirectVUAS.getEmployee());
        assertEquals(yearMonth, financePlanningWithIndirectVUAS.getYearMonth());
        assertEquals(160.0, financePlanningWithIndirectVUAS.getHoursCertainMonth());
        assertEquals(1.0, financePlanningWithIndirectVUAS.getWorkLoadCertainMonth());
        assertEquals(25.0, financePlanningWithIndirectVUAS.getPerHour());
    }

    @Test
    void testSettersAndGetters() {
        FinancePlanning financePlanning = new FinancePlanning();

        financePlanning.setProject(project);
        financePlanning.setAcWorkLoad(academicWorkLoad);
        financePlanning.setBaseFin(baseFin);
        financePlanning.setIndVUAS(indirectVUAS);
        financePlanning.setEmployee(employee);
        financePlanning.setYearMonth(yearMonth);
        financePlanning.setHoursCertainMonth(160.0);
        financePlanning.setWorkLoadCertainMonth(1.0);
        financePlanning.setPerHour(25.0);

        assertEquals(project, financePlanning.getProject());
        assertEquals(academicWorkLoad, financePlanning.getAcWorkLoad());
        assertEquals(baseFin, financePlanning.getBaseFin());
        assertEquals(indirectVUAS, financePlanning.getIndVUAS());
        assertEquals(employee, financePlanning.getEmployee());
        assertEquals(yearMonth, financePlanning.getYearMonth());
        assertEquals(160.0, financePlanning.getHoursCertainMonth());
        assertEquals(1.0, financePlanning.getWorkLoadCertainMonth());
        assertEquals(25.0, financePlanning.getPerHour());
    }

    @Test
    void testFinancePlanningToString() {
        String expected = "FinancePlanning(idFinPlanning=0, project=null, acWorkLoad=null, baseFin=null, indVUAS=null, employee=null, yearMonth=null, hoursCertainMonth=0.0, workLoadCertainMonth=0.0, perHour=0.0)";
        FinancePlanning financePlanning = new FinancePlanning();
        assertEquals(expected, financePlanning.toString());
    }

}