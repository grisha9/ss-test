package com.example.ss.sstest.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class BasicAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        if (!"admin".equals(token.getName())) {
            throw new UsernameNotFoundException("User not found");
        }

        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(aClass));
    }
}
