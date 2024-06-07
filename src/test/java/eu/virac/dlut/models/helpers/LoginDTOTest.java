package eu.virac.dlut.models.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginDTOTest {

    String username = "testaLietotajs";
    String password = "testaParole";

    LoginDTO loginDefault = new LoginDTO();
    LoginDTO loginParameterized = new LoginDTO(username, password);

    @Test
    void testDefaultConstructor() {
        assertNotNull(loginDefault);
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals(username, loginParameterized.getUsername());
        assertEquals(password, loginParameterized.getPassword());
    }

    @Test
    void testSetAndGetMethods() {
        loginDefault.setUsername(username);
        loginDefault.setPassword(password);

        assertEquals(username, loginDefault.getUsername());
        assertEquals(password, loginDefault.getPassword());
    }

    @Test
    void testToString() {
        String expected = "LoginDTO(username=testaLietotajs, password=testaParole)";
        assertEquals(expected, loginParameterized.toString());
    }

}