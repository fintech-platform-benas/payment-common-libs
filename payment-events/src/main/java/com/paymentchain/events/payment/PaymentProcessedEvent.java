package com.paymentchain.events.payment;

import com.paymentchain.domain.model.event.DomainEvent;

import java.math.BigDecimal;

/**
 * Event published when a payment is processed.
 *
 * @author benas
 */
public class PaymentProcessedEvent extends DomainEvent {

    private Long paymentId;
    private String paymentReference;
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private String status;

    public PaymentProcessedEvent() {
        super();
    }

    private PaymentProcessedEvent(Builder builder) {
        super();
        this.paymentId = builder.paymentId;
        this.paymentReference = builder.paymentReference;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.paymentMethod = builder.paymentMethod;
        this.status = builder.status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long paymentId;
        private String paymentReference;
        private BigDecimal amount;
        private String currency;
        private String paymentMethod;
        private String status;

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

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public PaymentProcessedEvent build() {
            return new PaymentProcessedEvent(this);
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentProcessedEvent{" +
                "eventId='" + getEventId() + '\'' +
                ", paymentId=" + paymentId +
                ", paymentReference='" + paymentReference + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
