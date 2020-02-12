/*
 * Copyright (c) 2018.
 */

package dev.shermende.security.filter;

import dev.shermende.security.token.TokenAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final String X_TOKEN = "X-TOKEN";

    public TokenAuthenticationFilter(
        AuthenticationManager authenticationManager
    ) {
        super(new AntPathRequestMatcher("/**"));
        setAuthenticationManager(authenticationManager);
    }

    private String extractToken(ServletRequest req) {
        HttpServletRequest request = (HttpServletRequest) req;
        return request.getHeader(X_TOKEN);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        final String token = extractToken(httpServletRequest);
        return getAuthenticationManager().authenticate(new TokenAuthentication(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

}