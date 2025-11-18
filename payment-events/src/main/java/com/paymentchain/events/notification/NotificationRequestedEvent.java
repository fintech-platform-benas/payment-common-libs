package com.paymentchain.events.notification;

import com.paymentchain.domain.model.event.DomainEvent;

/**
 * Event published when a notification needs to be sent.
 *
 * @author benas
 */
public class NotificationRequestedEvent extends DomainEvent {

    private String recipient;
    private String notificationType; // EMAIL, SMS, PUSH
    private String subject;
    private String message;
    private String template;
    private String priority; // HIGH, MEDIUM, LOW

    public NotificationRequestedEvent() {
        super();
    }

    private NotificationRequestedEvent(Builder builder) {
        super();
        this.recipient = builder.recipient;
        this.notificationType = builder.notificationType;
        this.subject = builder.subject;
        this.message = builder.message;
        this.template = builder.template;
        this.priority = builder.priority;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String recipient;
        private String notificationType;
        private String subject;
        private String message;
        private String template;
        private String priority;

        public Builder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder notificationType(String notificationType) {
            this.notificationType = notificationType;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder template(String template) {
            this.template = template;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public NotificationRequestedEvent build() {
            return new NotificationRequestedEvent(this);
        }
    }

    // Getters y Setters
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "NotificationRequestedEvent{" +
                "eventId='" + getEventId() + '\'' +
                ", recipient='" + recipient + '\'' +
                ", notificationType='" + notificationType + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
