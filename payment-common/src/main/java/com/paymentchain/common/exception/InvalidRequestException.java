package com.paymentchain.common.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Exception thrown when a request contains invalid data.
 *
 * @author benas
 */
public class InvalidRequestException extends RuntimeException {

    private List<String> validationErrors;

    public InvalidRequestException(String message) {
        super(message);
        this.validationErrors = new ArrayList<>();
    }

    public InvalidRequestException(String message, List<String> validationErrors) {
        super(message);
        this.validationErrors = validationErrors != null ? validationErrors : new ArrayList<>();
    }

    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
        this.validationErrors = new ArrayList<>();
    }

    public void addValidationError(String error) {
        this.validationErrors.add(error);
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
