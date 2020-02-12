package dev.shermende.security.token;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.AuthorityUtils;

class TokenAuthenticationTest {

    @Test
    void getCredentials() {
        final String ts = String.valueOf(System.currentTimeMillis());
        final TokenAuthentication authentication = new TokenAuthentication(ts);
        Assertions.assertEquals(ts, authentication.getCredentials());
    }

    @Test
    void getPrincipal() {
        final String ts = String.valueOf(System.currentTimeMillis());
        final TokenAuthentication authentication = new TokenAuthentication(ts, AuthorityUtils.NO_AUTHORITIES);
        Assertions.assertNull(authentication.getCredentials());
        Assertions.assertEquals(ts, authentication.getPrincipal());
    }

    @Test
    void getToken() {
        final String ts = String.valueOf(System.currentTimeMillis());
        final TokenAuthentication authentication = new TokenAuthentication(ts);
        Assertions.assertEquals(ts, authentication.getCredentials());
        Assertions.assertEquals(authentication.getToken(), authentication.getCredentials());
    }

    @Test
    void testEquals() {
        final TokenAuthentication authentication1 = new TokenAuthentication("one");
        final TokenAuthentication authentication2 = new TokenAuthentication("two");
        final TokenAuthentication authentication3 = new TokenAuthentication("one");

        Assertions.assertEquals(authentication1, authentication3);
        Assertions.assertNotEquals(authentication1, authentication2);
    }

    @Test
    void testHashCode() {
        final TokenAuthentication authentication1 = new TokenAuthentication("one");
        final TokenAuthentication authentication2 = new TokenAuthentication("two");
        final TokenAuthentication authentication3 = new TokenAuthentication("one");

        Assertions.assertEquals(authentication1.hashCode(), authentication3.hashCode());
        Assertions.assertNotEquals(authentication1.hashCode(), authentication2.hashCode());
    }

}