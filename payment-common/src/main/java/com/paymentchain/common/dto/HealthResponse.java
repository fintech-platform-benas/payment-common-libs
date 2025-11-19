package com.paymentchain.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Health check response for microservices.
 *
 * @author benas
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthResponse {

    private String status;
    private String serviceName;
    private String version;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant timestamp;

    private Map<String, Object> details;

    public HealthResponse() {
        this.timestamp = Instant.now();
        this.details = new HashMap<>();
    }

    private HealthResponse(Builder builder) {
        this.status = builder.status;
        this.serviceName = builder.serviceName;
        this.version = builder.version;
        this.timestamp = builder.timestamp != null ? builder.timestamp : Instant.now();
        this.details = builder.details != null ? builder.details : new HashMap<>();
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Creates a healthy response.
     */
    public static HealthResponse up(String serviceName) {
        return HealthResponse.builder()
                .status("UP")
                .serviceName(serviceName)
                .build();
    }

    /**
     * Creates an unhealthy response.
     */
    public static HealthResponse down(String serviceName) {
        return HealthResponse.builder()
                .status("DOWN")
                .serviceName(serviceName)
                .build();
    }

    public static class Builder {
        private String status;
        private String serviceName;
        private String version;
        private Instant timestamp;
        private Map<String, Object> details;

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder serviceName(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder details(Map<String, Object> details) {
            this.details = details;
            return this;
        }

        public Builder addDetail(String key, Object value) {
            if (this.details == null) {
                this.details = new HashMap<>();
            }
            this.details.put(key, value);
            return this;
        }

        public HealthResponse build() {
            return new HealthResponse(this);
        }
    }

    public void addDetail(String key, Object value) {
        this.details.put(key, value);
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }
}
