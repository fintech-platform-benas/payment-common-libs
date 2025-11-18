package com.paymentchain.events.transaction;

import com.paymentchain.domain.model.event.DomainEvent;

/**
 * Event published when a transaction is cancelled.
 *
 * @author benas
 */
public class TransactionCancelledEvent extends DomainEvent {

    private Long transactionId;
    private String reference;
    private String cancelledBy;
    private String cancellationReason;

    public TransactionCancelledEvent() {
        super();
    }

    private TransactionCancelledEvent(Builder builder) {
        super();
        this.transactionId = builder.transactionId;
        this.reference = builder.reference;
        this.cancelledBy = builder.cancelledBy;
        this.cancellationReason = builder.cancellationReason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long transactionId;
        private String reference;
        private String cancelledBy;
        private String cancellationReason;

        public Builder transactionId(Long transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder cancelledBy(String cancelledBy) {
            this.cancelledBy = cancelledBy;
            return this;
        }

        public Builder cancellationReason(String cancellationReason) {
            this.cancellationReason = cancellationReason;
            return this;
        }

        public TransactionCancelledEvent build() {
            return new TransactionCancelledEvent(this);
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

    public String getCancelledBy() {
        return cancelledBy;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    @Override
    public String toString() {
        return "TransactionCancelledEvent{" +
                "eventId='" + getEventId() + '\'' +
                ", transactionId=" + transactionId +
                ", reference='" + reference + '\'' +
                ", cancelledBy='" + cancelledBy + '\'' +
                ", cancellationReason='" + cancellationReason + '\'' +
                '}';
    }
}
