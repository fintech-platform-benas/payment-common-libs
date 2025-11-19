package com.paymentchain.common.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for ValidationUtils.
 *
 * @author benas
 */
class ValidationUtilsTest {

    @Test
    void shouldDetectNullOrEmptyStrings() {
        // Assert
        assertThat(ValidationUtils.isNullOrEmpty(null)).isTrue();
        assertThat(ValidationUtils.isNullOrEmpty("")).isTrue();
        assertThat(ValidationUtils.isNullOrEmpty("   ")).isTrue();
        assertThat(ValidationUtils.isNullOrEmpty("test")).isFalse();
    }

    @Test
    void shouldRequireNonNullObject() {
        // Arrange
        String value = "test";

        // Act & Assert
        assertThat(ValidationUtils.requireNonNull(value, "value")).isEqualTo(value);
        assertThatThrownBy(() -> ValidationUtils.requireNonNull(null, "value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("value cannot be null");
    }

    @Test
    void shouldRequireNonEmptyString() {
        // Act & Assert
        assertThat(ValidationUtils.requireNonEmpty("test", "field")).isEqualTo("test");
        assertThatThrownBy(() -> ValidationUtils.requireNonEmpty((String) null, "field"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("field cannot be null or empty");
        assertThatThrownBy(() -> ValidationUtils.requireNonEmpty("", "field"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("field cannot be null or empty");
    }

    @Test
    void shouldRequireNonEmptyCollection() {
        // Arrange
        List<String> validList = Arrays.asList("item1", "item2");
        List<String> emptyList = new ArrayList<>();

        // Act & Assert
        assertThat(ValidationUtils.requireNonEmpty(validList, "list")).isEqualTo(validList);
        assertThatThrownBy(() -> ValidationUtils.requireNonEmpty(emptyList, "list"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("list cannot be null or empty");
        assertThatThrownBy(() -> ValidationUtils.requireNonEmpty((List<String>) null, "list"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("list cannot be null or empty");
    }

    @Test
    void shouldRequirePositiveNumbers() {
        // Act & Assert
        assertThat(ValidationUtils.requirePositive(10, "number")).isEqualTo(10);
        assertThat(ValidationUtils.requirePositive(1.5, "number")).isEqualTo(1.5);
        assertThat(ValidationUtils.requirePositive(new BigDecimal("100.50"), "amount"))
                .isEqualTo(new BigDecimal("100.50"));

        assertThatThrownBy(() -> ValidationUtils.requirePositive(0, "number"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("number must be positive");
        assertThatThrownBy(() -> ValidationUtils.requirePositive(-1, "number"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("number must be positive");
    }

    @Test
    void shouldRequireNonNegativeNumbers() {
        // Act & Assert
        assertThat(ValidationUtils.requireNonNegative(0, "number")).isEqualTo(0);
        assertThat(ValidationUtils.requireNonNegative(10, "number")).isEqualTo(10);
        assertThat(ValidationUtils.requireNonNegative(1.5, "number")).isEqualTo(1.5);

        assertThatThrownBy(() -> ValidationUtils.requireNonNegative(-1, "number"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("number cannot be negative");
        assertThatThrownBy(() -> ValidationUtils.requireNonNegative(-0.1, "number"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("number cannot be negative");
    }

    @Test
    void shouldHandleNullNumbersInValidation() {
        // Act & Assert
        assertThatThrownBy(() -> ValidationUtils.requirePositive(null, "number"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("number cannot be null");
        assertThatThrownBy(() -> ValidationUtils.requireNonNegative(null, "number"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("number cannot be null");
    }
}
