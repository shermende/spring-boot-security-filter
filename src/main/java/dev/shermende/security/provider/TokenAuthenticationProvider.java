package dev.shermende.security.provider;

import dev.shermende.db.entity.AccessToken;
import dev.shermende.db.repository.AccessTokenRepository;
import dev.shermende.security.token.Token;
import dev.shermende.security.token.TokenAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    private static final String TOKEN_IS_NULL = "token-is-null";

    private final AccessTokenRepository repository;

    public TokenAuthenticationProvider(
        AccessTokenRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        final String token = getToken(authentication);
        final AccessToken accessToken = getAccessToken(token);
        return new TokenAuthentication(accessToken.getId());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Token.class.isAssignableFrom(aClass);
    }

    private String getToken(Authentication authentication) {
        return (String) Optional.ofNullable(authentication.getCredentials())
            .orElseThrow(() -> new BadCredentialsException(TOKEN_IS_NULL));
    }

    private AccessToken getAccessToken(String token) {
        return repository.findById(token).orElseThrow(() -> new UsernameNotFoundException(token));
    }

}
