package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    String title = "Projekta nosaukums";
    String code = "PROJ123";
    String accountingAcronym = "ACR";
    String contractNo = "CNT123";
    ProjectCharacter projChar = new ProjectCharacter();
    ProjectCategory projCat = new ProjectCategory();
    int projLengthMonths = 12;
    LocalDate start = LocalDate.of(2024, 1, 1);
    LocalDate end = LocalDate.of(2024, 12, 31);
    LocalDate newEnd = LocalDate.of(2025, 12, 31);
    LocalDate newStart = LocalDate.of(2025, 1, 1);
    boolean isActive = true;
    FinancePlanning financePlanning = new FinancePlanning();
    FinanceOrderVacation financeOrderVacation = new FinanceOrderVacation();
    FinanceOrderWork financeOrderWork = new FinanceOrderWork();

    Project project = new Project(title, code, accountingAcronym, contractNo, projChar, projCat, projLengthMonths, start, end);

    int newProjLengthMonths = 24;
    Project proj = new Project();

    @Test
    void testProjectCreation() {
        assertEquals(title, project.getTitle());
        assertEquals(code, project.getCode());
        assertEquals(accountingAcronym, project.getAccountingAcronym());
        assertEquals(contractNo, project.getContractNo());
        assertEquals(projChar, project.getProjChar());
        assertEquals(projCat, project.getProjCat());
        assertEquals(projLengthMonths, project.getProjLenghtMonths());
        assertEquals(start, project.getStart());
        assertEquals(end, project.getEnd());
    }

    @Test
    void testSetAccountingAcronym() {
        String newAccountingAcronym = "NEW_ACR";
        project.setAccountingAcronym(newAccountingAcronym);
        assertEquals(newAccountingAcronym, project.getAccountingAcronym());
    }

    @Test
    void testSetContractNo() {
        String newContractNo = "NEW_CNT123";
        project.setContractNo(newContractNo);
        assertEquals(newContractNo, project.getContractNo());
    }

    @Test
    void testSetProjChar() {
        ProjectCharacter newProjChar = new ProjectCharacter();
        project.setProjChar(newProjChar);
        assertEquals(newProjChar, project.getProjChar());
    }

    @Test
    void testSetProjCat() {
        ProjectCategory newProjCat = new ProjectCategory();
        project.setProjCat(newProjCat);
        assertEquals(newProjCat, project.getProjCat());
    }

    @Test
    void testSetProjLengthMonths() {
        project.setProjLenghtMonths(newProjLengthMonths);
        assertEquals(newProjLengthMonths, project.getProjLenghtMonths());
    }

    @Test
    void testSetStart() {
        project.setStart(newStart);
        assertEquals(newStart, project.getStart());
    }

    @Test
    void testSetEnd() {
        project.setEnd(newEnd);
        assertEquals(newEnd, project.getEnd());
    }

    @Test
    void testSetIsActive() {
        project.setActive(isActive);
        assertTrue(project.isActive());
    }

    @Test
    void testFinPlanningColInitialization() {
        proj.setFinPlanningCol(new ArrayList<>());
        assertNotNull(proj.getFinPlanningCol());
        assertTrue(proj.getFinPlanningCol().isEmpty());
    }

    @Test
    void testAddFinancePlanning() {
        project.setFinPlanningCol(new ArrayList<>());
        project.addFinancePlanningToProject(financePlanning);
        assertTrue(project.getFinPlanningCol().contains(financePlanning));
    }

    @Test
    void testRemoveFinancePlanning() {
        project.setFinPlanningCol(new ArrayList<>());
        project.addFinancePlanningToProject(financePlanning);
        project.removeFinancePlanningFromProject(financePlanning);
        assertFalse(project.getFinPlanningCol().contains(financePlanning));
    }

    @Test
    void testFinOrderVacColInitialization() {
        proj.setFinOrderVacCol(new ArrayList<>());
        assertNotNull(proj.getFinOrderVacCol());
        assertTrue(proj.getFinOrderVacCol().isEmpty());
    }

    @Test
    void testAddFinanceOrderVacation() {
        project.setFinOrderVacCol(new ArrayList<>());
        project.addFinanceOrderVacationToProject(financeOrderVacation);
        assertTrue(project.getFinOrderVacCol().contains(financeOrderVacation));
    }

    @Test
    void testRemoveFinanceOrderVacation() {
        project.setFinOrderVacCol(new ArrayList<>());
        project.addFinanceOrderVacationToProject(financeOrderVacation);
        project.removeFinanceOrderVacationFromProject(financeOrderVacation);
        assertFalse(project.getFinOrderVacCol().contains(financeOrderVacation));
    }

    @Test
    void testFinOrderWorkColInitialization() {
        proj.setFinOrderWorkCol(new ArrayList<>());
        assertNotNull(proj.getFinOrderWorkCol());
        assertTrue(proj.getFinOrderWorkCol().isEmpty());
    }

    @Test
    void testAddFinanceOrderWork() {
        project.setFinOrderWorkCol(new ArrayList<>());
        project.addFinanceOrderWorkToProject(financeOrderWork);
        assertTrue(project.getFinOrderWorkCol().contains(financeOrderWork));
    }

    @Test
    void testRemoveFinanceOrderWork() {
        project.setFinOrderWorkCol(new ArrayList<>());
        project.addFinanceOrderWorkToProject(financeOrderWork);
        project.removeFinanceOrderWorkFromProject(financeOrderWork);
        assertFalse(project.getFinOrderWorkCol().contains(financeOrderWork));
    }

    @Test
    void testProjectToString() {
        String expected = "Project(accountingAcronym=ACR, contractNo=CNT123, projChar=ProjectCharacter(idProjChar=0, projCharTitle=null), projCat=ProjectCategory(idProjCat=0, projCatTitle=null), projLenghtMonths=12, start=2024-01-01, end=2024-12-31, isActive=false)";
        assertEquals(expected, project.toString());
    }

}