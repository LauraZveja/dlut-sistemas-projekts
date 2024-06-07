package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectCharacterTest {

    String projCharTitle = "Saimniecisks";
    String newProjCharTitle = "Nesaimniecisks";
    Project project = new Project();

    ProjectCharacter projectCharacter = new ProjectCharacter(projCharTitle);

    @Test
    void testProjectCharacterCreation() {
        assertEquals(projCharTitle, projectCharacter.getProjCharTitle());
    }

    @Test
    void testSetProjCharTitle() {
        projectCharacter.setProjCharTitle(newProjCharTitle);
        assertEquals(newProjCharTitle, projectCharacter.getProjCharTitle());
    }

    @Test
    void testProjectsInitialization() {
        ProjectCharacter pc = new ProjectCharacter();
        pc.setProjects(new ArrayList<>());
        assertNotNull(pc.getProjects());
        assertTrue(pc.getProjects().isEmpty());
    }

    @Test
    void testAddProject() {
        projectCharacter.setProjects(new ArrayList<>());
        projectCharacter.addProjectToProjectCharacter(project);
        assertTrue(projectCharacter.getProjects().contains(project));
    }

    @Test
    void testRemoveProject() {
        projectCharacter.setProjects(new ArrayList<>());
        projectCharacter.addProjectToProjectCharacter(project);
        projectCharacter.removeProjectFromProjectCharacter(project);
        assertFalse(projectCharacter.getProjects().contains(project));
    }

    @Test
    void testProjectCharacterToString() {
        String expected = "ProjectCharacter(idProjChar=0, projCharTitle=Saimniecisks)";
        assertEquals(expected, projectCharacter.toString());
    }

}