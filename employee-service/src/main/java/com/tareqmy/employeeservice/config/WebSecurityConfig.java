package com.tareqmy.employeeservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value(value = "${employee-service.security.username}")
    private String username;

    @Value(value = "${employee-service.security.password}")
    private String password;

    @Value(value = "${employee-service.security.role}")
    private String role;

    @Value(value = "${employee-service.security.authorities}")
    private String authorities;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.requestMatcher(EndpointRequest.toAnyEndpoint().excluding("health", "info", "hystrix.stream"))
            .authorizeRequests()
            .anyRequest().hasRole(role)

            .and()
            .httpBasic();
        // @formatter:on
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser(username)
            .password(password)
            .authorities(authorities);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
