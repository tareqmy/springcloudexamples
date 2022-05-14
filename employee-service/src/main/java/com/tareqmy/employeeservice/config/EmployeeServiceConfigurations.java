package com.tareqmy.employeeservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Created by tareqmy at 5/5/22
 */
@Configuration
@ConfigurationProperties(prefix = "employee-service")
public class EmployeeServiceConfigurations {

    private final CorsConfiguration cors = new CorsConfiguration();

    public CorsConfiguration getCors() {
        return cors;
    }
}
