package com.paymentchain.common.dto;

import com.paymentchain.common.util.JsonUtils;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for ApiResponse.
 *
 * @author benas
 */
class ApiResponseTest {

    @Test
    void shouldCreateApiResponseWithBuilder() {
        // Arrange
        String data = "test data";
        String correlationId = "corr-123";

        // Act
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Success")
                .data(data)
                .correlationId(correlationId)
                .build();

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getData()).isEqualTo(data);
        assertThat(response.getCorrelationId()).isEqualTo(correlationId);
        assertThat(response.getTimestamp()).isNotNull();
    }

    @Test
    void shouldCreateSuccessResponseWithData() {
        // Arrange
        String data = "test data";

        // Act
        ApiResponse<String> response = ApiResponse.success(data);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isEqualTo(data);
    }

    @Test
    void shouldCreateSuccessResponseWithMessageAndData() {
        // Arrange
        String data = "test data";
        String message = "Operation completed";

        // Act
        ApiResponse<String> response = ApiResponse.success(message, data);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getMessage()).isEqualTo(message);
        assertThat(response.getData()).isEqualTo(data);
    }

    @Test
    void shouldCreateErrorResponse() {
        // Arrange
        String errorMessage = "Something went wrong";

        // Act
        ApiResponse<String> response = ApiResponse.error(errorMessage);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getMessage()).isEqualTo(errorMessage);
        assertThat(response.getData()).isNull();
    }

    @Test
    void shouldSerializeToJson() throws Exception {
        // Arrange
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Success")
                .data("test data")
                .correlationId("corr-123")
                .build();

        // Act
        String json = JsonUtils.toJson(response);

        // Assert
        assertThat(json).isNotNull();
        assertThat(json).contains("\"success\":true");
        assertThat(json).contains("\"message\":\"Success\"");
        assertThat(json).contains("\"data\":\"test data\"");
        assertThat(json).contains("\"correlationId\":\"corr-123\"");
    }

    @Test
    void shouldHandleGenericTypes() {
        // Arrange
        Integer data = 42;

        // Act
        ApiResponse<Integer> response = ApiResponse.success("Number result", data);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getData()).isInstanceOf(Integer.class);
        assertThat(response.getData()).isEqualTo(42);
    }
}
