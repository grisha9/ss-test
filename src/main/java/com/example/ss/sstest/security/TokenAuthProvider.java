package com.example.ss.sstest.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;


public class TokenAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BearerTokenAuthenticationToken token = (BearerTokenAuthenticationToken) authentication;
        if (!"admin".equals(token.getName())) {
            throw new UsernameNotFoundException("User not found");
        }

        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (BearerTokenAuthenticationToken.class
                .isAssignableFrom(aClass));
    }
}
