package com.paymentchain.common.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for PageResponse.
 *
 * @author benas
 */
class PageResponseTest {

    @Test
    void shouldCreatePageResponseWithBuilder() {
        // Arrange
        List<String> content = Arrays.asList("item1", "item2", "item3");

        // Act
        PageResponse<String> response = PageResponse.<String>builder()
                .content(content)
                .pageNumber(0)
                .pageSize(10)
                .totalElements(25)
                .build();

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getContent()).hasSize(3);
        assertThat(response.getPageNumber()).isEqualTo(0);
        assertThat(response.getPageSize()).isEqualTo(10);
        assertThat(response.getTotalElements()).isEqualTo(25);
        assertThat(response.getTotalPages()).isEqualTo(3);
        assertThat(response.isFirst()).isTrue();
        assertThat(response.isLast()).isFalse();
        assertThat(response.isEmpty()).isFalse();
        assertThat(response.getNumberOfElements()).isEqualTo(3);
    }

    @Test
    void shouldCreatePageResponseWithConstructor() {
        // Arrange
        List<String> content = Arrays.asList("item1", "item2");

        // Act
        PageResponse<String> response = new PageResponse<>(content, 1, 10, 25);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getPageNumber()).isEqualTo(1);
        assertThat(response.getTotalPages()).isEqualTo(3);
        assertThat(response.isFirst()).isFalse();
        assertThat(response.isLast()).isFalse();
    }

    @Test
    void shouldDetectFirstPage() {
        // Arrange
        List<String> content = Arrays.asList("item1");

        // Act
        PageResponse<String> response = PageResponse.<String>builder()
                .content(content)
                .pageNumber(0)
                .pageSize(10)
                .totalElements(5)
                .build();

        // Assert
        assertThat(response.isFirst()).isTrue();
        assertThat(response.isLast()).isTrue();
    }

    @Test
    void shouldDetectLastPage() {
        // Arrange
        List<String> content = Arrays.asList("item1", "item2");

        // Act
        PageResponse<String> response = PageResponse.<String>builder()
                .content(content)
                .pageNumber(2)
                .pageSize(10)
                .totalElements(25)
                .build();

        // Assert
        assertThat(response.isFirst()).isFalse();
        assertThat(response.isLast()).isTrue();
    }

    @Test
    void shouldDetectEmptyPage() {
        // Arrange
        List<String> emptyContent = Collections.emptyList();

        // Act
        PageResponse<String> response = PageResponse.<String>builder()
                .content(emptyContent)
                .pageNumber(0)
                .pageSize(10)
                .totalElements(0)
                .build();

        // Assert
        assertThat(response.isEmpty()).isTrue();
        assertThat(response.getNumberOfElements()).isEqualTo(0);
        assertThat(response.getTotalPages()).isEqualTo(0);
    }

    @Test
    void shouldCalculateTotalPagesCorrectly() {
        // Test various page size and total elements combinations
        PageResponse<String> response1 = PageResponse.<String>builder()
                .content(Collections.emptyList())
                .pageNumber(0)
                .pageSize(10)
                .totalElements(25)
                .build();
        assertThat(response1.getTotalPages()).isEqualTo(3);

        PageResponse<String> response2 = PageResponse.<String>builder()
                .content(Collections.emptyList())
                .pageNumber(0)
                .pageSize(10)
                .totalElements(30)
                .build();
        assertThat(response2.getTotalPages()).isEqualTo(3);

        PageResponse<String> response3 = PageResponse.<String>builder()
                .content(Collections.emptyList())
                .pageNumber(0)
                .pageSize(10)
                .totalElements(31)
                .build();
        assertThat(response3.getTotalPages()).isEqualTo(4);
    }

    @Test
    void shouldHandleNullContent() {
        // Act
        PageResponse<String> response = PageResponse.<String>builder()
                .content(null)
                .pageNumber(0)
                .pageSize(10)
                .totalElements(0)
                .build();

        // Assert
        assertThat(response.isEmpty()).isTrue();
        assertThat(response.getNumberOfElements()).isEqualTo(0);
    }
}
