package com.tareqmy.organizationservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //NOTE: this is to configure authentication
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //NOTE: this is to configure authorization
        http.authorizeRequests(authorize -> authorize
                .antMatchers("/v3/api-docs/**").permitAll()
                .anyRequest().authenticated())
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        http.csrf().disable();
        http.formLogin().disable();
        http.httpBasic().disable();
    }
}
