package com.github.bartoszpogoda.distmarketcentral.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${adminAccount.username}")
    private String adminUsername;

    @Value("${adminAccount.password}")
    private String adminPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        APIKeyAuthFilter apiKeyFilter = new APIKeyAuthFilter("API-KEY");
        apiKeyFilter.setAuthenticationManager(new APIKeyAuthenticationManager());

        BasicAuthenticationFilter basicFilter = new BasicAuthenticationFilter(new BasicAuthAuthenticationManager(adminUsername, adminPassword));

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilter(apiKeyFilter).addFilterBefore(basicFilter, APIKeyAuthFilter.class)
                .authorizeRequests().anyRequest().authenticated();
    }

}
