package com.paymentchain.events.customer;

import com.paymentchain.domain.model.event.DomainEvent;

/**
 * Event triggered when customer information is updated.
 *
 * @author benas
 */
public class CustomerUpdatedEvent extends DomainEvent {

    private final String customerId;
    private final String updatedField;
    private final String newValue;

    public CustomerUpdatedEvent(String customerId, String updatedField, String newValue) {
        super();
        this.customerId = customerId;
        this.updatedField = updatedField;
        this.newValue = newValue;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getUpdatedField() {
        return updatedField;
    }

    public String getNewValue() {
        return newValue;
    }
}
