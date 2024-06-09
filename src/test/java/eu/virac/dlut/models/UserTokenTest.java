package eu.virac.dlut.models;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserTokenTest {

    String dn = "exampleDN";
    String token = "exampleToken";
    Date expired = new Date();
    String newDn = "newExampleDN";
    String newToken = "newExampleToken";
    Date newExpired = new Date(expired.getTime() + 100000);

    UserToken userToken = new UserToken(dn, token, expired);

    @Test
    void testUserTokenCreation() {
        assertEquals(dn, userToken.getDn());
        assertEquals(token, userToken.getToken());
        assertEquals(expired, userToken.getExpired());
    }

    @Test
    void testSetDn() {
        userToken.setDn(newDn);
        assertEquals(newDn, userToken.getDn());
    }

    @Test
    void testSetToken() {
        userToken.setToken(newToken);
        assertEquals(newToken, userToken.getToken());
    }

    @Test
    void testSetExpired() {
        userToken.setExpired(newExpired);
        assertEquals(newExpired, userToken.getExpired());
    }

    @Test
    void testUserTokenToString() {
        String expected = "UserToken(idUserToken=0, dn=exampleDN, token=exampleToken, expired=" + expired.toString() + ")";
        assertEquals(expected, userToken.toString());
    }

}