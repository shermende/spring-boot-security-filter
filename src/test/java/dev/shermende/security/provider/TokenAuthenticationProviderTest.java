package dev.shermende.security.provider;

import dev.shermende.security.token.TokenAuthentication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class TokenAuthenticationProviderTest {
    private static final String OK = "ok";
    private static final String NOTOK = "notok";

    @Test
    void authenticate() {
        final TokenAuthenticationProvider provider = new TokenAuthenticationProvider();
        final Authentication authentication = provider.authenticate(new TokenAuthentication(OK));
        Assertions.assertEquals(OK, authentication.getPrincipal().toString());
    }

    @Test
    void authenticateException() {
        final TokenAuthenticationProvider provider = new TokenAuthenticationProvider();
        Assertions.assertThrows(UsernameNotFoundException.class,
            () -> provider.authenticate(new TokenAuthentication(NOTOK)));
    }

    @Test
    void supports() {
        final TokenAuthenticationProvider provider = new TokenAuthenticationProvider();
        Assertions.assertFalse(provider.supports(String.class));
        Assertions.assertTrue(provider.supports(TokenAuthentication.class));
    }


}