package com.paymentchain.events.transaction;

import com.paymentchain.domain.model.event.DomainEvent;

/**
 * Event triggered when a transaction is settled.
 *
 * @author benas
 */
public class TransactionSettledEvent extends DomainEvent {

    private final String transactionId;
    private final String settlementId;

    public TransactionSettledEvent(String transactionId, String settlementId) {
        super();
        this.transactionId = transactionId;
        this.settlementId = settlementId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getSettlementId() {
        return settlementId;
    }
}
