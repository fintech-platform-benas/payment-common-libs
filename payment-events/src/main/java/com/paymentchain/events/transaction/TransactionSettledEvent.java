package com.paymentchain.events.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paymentchain.domain.model.event.DomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Event published when a transaction is settled (completed).
 *
 * @author benas
 */
public class TransactionSettledEvent extends DomainEvent {

    private Long transactionId;
    private String reference;
    private BigDecimal amount;
    private String currency;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime settledAt;

    private String settlementMethod;

    public TransactionSettledEvent() {
        super();
    }

    private TransactionSettledEvent(Builder builder) {
        super();
        this.transactionId = builder.transactionId;
        this.reference = builder.reference;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.settledAt = builder.settledAt;
        this.settlementMethod = builder.settlementMethod;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long transactionId;
        private String reference;
        private BigDecimal amount;
        private String currency;
        private LocalDateTime settledAt;
        private String settlementMethod;

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

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder settledAt(LocalDateTime settledAt) {
            this.settledAt = settledAt;
            return this;
        }

        public Builder settlementMethod(String settlementMethod) {
            this.settlementMethod = settlementMethod;
            return this;
        }

        public TransactionSettledEvent build() {
            return new TransactionSettledEvent(this);
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getSettledAt() {
        return settledAt;
    }

    public void setSettledAt(LocalDateTime settledAt) {
        this.settledAt = settledAt;
    }

    public String getSettlementMethod() {
        return settlementMethod;
    }

    public void setSettlementMethod(String settlementMethod) {
        this.settlementMethod = settlementMethod;
    }

    @Override
    public String toString() {
        return "TransactionSettledEvent{" +
                "eventId='" + getEventId() + '\'' +
                ", transactionId=" + transactionId +
                ", reference='" + reference + '\'' +
                ", amount=" + amount +
                ", settledAt=" + settledAt +
                '}';
    }
}
