package com.paymentchain.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Auto-configuration for payment-common module.
 * This enables component scanning for common components when the module is included.
 *
 * @author benas
 */
@Configuration
@ComponentScan(basePackages = {
        "com.paymentchain.common.exception",
        "com.paymentchain.common.filter",
        "com.paymentchain.common.config"
})
public class CommonAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CommonAutoConfiguration.class);

    public CommonAutoConfiguration() {
        logger.info("Payment Common Auto-Configuration initialized");
        logger.info("Enabled components: GlobalExceptionHandler, CorrelationIdFilter, LoggingFilter");
    }
}
