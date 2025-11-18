# Payment Common Libraries

Shared libraries for Payment Chain microservices platform implementing Domain-Driven Design (DDD) and Hexagonal Architecture patterns.

## Architecture

This repository contains common domain models, value objects, events, and utilities shared across all microservices in the Payment Chain platform.

### Modules

```
payment-common-libs/
├── payment-domain/         # Core domain models and value objects
├── payment-events/         # Domain events for event-driven architecture
└── payment-common/         # Common DTOs, utilities, and exception handling
```

## Modules Overview

### payment-domain

Core domain layer containing:

- **Value Objects**: Immutable, self-validating domain primitives
  - `Money`: Monetary amounts with currency
  - `IBAN`: Spanish IBAN validation
  - `Email`: Email address validation
  - `Currency`: Supported currencies (EUR, USD, GBP)

- **Domain Events**: Base `DomainEvent` abstract class for event sourcing

- **Exceptions**: Domain-specific exceptions
  - `DomainException`: Base exception for domain violations
  - `BusinessRuleException`: Business rule violations

### payment-events

Domain events for microservice communication:

**Transaction Events**:
- `TransactionCreatedEvent`
- `TransactionSettledEvent`
- `TransactionFailedEvent`

**Customer Events**:
- `CustomerCreatedEvent`
- `CustomerUpdatedEvent`

**Notification Events**:
- `NotificationRequestedEvent`

### payment-common

Common infrastructure components:

- **DTOs**: `ErrorResponse`, `PageResponse<T>`
- **Exception Handling**: `GlobalExceptionHandler`
- **Utilities**:
  - `CorrelationIdUtils`: Request tracing
  - `ValidationUtils`: Common validation helpers

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+

### Building the Project

```bash
cd payment-common-libs
mvn clean install
```

This will build all modules and install them to your local Maven repository.

### Running Tests

```bash
mvn test
```

## Usage Examples

### Using Money Value Object

```java
import com.paymentchain.domain.model.valueobject.Money;
import com.paymentchain.domain.model.valueobject.Currency;
import java.math.BigDecimal;

// Create money instance
Money amount = Money.of(new BigDecimal("100.50"), Currency.EUR);

// Arithmetic operations
Money total = amount.add(Money.of(new BigDecimal("50.00"), Currency.EUR));

// Comparisons
if (total.isGreaterThan(amount)) {
    System.out.println("Total is greater!");
}

// Zero amount
Money zero = Money.zero(Currency.EUR);
```

### Using IBAN Value Object

```java
import com.paymentchain.domain.model.valueobject.IBAN;

// Create and validate Spanish IBAN
IBAN iban = IBAN.of("ES1234567890123456789012");

// Get masked version for display
String masked = iban.getMasked(); // "ES12****9012"

// With spaces (automatically normalized)
IBAN iban2 = IBAN.of("ES12 3456 7890 1234 5678 9012");
```

### Using Email Value Object

```java
import com.paymentchain.domain.model.valueobject.Email;

// Create and validate email
Email email = Email.of("user@example.com");

// Get masked version
String masked = email.getMasked(); // "u***@example.com"
```

### Publishing Domain Events

```java
import com.paymentchain.events.transaction.TransactionCreatedEvent;
import com.paymentchain.domain.model.valueobject.Money;
import com.paymentchain.domain.model.valueobject.Currency;
import java.math.BigDecimal;

// Create event
Money amount = Money.of(new BigDecimal("100.00"), Currency.EUR);
TransactionCreatedEvent event = new TransactionCreatedEvent(
    "txn-123",
    amount,
    "customer-456"
);

// Event contains auto-generated metadata
String eventId = event.getEventId();
Instant occurredOn = event.getOccurredOn();
String eventType = event.getEventType();
```

### Using Common Utilities

```java
import com.paymentchain.common.util.ValidationUtils;
import com.paymentchain.common.util.CorrelationIdUtils;

// Validation
ValidationUtils.requireNonEmpty(customerName, "customerName");
ValidationUtils.requirePositive(amount, "amount");

// Correlation ID management
String correlationId = CorrelationIdUtils.generateCorrelationId();
CorrelationIdUtils.setCorrelationId(correlationId);
```

## Adding as Dependency

Once published to GitHub Packages, add to your microservice `pom.xml`:

```xml
<dependencies>
    <!-- Domain models and value objects -->
    <dependency>
        <groupId>com.paymentchain</groupId>
        <artifactId>payment-domain</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>

    <!-- Domain events -->
    <dependency>
        <groupId>com.paymentchain</groupId>
        <artifactId>payment-events</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>

    <!-- Common utilities -->
    <dependency>
        <groupId>com.paymentchain</groupId>
        <artifactId>payment-common</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## Design Principles

### Value Objects

All value objects are:
- **Immutable**: Cannot be changed after creation
- **Self-validating**: Validate in constructor/factory method
- **Equality by value**: Two instances with same value are equal

### Domain Events

All domain events:
- Extend `DomainEvent` base class
- Auto-generate unique event ID
- Capture occurrence timestamp
- Are immutable

### Exception Handling

- Domain exceptions for business logic violations
- Global exception handler for REST APIs
- Standardized error responses

## Testing

The project includes comprehensive tests:

- **MoneyTest**: 14 test cases covering all operations
- **IBANTest**: 12 test cases for validation and formatting
- **EmailTest**: 12 test cases for validation and masking

Total: **38+ unit tests**

## CI/CD

GitHub Actions workflow automatically:
- Builds all modules
- Runs all tests
- Validates code quality

## Contributing

1. Create a feature branch
2. Make your changes
3. Add/update tests
4. Ensure all tests pass: `mvn test`
5. Submit a pull request

## License

Copyright (c) 2025 Payment Chain - Benas

## Author

**Benas** - Fintech Platform Developer

## Next Steps

- **Phase 1B**: Add more domain events
- **Phase 1C**: Add integration tests
- **Phase 2**: Create `payment-transaction-service` using these libraries
