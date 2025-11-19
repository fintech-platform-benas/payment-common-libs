package com.paymentchain.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents a validation error for a specific field.
 *
 * @author benas
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationError {

    private String field;
    private String message;
    private Object rejectedValue;
    private String code;

    public ValidationError() {
    }

    public ValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public ValidationError(String field, String message, Object rejectedValue) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    private ValidationError(Builder builder) {
        this.field = builder.field;
        this.message = builder.message;
        this.rejectedValue = builder.rejectedValue;
        this.code = builder.code;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String field;
        private String message;
        private Object rejectedValue;
        private String code;

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder rejectedValue(Object rejectedValue) {
            this.rejectedValue = rejectedValue;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public ValidationError build() {
            return new ValidationError(this);
        }
    }

    // Getters and setters
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
