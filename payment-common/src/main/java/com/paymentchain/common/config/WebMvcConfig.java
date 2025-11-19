package com.paymentchain.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC configuration for common web settings.
 * Configures CORS and other web-related settings.
 *
 * @author benas
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("Configuring CORS mappings");

        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:4200", "http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("X-Correlation-Id", "Authorization", "Content-Type")
                .allowCredentials(true)
                .maxAge(3600);

        logger.debug("CORS configured for /api/** endpoints");
    }
}
