package com.tareqmy.departmentservice.config;

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

    private final DepartmentServiceConfigurations departmentServiceConfigurations;

    public WebConfigurer(DepartmentServiceConfigurations departmentServiceConfigurations) {
        this.departmentServiceConfigurations = departmentServiceConfigurations;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = departmentServiceConfigurations.getCors();
        if (!CollectionUtils.isEmpty(config.getAllowedOrigins()) || !CollectionUtils.isEmpty(config.getAllowedOriginPatterns())) {
            source.registerCorsConfiguration("/**", config);
        }
        return new CorsFilter(source);
    }
}
