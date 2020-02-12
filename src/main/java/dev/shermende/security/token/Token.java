package dev.shermende.security.token;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Token extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 7569225231891246126L;

    private final String accessToken;

    public Token(String accessToken) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.accessToken = accessToken;
    }

    @Override
    public Object getCredentials() {
        return getAccessToken();
    }

    @Override
    public Object getPrincipal() {
        return getAccessToken();
    }

}
