package com.tareqmy.employeeservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //NOTE: this is to configure authentication
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //NOTE: this is to configure authorization
        http.authorizeRequests()
            .antMatchers("/v3/api-docs/**").permitAll()
            .anyRequest().authenticated()
            .and().oauth2ResourceServer().jwt();

        http.csrf().disable();
        http.formLogin().disable();
        http.httpBasic().disable();
    }
}
