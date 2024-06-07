package eu.virac.dlut.models.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentDTOTest {

    int idDepartment = 1;
    String title = "Pētniecības nodaļa";
    String abbreviation = "PN";
    String newTitle = "Jaunā pētniecības nodaļa";
    String newAbbreviation = "JPN";

    DepartmentDTO departmentDTO = new DepartmentDTO(idDepartment, title, abbreviation);

    @Test
    void testDepartmentDTOCreation() {
        assertEquals(idDepartment, departmentDTO.getIdDepartment());
        assertEquals(title, departmentDTO.getTitle());
        assertEquals(abbreviation, departmentDTO.getAbbreviation());
    }

    @Test
    void testSetTitle() {
        departmentDTO.setTitle(newTitle);
        assertEquals(newTitle, departmentDTO.getTitle());
    }

    @Test
    void testSetAbbreviation() {
        departmentDTO.setAbbreviation(newAbbreviation);
        assertEquals(newAbbreviation, departmentDTO.getAbbreviation());
    }

    @Test
    void testDepartmentDTOToString() {
        String expected = "DepartmentDTO(idDepartment=1, title=Pētniecības nodaļa, abbreviation=PN)";
        assertEquals(expected, departmentDTO.toString());
    }

}