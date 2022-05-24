package com.tareqmy.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Created by tareqmy at 22/5/22
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.authorizeExchange(exchange -> exchange
                .pathMatchers("/v3/api-docs/**",
                    "/employee/v3/api-docs/**",
                    "/department/v3/api-docs/**",
                    "/organization/v3/api-docs/**",
                    "/webjars/swagger-ui/**",
                    "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .anyExchange().authenticated())
            .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);

        serverHttpSecurity.csrf().disable();
        serverHttpSecurity.formLogin().disable();
        serverHttpSecurity.httpBasic().disable();
        return serverHttpSecurity.build();
    }
}
