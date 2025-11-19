package com.paymentchain.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Generic paginated response wrapper with Builder pattern.
 *
 * @param <T> The type of content in the page
 * @author benas
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    private boolean empty;
    private int numberOfElements;

    public PageResponse() {
    }

    public PageResponse(List<T> content, int pageNumber, int pageSize, long totalElements) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = pageSize > 0 ? (int) Math.ceil((double) totalElements / pageSize) : 0;
        this.first = pageNumber == 0;
        this.last = pageNumber >= totalPages - 1;
        this.empty = content == null || content.isEmpty();
        this.numberOfElements = content != null ? content.size() : 0;
    }

    private PageResponse(Builder<T> builder) {
        this.content = builder.content;
        this.pageNumber = builder.pageNumber;
        this.pageSize = builder.pageSize;
        this.totalElements = builder.totalElements;
        this.totalPages = builder.pageSize > 0 ? (int) Math.ceil((double) builder.totalElements / builder.pageSize) : 0;
        this.first = builder.pageNumber == 0;
        this.last = builder.pageNumber >= this.totalPages - 1;
        this.empty = builder.content == null || builder.content.isEmpty();
        this.numberOfElements = builder.content != null ? builder.content.size() : 0;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private List<T> content;
        private int pageNumber;
        private int pageSize;
        private long totalElements;

        public Builder<T> content(List<T> content) {
            this.content = content;
            return this;
        }

        public Builder<T> pageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder<T> totalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public PageResponse<T> build() {
            return new PageResponse<>(this);
        }
    }

    // Getters and setters
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
}
