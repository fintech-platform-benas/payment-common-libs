package com.paymentchain.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paymentchain.events.customer.CustomerCreatedEvent;
import com.paymentchain.events.customer.CustomerUpdatedEvent;
import com.paymentchain.events.customer.CustomerDeletedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for customer events.
 *
 * @author benas
 */
class CustomerEventsTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void shouldCreateCustomerCreatedEvent() {
        // Arrange & Act
        CustomerCreatedEvent event = CustomerCreatedEvent.builder()
            .customerId(1L)
            .name("John")
            .surname("Doe")
            .email("john.doe@example.com")
            .phone("+34600123456")
            .iban("ES1234567890123456789012")
            .build();

        // Assert
        assertThat(event).isNotNull();
        assertThat(event.getEventId()).isNotNull();
        assertThat(event.getCustomerId()).isEqualTo(1L);
        assertThat(event.getName()).isEqualTo("John");
        assertThat(event.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    void shouldSerializeCustomerCreatedEvent() throws Exception {
        // Arrange
        CustomerCreatedEvent event = CustomerCreatedEvent.builder()
            .customerId(1L)
            .name("John")
            .surname("Doe")
            .email("john.doe@example.com")
            .phone("+34600123456")
            .iban("ES1234567890123456789012")
            .build();

        // Act
        String json = objectMapper.writeValueAsString(event);

        // Assert
        assertThat(json).contains("john.doe@example.com");
        assertThat(json).contains("ES1234567890123456789012");
    }

    @Test
    void shouldCreateCustomerUpdatedEvent() {
        // Arrange & Act
        CustomerUpdatedEvent event = CustomerUpdatedEvent.builder()
            .customerId(1L)
            .name("Jane")
            .surname("Doe")
            .email("jane.doe@example.com")
            .phone("+34600654321")
            .build();

        // Assert
        assertThat(event).isNotNull();
        assertThat(event.getEventId()).isNotNull();
        assertThat(event.getCustomerId()).isEqualTo(1L);
        assertThat(event.getName()).isEqualTo("Jane");
    }

    @Test
    void shouldCreateCustomerDeletedEvent() {
        // Arrange & Act
        CustomerDeletedEvent event = CustomerDeletedEvent.builder()
            .customerId(1L)
            .deletedBy("admin")
            .reason("Customer request")
            .build();

        // Assert
        assertThat(event).isNotNull();
        assertThat(event.getEventId()).isNotNull();
        assertThat(event.getCustomerId()).isEqualTo(1L);
        assertThat(event.getDeletedBy()).isEqualTo("admin");
        assertThat(event.getReason()).isEqualTo("Customer request");
    }
}
