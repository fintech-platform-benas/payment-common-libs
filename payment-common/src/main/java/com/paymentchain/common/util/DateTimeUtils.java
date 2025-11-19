package com.paymentchain.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for date and time operations.
 *
 * @author benas
 */
public class DateTimeUtils {

    public static final String ISO_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DISPLAY_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String DISPLAY_DATE_FORMAT = "dd/MM/yyyy";

    public static final DateTimeFormatter ISO_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(ISO_DATE_TIME_FORMAT);
    public static final DateTimeFormatter ISO_DATE_FORMATTER = DateTimeFormatter.ofPattern(ISO_DATE_FORMAT);
    public static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DISPLAY_DATE_TIME_FORMAT);
    public static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern(DISPLAY_DATE_FORMAT);

    private DateTimeUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Get current UTC instant.
     */
    public static Instant now() {
        return Instant.now();
    }

    /**
     * Get current UTC LocalDateTime.
     */
    public static LocalDateTime nowLocalDateTime() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    /**
     * Get current UTC LocalDate.
     */
    public static LocalDate nowLocalDate() {
        return LocalDate.now(ZoneOffset.UTC);
    }

    /**
     * Convert Instant to LocalDateTime in UTC.
     */
    public static LocalDateTime toLocalDateTime(Instant instant) {
        if (instant == null) {
            return null;
        }
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    /**
     * Convert LocalDateTime to Instant assuming UTC.
     */
    public static Instant toInstant(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.toInstant(ZoneOffset.UTC);
    }

    /**
     * Format Instant to ISO date-time string.
     */
    public static String formatIsoDateTime(Instant instant) {
        if (instant == null) {
            return null;
        }
        return ISO_DATE_TIME_FORMATTER.format(toLocalDateTime(instant));
    }

    /**
     * Format LocalDateTime to ISO date-time string.
     */
    public static String formatIsoDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return ISO_DATE_TIME_FORMATTER.format(localDateTime);
    }

    /**
     * Format LocalDate to ISO date string.
     */
    public static String formatIsoDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return ISO_DATE_FORMATTER.format(localDate);
    }

    /**
     * Format Instant to display date-time string.
     */
    public static String formatDisplayDateTime(Instant instant) {
        if (instant == null) {
            return null;
        }
        return DISPLAY_DATE_TIME_FORMATTER.format(toLocalDateTime(instant));
    }

    /**
     * Format LocalDateTime to display date-time string.
     */
    public static String formatDisplayDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return DISPLAY_DATE_TIME_FORMATTER.format(localDateTime);
    }

    /**
     * Format LocalDate to display date string.
     */
    public static String formatDisplayDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return DISPLAY_DATE_FORMATTER.format(localDate);
    }

    /**
     * Parse ISO date-time string to LocalDateTime.
     */
    public static LocalDateTime parseIsoDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(dateTimeStr, ISO_DATE_TIME_FORMATTER);
    }

    /**
     * Parse ISO date string to LocalDate.
     */
    public static LocalDate parseIsoDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        return LocalDate.parse(dateStr, ISO_DATE_FORMATTER);
    }

    /**
     * Calculate days between two dates.
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * Calculate hours between two date-times.
     */
    public static long hoursBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Date-times cannot be null");
        }
        return ChronoUnit.HOURS.between(start, end);
    }

    /**
     * Calculate minutes between two date-times.
     */
    public static long minutesBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Date-times cannot be null");
        }
        return ChronoUnit.MINUTES.between(start, end);
    }

    /**
     * Check if a date is in the past.
     */
    public static boolean isPast(LocalDate date) {
        if (date == null) {
            return false;
        }
        return date.isBefore(nowLocalDate());
    }

    /**
     * Check if a date-time is in the past.
     */
    public static boolean isPast(LocalDateTime dateTime) {
        if (dateTime == null) {
            return false;
        }
        return dateTime.isBefore(nowLocalDateTime());
    }

    /**
     * Check if a date is in the future.
     */
    public static boolean isFuture(LocalDate date) {
        if (date == null) {
            return false;
        }
        return date.isAfter(nowLocalDate());
    }

    /**
     * Check if a date-time is in the future.
     */
    public static boolean isFuture(LocalDateTime dateTime) {
        if (dateTime == null) {
            return false;
        }
        return dateTime.isAfter(nowLocalDateTime());
    }

    /**
     * Get start of day for a date.
     */
    public static LocalDateTime startOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.atStartOfDay();
    }

    /**
     * Get end of day for a date.
     */
    public static LocalDateTime endOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.atTime(LocalTime.MAX);
    }
}
