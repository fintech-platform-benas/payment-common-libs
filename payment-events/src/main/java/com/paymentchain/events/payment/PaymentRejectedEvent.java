package com.paymentchain.events.payment;

import com.paymentchain.domain.model.event.DomainEvent;

import java.math.BigDecimal;

/**
 * Event published when a payment is rejected.
 *
 * @author benas
 */
public class PaymentRejectedEvent extends DomainEvent {

    private Long paymentId;
    private String paymentReference;
    private BigDecimal amount;
    private String currency;
    private String rejectionReason;
    private String rejectionCode;
    private String gateway;

    public PaymentRejectedEvent() {
        super();
    }

    private PaymentRejectedEvent(Builder builder) {
        super();
        this.paymentId = builder.paymentId;
        this.paymentReference = builder.paymentReference;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.rejectionReason = builder.rejectionReason;
        this.rejectionCode = builder.rejectionCode;
        this.gateway = builder.gateway;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long paymentId;
        private String paymentReference;
        private BigDecimal amount;
        private String currency;
        private String rejectionReason;
        private String rejectionCode;
        private String gateway;

        public Builder paymentId(Long paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder paymentReference(String paymentReference) {
            this.paymentReference = paymentReference;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder rejectionReason(String rejectionReason) {
            this.rejectionReason = rejectionReason;
            return this;
        }

        public Builder rejectionCode(String rejectionCode) {
            this.rejectionCode = rejectionCode;
            return this;
        }

        public Builder gateway(String gateway) {
            this.gateway = gateway;
            return this;
        }

        public PaymentRejectedEvent build() {
            return new PaymentRejectedEvent(this);
        }
    }

    // Getters y Setters
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getRejectionCode() {
        return rejectionCode;
    }

    public void setRejectionCode(String rejectionCode) {
        this.rejectionCode = rejectionCode;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    @Override
    public String toString() {
        return "PaymentRejectedEvent{" +
                "eventId='" + getEventId() + '\'' +
                ", paymentId=" + paymentId +
                ", rejectionReason='" + rejectionReason + '\'' +
                ", rejectionCode='" + rejectionCode + '\'' +
                '}';
    }
}
