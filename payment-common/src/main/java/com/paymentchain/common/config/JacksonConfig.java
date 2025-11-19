package com.paymentchain.common.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Jackson ObjectMapper configuration.
 * Configures JSON serialization/deserialization settings.
 *
 * @author benas
 */
@Configuration
public class JacksonConfig {

    private static final Logger logger = LoggerFactory.getLogger(JacksonConfig.class);

    /**
     * Configure the default ObjectMapper bean.
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        logger.info("Configuring Jackson ObjectMapper");

        ObjectMapper mapper = new ObjectMapper();

        // Register Java 8 date/time module
        mapper.registerModule(new JavaTimeModule());

        // Disable writing dates as timestamps (use ISO-8601 format)
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Pretty print for development (can be disabled in production)
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Ignore unknown properties during deserialization
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Include non-null values only
        mapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);

        logger.debug("ObjectMapper configured with JavaTimeModule and custom settings");

        return mapper;
    }
}
