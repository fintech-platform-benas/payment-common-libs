package com.paymentchain.events.customer;

import com.paymentchain.domain.model.event.DomainEvent;

/**
 * Event published when a customer is created.
 *
 * @author benas
 */
public class CustomerCreatedEvent extends DomainEvent {

    private Long customerId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String iban;

    public CustomerCreatedEvent() {
        super();
    }

    private CustomerCreatedEvent(Builder builder) {
        super();
        this.customerId = builder.customerId;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.phone = builder.phone;
        this.iban = builder.iban;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long customerId;
        private String name;
        private String surname;
        private String email;
        private String phone;
        private String iban;

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder iban(String iban) {
            this.iban = iban;
            return this;
        }

        public CustomerCreatedEvent build() {
            return new CustomerCreatedEvent(this);
        }
    }

    // Getters y Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "CustomerCreatedEvent{" +
                "eventId='" + getEventId() + '\'' +
                ", customerId=" + customerId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
