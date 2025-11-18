package com.paymentchain.events.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paymentchain.domain.model.event.DomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Event published when a payment is authorized.
 *
 * @author benas
 */
public class PaymentAuthorizedEvent extends DomainEvent {

    private Long paymentId;
    private String authorizationCode;
    private BigDecimal authorizedAmount;
    private String currency;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime authorizedAt;

    private String gateway;

    public PaymentAuthorizedEvent() {
        super();
    }

    private PaymentAuthorizedEvent(Builder builder) {
        super();
        this.paymentId = builder.paymentId;
        this.authorizationCode = builder.authorizationCode;
        this.authorizedAmount = builder.authorizedAmount;
        this.currency = builder.currency;
        this.authorizedAt = builder.authorizedAt;
        this.gateway = builder.gateway;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long paymentId;
        private String authorizationCode;
        private BigDecimal authorizedAmount;
        private String currency;
        private LocalDateTime authorizedAt;
        private String gateway;

        public Builder paymentId(Long paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder authorizationCode(String authorizationCode) {
            this.authorizationCode = authorizationCode;
            return this;
        }

        public Builder authorizedAmount(BigDecimal authorizedAmount) {
            this.authorizedAmount = authorizedAmount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder authorizedAt(LocalDateTime authorizedAt) {
            this.authorizedAt = authorizedAt;
            return this;
        }

        public Builder gateway(String gateway) {
            this.gateway = gateway;
            return this;
        }

        public PaymentAuthorizedEvent build() {
            return new PaymentAuthorizedEvent(this);
        }
    }

    // Getters y Setters
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public BigDecimal getAuthorizedAmount() {
        return authorizedAmount;
    }

    public void setAuthorizedAmount(BigDecimal authorizedAmount) {
        this.authorizedAmount = authorizedAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getAuthorizedAt() {
        return authorizedAt;
    }

    public void setAuthorizedAt(LocalDateTime authorizedAt) {
        this.authorizedAt = authorizedAt;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    @Override
    public String toString() {
        return "PaymentAuthorizedEvent{" +
                "eventId='" + getEventId() + '\'' +
                ", paymentId=" + paymentId +
                ", authorizationCode='" + authorizationCode + '\'' +
                ", authorizedAmount=" + authorizedAmount +
                ", gateway='" + gateway + '\'' +
                '}';
    }
}
