package com.paymentchain.common.util;

import java.util.Collection;

/**
 * Utility class for common validation operations.
 *
 * @author benas
 */
public class ValidationUtils {

    private ValidationUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Check if a string is null or empty.
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Require a non-null object.
     *
     * @throws IllegalArgumentException if object is null
     */
    public static <T> T requireNonNull(T obj, String fieldName) {
        if (obj == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
        return obj;
    }

    /**
     * Require a non-empty string.
     *
     * @throws IllegalArgumentException if string is null or empty
     */
    public static String requireNonEmpty(String str, String fieldName) {
        if (isNullOrEmpty(str)) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        return str;
    }

    /**
     * Require a non-empty collection.
     *
     * @throws IllegalArgumentException if collection is null or empty
     */
    public static <T extends Collection<?>> T requireNonEmpty(T collection, String fieldName) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        return collection;
    }

    /**
     * Require a positive number.
     *
     * @throws IllegalArgumentException if number is not positive
     */
    public static <T extends Number & Comparable<T>> T requirePositive(T number, String fieldName) {
        requireNonNull(number, fieldName);
        if (number.doubleValue() <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
        return number;
    }

    /**
     * Require a non-negative number.
     *
     * @throws IllegalArgumentException if number is negative
     */
    public static <T extends Number & Comparable<T>> T requireNonNegative(T number, String fieldName) {
        requireNonNull(number, fieldName);
        if (number.doubleValue() < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return number;
    }
}
