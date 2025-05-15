package com.angel.server_spring_boot.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String email;

    public JwtAuthenticationToken(String email) {
        super(Collections.singletonList(new SimpleGrantedAuthority("USER")));
        this.email = email;
        setAuthenticated(true);
    }

    public JwtAuthenticationToken(String email, boolean authenticated) {
        super(Collections.singletonList(new SimpleGrantedAuthority("USER")));
        this.email = email;
        setAuthenticated(authenticated);
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }
}
