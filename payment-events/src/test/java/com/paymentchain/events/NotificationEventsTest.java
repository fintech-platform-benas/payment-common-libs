package com.paymentchain.events;

import com.paymentchain.events.notification.NotificationRequestedEvent;
import com.paymentchain.events.notification.NotificationSentEvent;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for notification events.
 *
 * @author benas
 */
class NotificationEventsTest {

    @Test
    void shouldCreateNotificationRequestedEvent() {
        // Arrange & Act
        NotificationRequestedEvent event = NotificationRequestedEvent.builder()
            .recipient("john.doe@example.com")
            .notificationType("EMAIL")
            .subject("Transaction Confirmation")
            .message("Your transaction TX001 was successful")
            .template("transaction-confirmation")
            .priority("HIGH")
            .build();

        // Assert
        assertThat(event).isNotNull();
        assertThat(event.getEventId()).isNotNull();
        assertThat(event.getRecipient()).isEqualTo("john.doe@example.com");
        assertThat(event.getNotificationType()).isEqualTo("EMAIL");
        assertThat(event.getPriority()).isEqualTo("HIGH");
    }

    @Test
    void shouldCreateNotificationSentEvent() {
        // Arrange & Act
        NotificationSentEvent event = NotificationSentEvent.builder()
            .notificationId("NOTIF-001")
            .recipient("john.doe@example.com")
            .notificationType("EMAIL")
            .success(true)
            .provider("SendGrid")
            .sentAt(LocalDateTime.now())
            .build();

        // Assert
        assertThat(event).isNotNull();
        assertThat(event.getEventId()).isNotNull();
        assertThat(event.getNotificationId()).isEqualTo("NOTIF-001");
        assertThat(event.isSuccess()).isTrue();
        assertThat(event.getProvider()).isEqualTo("SendGrid");
    }

    @Test
    void shouldHandleFailedNotification() {
        // Arrange & Act
        NotificationSentEvent event = NotificationSentEvent.builder()
            .notificationId("NOTIF-002")
            .recipient("invalid@example.com")
            .notificationType("EMAIL")
            .success(false)
            .provider("SendGrid")
            .sentAt(LocalDateTime.now())
            .build();

        // Assert
        assertThat(event).isNotNull();
        assertThat(event.isSuccess()).isFalse();
    }
}
