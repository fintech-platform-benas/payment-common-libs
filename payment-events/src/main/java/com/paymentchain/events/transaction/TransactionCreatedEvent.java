package com.paymentchain.events.transaction;

import com.paymentchain.domain.model.event.DomainEvent;
import com.paymentchain.domain.model.valueobject.Money;

/**
 * Event triggered when a transaction is created.
 *
 * @author benas
 */
public class TransactionCreatedEvent extends DomainEvent {

    private final String transactionId;
    private final Money amount;
    private final String customerId;

    public TransactionCreatedEvent(String transactionId, Money amount, String customerId) {
        super();
        this.transactionId = transactionId;
        this.amount = amount;
        this.customerId = customerId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Money getAmount() {
        return amount;
    }

    public String getCustomerId() {
        return customerId;
    }
}
