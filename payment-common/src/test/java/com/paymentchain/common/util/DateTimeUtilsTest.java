package com.paymentchain.common.util;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for DateTimeUtils.
 *
 * @author benas
 */
class DateTimeUtilsTest {

    @Test
    void shouldGetCurrentInstant() {
        // Act
        Instant now = DateTimeUtils.now();

        // Assert
        assertThat(now).isNotNull();
        assertThat(now).isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void shouldGetCurrentLocalDateTime() {
        // Act
        LocalDateTime now = DateTimeUtils.nowLocalDateTime();

        // Assert
        assertThat(now).isNotNull();
    }

    @Test
    void shouldGetCurrentLocalDate() {
        // Act
        LocalDate now = DateTimeUtils.nowLocalDate();

        // Assert
        assertThat(now).isNotNull();
    }

    @Test
    void shouldConvertInstantToLocalDateTime() {
        // Arrange
        Instant instant = Instant.parse("2024-01-15T10:30:00Z");

        // Act
        LocalDateTime localDateTime = DateTimeUtils.toLocalDateTime(instant);

        // Assert
        assertThat(localDateTime).isNotNull();
        assertThat(localDateTime.getYear()).isEqualTo(2024);
        assertThat(localDateTime.getMonthValue()).isEqualTo(1);
        assertThat(localDateTime.getDayOfMonth()).isEqualTo(15);
    }

    @Test
    void shouldConvertLocalDateTimeToInstant() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 15, 10, 30);

        // Act
        Instant instant = DateTimeUtils.toInstant(localDateTime);

        // Assert
        assertThat(instant).isNotNull();
    }

    @Test
    void shouldFormatInstantToIsoDateTime() {
        // Arrange
        Instant instant = Instant.parse("2024-01-15T10:30:00Z");

        // Act
        String formatted = DateTimeUtils.formatIsoDateTime(instant);

        // Assert
        assertThat(formatted).isEqualTo("2024-01-15T10:30:00");
    }

    @Test
    void shouldFormatLocalDateTimeToIsoDateTime() {
        // Arrange
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 15, 10, 30);

        // Act
        String formatted = DateTimeUtils.formatIsoDateTime(dateTime);

        // Assert
        assertThat(formatted).isEqualTo("2024-01-15T10:30:00");
    }

    @Test
    void shouldFormatLocalDateToIsoDate() {
        // Arrange
        LocalDate date = LocalDate.of(2024, 1, 15);

        // Act
        String formatted = DateTimeUtils.formatIsoDate(date);

        // Assert
        assertThat(formatted).isEqualTo("2024-01-15");
    }

    @Test
    void shouldFormatToDisplayDateTime() {
        // Arrange
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 15, 10, 30, 45);

        // Act
        String formatted = DateTimeUtils.formatDisplayDateTime(dateTime);

        // Assert
        assertThat(formatted).isEqualTo("15/01/2024 10:30:45");
    }

    @Test
    void shouldFormatToDisplayDate() {
        // Arrange
        LocalDate date = LocalDate.of(2024, 1, 15);

        // Act
        String formatted = DateTimeUtils.formatDisplayDate(date);

        // Assert
        assertThat(formatted).isEqualTo("15/01/2024");
    }

    @Test
    void shouldParseIsoDateTime() {
        // Arrange
        String dateTimeStr = "2024-01-15T10:30:00";

        // Act
        LocalDateTime parsed = DateTimeUtils.parseIsoDateTime(dateTimeStr);

        // Assert
        assertThat(parsed).isNotNull();
        assertThat(parsed.getYear()).isEqualTo(2024);
        assertThat(parsed.getMonthValue()).isEqualTo(1);
        assertThat(parsed.getDayOfMonth()).isEqualTo(15);
        assertThat(parsed.getHour()).isEqualTo(10);
        assertThat(parsed.getMinute()).isEqualTo(30);
    }

    @Test
    void shouldParseIsoDate() {
        // Arrange
        String dateStr = "2024-01-15";

        // Act
        LocalDate parsed = DateTimeUtils.parseIsoDate(dateStr);

        // Assert
        assertThat(parsed).isNotNull();
        assertThat(parsed.getYear()).isEqualTo(2024);
        assertThat(parsed.getMonthValue()).isEqualTo(1);
        assertThat(parsed.getDayOfMonth()).isEqualTo(15);
    }

    @Test
    void shouldCalculateDaysBetween() {
        // Arrange
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 1, 15);

        // Act
        long days = DateTimeUtils.daysBetween(start, end);

        // Assert
        assertThat(days).isEqualTo(14);
    }

    @Test
    void shouldCalculateHoursBetween() {
        // Arrange
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 1, 15, 0);

        // Act
        long hours = DateTimeUtils.hoursBetween(start, end);

        // Assert
        assertThat(hours).isEqualTo(5);
    }

    @Test
    void shouldCalculateMinutesBetween() {
        // Arrange
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 1, 10, 30);

        // Act
        long minutes = DateTimeUtils.minutesBetween(start, end);

        // Assert
        assertThat(minutes).isEqualTo(30);
    }

    @Test
    void shouldDetectPastDate() {
        // Arrange
        LocalDate pastDate = LocalDate.now().minusDays(1);
        LocalDate futureDate = LocalDate.now().plusDays(1);

        // Act & Assert
        assertThat(DateTimeUtils.isPast(pastDate)).isTrue();
        assertThat(DateTimeUtils.isPast(futureDate)).isFalse();
    }

    @Test
    void shouldDetectFutureDate() {
        // Arrange
        LocalDate futureDate = LocalDate.now().plusDays(1);
        LocalDate pastDate = LocalDate.now().minusDays(1);

        // Act & Assert
        assertThat(DateTimeUtils.isFuture(futureDate)).isTrue();
        assertThat(DateTimeUtils.isFuture(pastDate)).isFalse();
    }

    @Test
    void shouldGetStartOfDay() {
        // Arrange
        LocalDate date = LocalDate.of(2024, 1, 15);

        // Act
        LocalDateTime startOfDay = DateTimeUtils.startOfDay(date);

        // Assert
        assertThat(startOfDay).isNotNull();
        assertThat(startOfDay.getHour()).isEqualTo(0);
        assertThat(startOfDay.getMinute()).isEqualTo(0);
        assertThat(startOfDay.getSecond()).isEqualTo(0);
    }

    @Test
    void shouldGetEndOfDay() {
        // Arrange
        LocalDate date = LocalDate.of(2024, 1, 15);

        // Act
        LocalDateTime endOfDay = DateTimeUtils.endOfDay(date);

        // Assert
        assertThat(endOfDay).isNotNull();
        assertThat(endOfDay.getHour()).isEqualTo(23);
        assertThat(endOfDay.getMinute()).isEqualTo(59);
        assertThat(endOfDay.getSecond()).isEqualTo(59);
    }

    @Test
    void shouldHandleNullValues() {
        // Act & Assert
        assertThat(DateTimeUtils.toLocalDateTime(null)).isNull();
        assertThat(DateTimeUtils.toInstant(null)).isNull();
        assertThat(DateTimeUtils.formatIsoDateTime((Instant) null)).isNull();
        assertThat(DateTimeUtils.formatIsoDateTime((LocalDateTime) null)).isNull();
        assertThat(DateTimeUtils.formatIsoDate(null)).isNull();
        assertThat(DateTimeUtils.parseIsoDateTime(null)).isNull();
        assertThat(DateTimeUtils.parseIsoDate(null)).isNull();
        assertThat(DateTimeUtils.isPast((LocalDate) null)).isFalse();
        assertThat(DateTimeUtils.isFuture((LocalDate) null)).isFalse();
    }
}
