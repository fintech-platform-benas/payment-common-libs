package com.paymentchain.events.transaction;

import com.paymentchain.domain.model.event.DomainEvent;

/**
 * Event triggered when a transaction fails.
 *
 * @author benas
 */
public class TransactionFailedEvent extends DomainEvent {

    private final String transactionId;
    private final String reason;
    private final String errorCode;

    public TransactionFailedEvent(String transactionId, String reason, String errorCode) {
        super();
        this.transactionId = transactionId;
        this.reason = reason;
        this.errorCode = errorCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getReason() {
        return reason;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
