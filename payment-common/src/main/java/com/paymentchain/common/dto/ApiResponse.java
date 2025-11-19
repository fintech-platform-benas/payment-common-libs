package com.paymentchain.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

/**
 * Generic API response wrapper with Builder pattern.
 *
 * @param <T> The type of the response data
 * @author benas
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant timestamp;
    private boolean success;
    private String message;
    private T data;
    private String correlationId;

    public ApiResponse() {
        this.timestamp = Instant.now();
    }

    private ApiResponse(Builder<T> builder) {
        this.timestamp = builder.timestamp != null ? builder.timestamp : Instant.now();
        this.success = builder.success;
        this.message = builder.message;
        this.data = builder.data;
        this.correlationId = builder.correlationId;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    /**
     * Creates a successful response with data.
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    /**
     * Creates a successful response with message and data.
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * Creates an error response with message.
     */
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }

    public static class Builder<T> {
        private Instant timestamp;
        private boolean success;
        private String message;
        private T data;
        private String correlationId;

        public Builder<T> timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> correlationId(String correlationId) {
            this.correlationId = correlationId;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse<>(this);
        }
    }

    // Getters and setters
    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
