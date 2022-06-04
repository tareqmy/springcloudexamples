package com.tareqmy.gatewayserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;

@Slf4j
@Configuration
public class RateLimitingConfig {

//    @Bean
//    KeyResolver userKeyResolver() {
//        return exchange -> Mono.just("1");
//    }

    /*
     * NOTE: this stops all unauthenticated access :(
     * need a way to allow public permitted urls from this. but how!
     */
    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> ReactiveSecurityContextHolder.getContext()
            .map(ctx -> ctx.getAuthentication().getPrincipal().toString());
    }
}
