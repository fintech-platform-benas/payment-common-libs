package com.paymentchain.common.dto;

import com.paymentchain.common.util.JsonUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for ErrorResponse.
 *
 * @author benas
 */
class ErrorResponseTest {

    @Test
    void shouldCreateErrorResponseWithBuilder() {
        // Act
        ErrorResponse response = ErrorResponse.builder()
                .status(400)
                .error("Bad Request")
                .message("Invalid input")
                .path("/api/test")
                .correlationId("corr-123")
                .build();

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.getError()).isEqualTo("Bad Request");
        assertThat(response.getMessage()).isEqualTo("Invalid input");
        assertThat(response.getPath()).isEqualTo("/api/test");
        assertThat(response.getCorrelationId()).isEqualTo("corr-123");
        assertThat(response.getTimestamp()).isNotNull();
    }

    @Test
    void shouldAddValidationErrorsUsingBuilder() {
        // Act
        ErrorResponse response = ErrorResponse.builder()
                .status(400)
                .error("Validation Failed")
                .message("Input validation error")
                .addValidationError("email", "Email is required")
                .addValidationError("age", "Age must be positive")
                .build();

        // Assert
        assertThat(response.getValidationErrors()).hasSize(2);
        assertThat(response.getValidationErrors().get(0).getField()).isEqualTo("email");
        assertThat(response.getValidationErrors().get(0).getMessage()).isEqualTo("Email is required");
        assertThat(response.getValidationErrors().get(1).getField()).isEqualTo("age");
        assertThat(response.getValidationErrors().get(1).getMessage()).isEqualTo("Age must be positive");
    }

    @Test
    void shouldAddValidationErrorsAfterCreation() {
        // Arrange
        ErrorResponse response = ErrorResponse.builder()
                .status(400)
                .error("Validation Failed")
                .message("Input validation error")
                .build();

        // Act
        response.addValidationError("name", "Name is required");
        response.addValidationError("email", "Email is invalid");

        // Assert
        assertThat(response.getValidationErrors()).hasSize(2);
    }

    @Test
    void shouldSerializeToJson() {
        // Arrange
        ErrorResponse response = ErrorResponse.builder()
                .status(404)
                .error("Not Found")
                .message("Resource not found")
                .path("/api/users/123")
                .correlationId("corr-456")
                .addValidationError("id", "User not found")
                .build();

        // Act
        String json = JsonUtils.toJson(response);

        // Assert
        assertThat(json).isNotNull();
        assertThat(json).contains("\"status\":404");
        assertThat(json).contains("\"error\":\"Not Found\"");
        assertThat(json).contains("\"message\":\"Resource not found\"");
        assertThat(json).contains("\"correlationId\":\"corr-456\"");
    }

    @Test
    void shouldInitializeWithEmptyValidationErrors() {
        // Act
        ErrorResponse response = ErrorResponse.builder()
                .status(500)
                .error("Internal Server Error")
                .message("Something went wrong")
                .build();

        // Assert
        assertThat(response.getValidationErrors()).isNotNull();
        assertThat(response.getValidationErrors()).isEmpty();
    }
}
