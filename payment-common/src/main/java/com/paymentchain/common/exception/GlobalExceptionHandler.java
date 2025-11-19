package com.paymentchain.common.exception;

import com.paymentchain.common.dto.ErrorResponse;
import com.paymentchain.common.dto.ValidationError;
import com.paymentchain.common.util.CorrelationIdUtils;
import com.paymentchain.domain.exception.BusinessRuleException;
import com.paymentchain.domain.exception.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for REST controllers.
 * Handles all exceptions and converts them to standardized error responses.
 *
 * @author benas
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles validation exceptions from @Valid annotations.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        logger.warn("Validation failed: {}", ex.getMessage());

        List<ValidationError> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::mapFieldError)
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Input validation error")
                .path(extractPath(request))
                .correlationId(CorrelationIdUtils.getCorrelationId())
                .validationErrors(validationErrors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handles ResourceNotFoundException.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request) {

        logger.warn("Resource not found: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .path(extractPath(request))
                .correlationId(CorrelationIdUtils.getCorrelationId())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handles InvalidRequestException.
     */
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(
            InvalidRequestException ex,
            WebRequest request) {

        logger.warn("Invalid request: {}", ex.getMessage());

        List<ValidationError> validationErrors = ex.getValidationErrors()
                .stream()
                .map(error -> ValidationError.builder()
                        .message(error)
                        .build())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .path(extractPath(request))
                .correlationId(CorrelationIdUtils.getCorrelationId())
                .validationErrors(validationErrors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handles ServiceUnavailableException.
     */
    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleServiceUnavailableException(
            ServiceUnavailableException ex,
            WebRequest request) {

        logger.error("Service unavailable: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error("Service Unavailable")
                .message(ex.getMessage())
                .path(extractPath(request))
                .correlationId(CorrelationIdUtils.getCorrelationId())
                .build();

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

    /**
     * Handles DomainException from payment-domain module.
     */
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(
            DomainException ex,
            WebRequest request) {

        logger.warn("Domain exception: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Domain Error")
                .message(ex.getMessage())
                .path(extractPath(request))
                .correlationId(CorrelationIdUtils.getCorrelationId())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handles BusinessRuleException from payment-domain module.
     */
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRuleException(
            BusinessRuleException ex,
            WebRequest request) {

        logger.warn("Business rule violation: {} - {}", ex.getRuleViolated(), ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error("Business Rule Violation")
                .message(ex.getMessage())
                .path(extractPath(request))
                .correlationId(CorrelationIdUtils.getCorrelationId())
                .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    /**
     * Handles IllegalArgumentException.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex,
            WebRequest request) {

        logger.warn("Illegal argument: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .path(extractPath(request))
                .correlationId(CorrelationIdUtils.getCorrelationId())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handles MethodArgumentTypeMismatchException.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            WebRequest request) {

        logger.warn("Type mismatch: {}", ex.getMessage());

        String message = String.format("Invalid value '%s' for parameter '%s'. Expected type: %s",
                ex.getValue(), ex.getName(), ex.getRequiredType().getSimpleName());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Type Mismatch")
                .message(message)
                .path(extractPath(request))
                .correlationId(CorrelationIdUtils.getCorrelationId())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handles all other exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex,
            WebRequest request) {

        logger.error("Unexpected error occurred", ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("An unexpected error occurred. Please try again later.")
                .path(extractPath(request))
                .correlationId(CorrelationIdUtils.getCorrelationId())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    /**
     * Maps FieldError to ValidationError.
     */
    private ValidationError mapFieldError(FieldError fieldError) {
        return ValidationError.builder()
                .field(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .rejectedValue(fieldError.getRejectedValue())
                .code(fieldError.getCode())
                .build();
    }

    /**
     * Extracts the request path from WebRequest.
     */
    private String extractPath(WebRequest request) {
        String description = request.getDescription(false);
        return description.replace("uri=", "");
    }
}
