package com.paymentchain.events.notification;

import com.paymentchain.domain.model.event.DomainEvent;
import com.paymentchain.domain.model.valueobject.Email;

/**
 * Event triggered when a notification is requested.
 *
 * @author benas
 */
public class NotificationRequestedEvent extends DomainEvent {

    private final String notificationId;
    private final Email recipient;
    private final String message;
    private final String notificationType;

    public NotificationRequestedEvent(String notificationId, Email recipient, String message, String notificationType) {
        super();
        this.notificationId = notificationId;
        this.recipient = recipient;
        this.message = message;
        this.notificationType = notificationType;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public Email getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getNotificationType() {
        return notificationType;
    }
}
