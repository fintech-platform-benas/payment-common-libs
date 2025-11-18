package com.paymentchain.events.customer;

import com.paymentchain.domain.model.event.DomainEvent;
import com.paymentchain.domain.model.valueobject.Email;

/**
 * Event triggered when a customer is created.
 *
 * @author benas
 */
public class CustomerCreatedEvent extends DomainEvent {

    private final String customerId;
    private final String name;
    private final Email email;

    public CustomerCreatedEvent(String customerId, String name, Email email) {
        super();
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }
}
