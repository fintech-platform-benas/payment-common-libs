package com.paymentchain.events.notification;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paymentchain.domain.model.event.DomainEvent;

import java.time.LocalDateTime;

/**
 * Event published when a notification was successfully sent.
 *
 * @author benas
 */
public class NotificationSentEvent extends DomainEvent {

    private String notificationId;
    private String recipient;
    private String notificationType;
    private boolean success;
    private String provider;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime sentAt;

    public NotificationSentEvent() {
        super();
    }

    private NotificationSentEvent(Builder builder) {
        super();
        this.notificationId = builder.notificationId;
        this.recipient = builder.recipient;
        this.notificationType = builder.notificationType;
        this.success = builder.success;
        this.provider = builder.provider;
        this.sentAt = builder.sentAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String notificationId;
        private String recipient;
        private String notificationType;
        private boolean success;
        private String provider;
        private LocalDateTime sentAt;

        public Builder notificationId(String notificationId) {
            this.notificationId = notificationId;
            return this;
        }

        public Builder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder notificationType(String notificationType) {
            this.notificationType = notificationType;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder provider(String provider) {
            this.provider = provider;
            return this;
        }

        public Builder sentAt(LocalDateTime sentAt) {
            this.sentAt = sentAt;
            return this;
        }

        public NotificationSentEvent build() {
            return new NotificationSentEvent(this);
        }
    }

    // Getters y Setters
    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public String toString() {
        return "NotificationSentEvent{" +
                "eventId='" + getEventId() + '\'' +
                ", notificationId='" + notificationId + '\'' +
                ", recipient='" + recipient + '\'' +
                ", success=" + success +
                '}';
    }
}
