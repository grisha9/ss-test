package com.example.ss.sstest.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class BasicAndBearerAuthConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

        BasicAuthProvider basicAuthProvider = new BasicAuthProvider();
        TokenAuthProvider tokenAuthProvider = new TokenAuthProvider();

        BasicAuthenticationFilter basicAuthFilter = new BasicAuthenticationFilter(authenticationManager);
        BearerTokenAuthenticationFilter bearerAuthFilter = new BearerTokenAuthenticationFilter(authenticationManager);

        http
                .authenticationProvider(basicAuthProvider)
                .authenticationProvider(tokenAuthProvider)
                .addFilter(postProcess(basicAuthFilter))
                .addFilter(postProcess(bearerAuthFilter));
    }
}

