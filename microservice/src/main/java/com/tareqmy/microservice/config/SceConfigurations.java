package com.tareqmy.microservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Created by tareqmy at 5/5/22
 */
@ConfigurationProperties(prefix = "springcloudexamples")
public class SceConfigurations {

    private final CorsConfiguration cors = new CorsConfiguration();

    public CorsConfiguration getCors() {
        return cors;
    }
}
