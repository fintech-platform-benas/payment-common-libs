package com.paymentchain.events.transaction;

import com.paymentchain.domain.model.event.DomainEvent;

import java.math.BigDecimal;

/**
 * Event published when a transaction fails.
 *
 * @author benas
 */
public class TransactionFailedEvent extends DomainEvent {

    private Long transactionId;
    private String reference;
    private BigDecimal amount;
    private String failureReason;
    private String errorCode;

    public TransactionFailedEvent() {
        super();
    }

    private TransactionFailedEvent(Builder builder) {
        super();
        this.transactionId = builder.transactionId;
        this.reference = builder.reference;
        this.amount = builder.amount;
        this.failureReason = builder.failureReason;
        this.errorCode = builder.errorCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long transactionId;
        private String reference;
        private BigDecimal amount;
        private String failureReason;
        private String errorCode;

        public Builder transactionId(Long transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder failureReason(String failureReason) {
            this.failureReason = failureReason;
            return this;
        }

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public TransactionFailedEvent build() {
            return new TransactionFailedEvent(this);
        }
    }

    // Getters y Setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "TransactionFailedEvent{" +
                "eventId='" + getEventId() + '\'' +
                ", transactionId=" + transactionId +
                ", failureReason='" + failureReason + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
