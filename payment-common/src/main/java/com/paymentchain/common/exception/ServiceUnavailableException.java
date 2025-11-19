package com.paymentchain.common.exception;

/**
 * Exception thrown when a service is temporarily unavailable.
 *
 * @author benas
 */
public class ServiceUnavailableException extends RuntimeException {

    private String serviceName;
    private Integer retryAfterSeconds;

    public ServiceUnavailableException(String message) {
        super(message);
    }

    public ServiceUnavailableException(String serviceName, String message) {
        super(message);
        this.serviceName = serviceName;
    }

    public ServiceUnavailableException(String serviceName, String message, Integer retryAfterSeconds) {
        super(message);
        this.serviceName = serviceName;
        this.retryAfterSeconds = retryAfterSeconds;
    }

    public ServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getServiceName() {
        return serviceName;
    }

    public Integer getRetryAfterSeconds() {
        return retryAfterSeconds;
    }
}
