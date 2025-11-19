package com.paymentchain.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Standard error response for API errors with Builder pattern.
 *
 * @author benas
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String correlationId;
    private List<ValidationError> validationErrors;

    public ErrorResponse() {
        this.timestamp = Instant.now();
        this.validationErrors = new ArrayList<>();
    }

    private ErrorResponse(Builder builder) {
        this.timestamp = builder.timestamp != null ? builder.timestamp : Instant.now();
        this.status = builder.status;
        this.error = builder.error;
        this.message = builder.message;
        this.path = builder.path;
        this.correlationId = builder.correlationId;
        this.validationErrors = builder.validationErrors != null ? builder.validationErrors : new ArrayList<>();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Instant timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
        private String correlationId;
        private List<ValidationError> validationErrors;

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder correlationId(String correlationId) {
            this.correlationId = correlationId;
            return this;
        }

        public Builder validationErrors(List<ValidationError> validationErrors) {
            this.validationErrors = validationErrors;
            return this;
        }

        public Builder addValidationError(String field, String message) {
            if (this.validationErrors == null) {
                this.validationErrors = new ArrayList<>();
            }
            this.validationErrors.add(new ValidationError(field, message));
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }

    public void addValidationError(String field, String message) {
        validationErrors.add(new ValidationError(field, message));
    }

    // Getters and setters
    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
