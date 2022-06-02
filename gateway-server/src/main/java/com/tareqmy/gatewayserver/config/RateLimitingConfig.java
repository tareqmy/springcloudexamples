package com.tareqmy.gatewayserver.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Mono;

@Configuration
public class RateLimitingConfig {

//    @Bean
//    @Primary
//    @ConditionalOnProperty("rateLimiter.non-secure")
//    KeyResolver userKeyResolver() {
//        return exchange -> Mono.just("1");
//    }

    //TODO: need to figure out how to make it work
    @Bean
//    @ConditionalOnProperty("rateLimiter.secure")
    KeyResolver authUserKeyResolver() {
        return exchange -> ReactiveSecurityContextHolder.getContext()
            .map(ctx -> ctx.getAuthentication()
                .getPrincipal().toString());
    }
}
