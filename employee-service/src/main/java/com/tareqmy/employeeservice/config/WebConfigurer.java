package com.tareqmy.employeeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created by tareqmy at 5/5/22
 */
@Configuration
public class WebConfigurer {

    private final EmployeeServiceConfigurations employeeServiceConfigurations;

    public WebConfigurer(EmployeeServiceConfigurations employeeServiceConfigurations) {
        this.employeeServiceConfigurations = employeeServiceConfigurations;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = employeeServiceConfigurations.getCors();
        if (!CollectionUtils.isEmpty(config.getAllowedOrigins()) || !CollectionUtils.isEmpty(config.getAllowedOriginPatterns())) {
            source.registerCorsConfiguration("/**", config);
        }
        return new CorsFilter(source);
    }
}
