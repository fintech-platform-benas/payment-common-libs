package com.paymentchain.common.util;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * Utility class for managing correlation IDs across requests.
 *
 * @author benas
 */
public class CorrelationIdUtils {

    private static final String CORRELATION_ID_KEY = "correlationId";
    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";

    private CorrelationIdUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Generate a new correlation ID.
     */
    public static String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Set correlation ID in MDC (Mapped Diagnostic Context).
     */
    public static void setCorrelationId(String correlationId) {
        MDC.put(CORRELATION_ID_KEY, correlationId);
    }

    /**
     * Get correlation ID from MDC.
     */
    public static String getCorrelationId() {
        return MDC.get(CORRELATION_ID_KEY);
    }

    /**
     * Clear correlation ID from MDC.
     */
    public static void clearCorrelationId() {
        MDC.remove(CORRELATION_ID_KEY);
    }

    /**
     * Get correlation ID header name.
     */
    public static String getCorrelationIdHeader() {
        return CORRELATION_ID_HEADER;
    }
}
