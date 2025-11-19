package com.paymentchain.common.filter;

import com.paymentchain.common.util.CorrelationIdUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Enumeration;

/**
 * Filter to log HTTP requests and responses.
 * Executes after CorrelationIdFilter to ensure correlation ID is available.
 *
 * @author benas
 */
@Component
@Order(2)
public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    private static final int MAX_PAYLOAD_LENGTH = 1000;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Wrap request and response to cache content
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(httpRequest);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(httpResponse);

        long startTime = System.currentTimeMillis();

        try {
            // Log incoming request
            logRequest(wrappedRequest);

            // Continue the filter chain
            chain.doFilter(wrappedRequest, wrappedResponse);

        } finally {
            long duration = System.currentTimeMillis() - startTime;

            // Log outgoing response
            logResponse(wrappedRequest, wrappedResponse, duration);

            // Copy cached response content to actual response
            wrappedResponse.copyBodyToResponse();
        }
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        String correlationId = CorrelationIdUtils.getCorrelationId();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();

        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Incoming Request: ")
                .append(method)
                .append(" ")
                .append(uri);

        if (queryString != null) {
            logMessage.append("?").append(queryString);
        }

        logMessage.append(" [correlationId=").append(correlationId).append("]");

        logger.info(logMessage.toString());

        // Log headers if debug is enabled
        if (logger.isDebugEnabled()) {
            logHeaders(request);
        }
    }

    private void logResponse(ContentCachingRequestWrapper request,
                            ContentCachingResponseWrapper response,
                            long duration) {
        String correlationId = CorrelationIdUtils.getCorrelationId();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        int status = response.getStatus();

        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Outgoing Response: ")
                .append(method)
                .append(" ")
                .append(uri)
                .append(" - Status: ")
                .append(status)
                .append(" - Duration: ")
                .append(duration)
                .append("ms")
                .append(" [correlationId=").append(correlationId).append("]");

        if (status >= 500) {
            logger.error(logMessage.toString());
        } else if (status >= 400) {
            logger.warn(logMessage.toString());
        } else {
            logger.info(logMessage.toString());
        }

        // Log response body if debug is enabled
        if (logger.isDebugEnabled()) {
            logResponseBody(response);
        }
    }

    private void logHeaders(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder("Request Headers: ");
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.append(headerName).append("=").append(headerValue).append("; ");
        }

        logger.debug(headers.toString());
    }

    private void logResponseBody(ContentCachingResponseWrapper response) {
        byte[] content = response.getContentAsByteArray();
        if (content.length > 0) {
            String body = new String(content, 0, Math.min(content.length, MAX_PAYLOAD_LENGTH));
            logger.debug("Response Body: {}", body);
            if (content.length > MAX_PAYLOAD_LENGTH) {
                logger.debug("Response body truncated (max {} chars)", MAX_PAYLOAD_LENGTH);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("LoggingFilter initialized");
    }

    @Override
    public void destroy() {
        logger.info("LoggingFilter destroyed");
    }
}
