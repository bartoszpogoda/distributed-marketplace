package com.github.bartoszpogoda.distmarketcentral.auth;

import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import com.github.bartoszpogoda.distmarketcentral.service.SupplierAdministrationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class APIKeyAuthenticationManager implements AuthenticationManager {

    private final SupplierAdministrationService supplierAdministrationService;

    public APIKeyAuthenticationManager(SupplierAdministrationService supplierAdministrationService) {
        this.supplierAdministrationService = supplierAdministrationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiKey = (String) authentication.getPrincipal();
        Optional<Supplier> supplierOpt = this.supplierAdministrationService.getByApiKey(apiKey);

        return supplierOpt.map(supplier -> {
            List<SimpleGrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_SUPPLIER"));
            return new UsernamePasswordAuthenticationToken(supplier.getId(), null, roles);
        }).orElseThrow(() -> new BadCredentialsException("The API key was not found or not the expected value."));

        // TODO if found set producer in context and make it easily accessible in Cont
    }
}