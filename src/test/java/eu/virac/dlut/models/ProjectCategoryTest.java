package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectCategoryTest {

    String projCatTitle = "Fundamentālais";
    String newProjCatTitle = "Lietišķais";
    Project project = new Project();

    ProjectCategory projectCategory = new ProjectCategory(projCatTitle);

    @Test
    void testProjectCategoryCreation() {
        assertEquals(projCatTitle, projectCategory.getProjCatTitle());
    }

    @Test
    void testSetProjCatTitle() {
        projectCategory.setProjCatTitle(newProjCatTitle);
        assertEquals(newProjCatTitle, projectCategory.getProjCatTitle());
    }

    @Test
    void testProjectsInitialization() {
        ProjectCategory pc = new ProjectCategory();
        pc.setProjects(new ArrayList<>());
        assertNotNull(pc.getProjects());
        assertTrue(pc.getProjects().isEmpty());
    }

    @Test
    void testAddProject() {
        projectCategory.setProjects(new ArrayList<>());
        projectCategory.addProjectToProjectCategory(project);
        assertTrue(projectCategory.getProjects().contains(project));
    }

    @Test
    void testRemoveProject() {
        projectCategory.setProjects(new ArrayList<>());
        projectCategory.addProjectToProjectCategory(project);
        projectCategory.removeProjectFromProjectCategory(project);
        assertFalse(projectCategory.getProjects().contains(project));
    }

    @Test
    void testProjectCategoryToString() {
        String expected = "ProjectCategory(idProjCat=0, projCatTitle=Fundamentālais)";
        assertEquals(expected, projectCategory.toString());
    }

}