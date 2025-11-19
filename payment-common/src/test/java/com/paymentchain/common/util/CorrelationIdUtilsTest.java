package com.paymentchain.common.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for CorrelationIdUtils.
 *
 * @author benas
 */
class CorrelationIdUtilsTest {

    @AfterEach
    void cleanup() {
        CorrelationIdUtils.clearCorrelationId();
    }

    @Test
    void shouldGenerateUniqueCorrelationIds() {
        // Act
        String id1 = CorrelationIdUtils.generateCorrelationId();
        String id2 = CorrelationIdUtils.generateCorrelationId();

        // Assert
        assertThat(id1).isNotNull();
        assertThat(id2).isNotNull();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void shouldSetAndGetCorrelationId() {
        // Arrange
        String correlationId = "test-correlation-123";

        // Act
        CorrelationIdUtils.setCorrelationId(correlationId);
        String retrieved = CorrelationIdUtils.getCorrelationId();

        // Assert
        assertThat(retrieved).isEqualTo(correlationId);
    }

    @Test
    void shouldClearCorrelationId() {
        // Arrange
        CorrelationIdUtils.setCorrelationId("test-id");

        // Act
        CorrelationIdUtils.clearCorrelationId();

        // Assert
        assertThat(CorrelationIdUtils.getCorrelationId()).isNull();
    }

    @Test
    void shouldReturnCorrelationIdHeaderName() {
        // Act
        String headerName = CorrelationIdUtils.getCorrelationIdHeader();

        // Assert
        assertThat(headerName).isEqualTo("X-Correlation-Id");
    }

    @Test
    void shouldStoreDifferentCorrelationIdsInMDC() {
        // Arrange
        String id1 = "correlation-1";
        String id2 = "correlation-2";

        // Act & Assert
        CorrelationIdUtils.setCorrelationId(id1);
        assertThat(MDC.get("correlationId")).isEqualTo(id1);

        CorrelationIdUtils.setCorrelationId(id2);
        assertThat(MDC.get("correlationId")).isEqualTo(id2);

        CorrelationIdUtils.clearCorrelationId();
        assertThat(MDC.get("correlationId")).isNull();
    }
}
