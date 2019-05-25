package com.github.bartoszpogoda.distmarketcentral.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class APIKeyAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        // TODO find producer by api - key, if found set producer in context and make it easily accessible in Controllers
        if (!"PALCEHOLDER-KEY".equals(principal))
        {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        }
        List<SimpleGrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_PRODUCER"));
        return new UsernamePasswordAuthenticationToken("PLACEHOLDER-ID", null, roles);
    }
}