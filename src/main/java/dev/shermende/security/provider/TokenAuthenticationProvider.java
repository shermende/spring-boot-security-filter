package dev.shermende.security.provider;

import dev.shermende.security.token.TokenAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    private static final String OK = "ok";
    private static final String TOKEN_IS_NULL = "token-is-null";

    @Override
    public Authentication authenticate(Authentication authentication) {
        return new TokenAuthentication(getToken(authentication), AuthorityUtils.NO_AUTHORITIES);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthentication.class.isAssignableFrom(aClass);
    }

    private String getToken(Authentication authentication) {
        return (String) Optional.ofNullable(authentication.getCredentials()).filter(o -> o.equals(OK))
            .orElseThrow(() -> new UsernameNotFoundException(TOKEN_IS_NULL));
    }

}
