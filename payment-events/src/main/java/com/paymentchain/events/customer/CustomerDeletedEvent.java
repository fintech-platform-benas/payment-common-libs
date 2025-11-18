package com.paymentchain.events.customer;

import com.paymentchain.domain.model.event.DomainEvent;

/**
 * Event published when a customer is deleted (soft delete).
 *
 * @author benas
 */
public class CustomerDeletedEvent extends DomainEvent {

    private Long customerId;
    private String deletedBy;
    private String reason;

    public CustomerDeletedEvent() {
        super();
    }

    private CustomerDeletedEvent(Builder builder) {
        super();
        this.customerId = builder.customerId;
        this.deletedBy = builder.deletedBy;
        this.reason = builder.reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long customerId;
        private String deletedBy;
        private String reason;

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder deletedBy(String deletedBy) {
            this.deletedBy = deletedBy;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public CustomerDeletedEvent build() {
            return new CustomerDeletedEvent(this);
        }
    }

    // Getters y Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "CustomerDeletedEvent{" +
                "eventId='" + getEventId() + '\'' +
                ", customerId=" + customerId +
                ", deletedBy='" + deletedBy + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
