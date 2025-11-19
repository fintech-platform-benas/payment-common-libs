package com.paymentchain.common.filter;

import com.paymentchain.common.util.CorrelationIdUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Filter to manage correlation IDs for request tracking.
 * Executes first in the filter chain to ensure correlation ID is available for all subsequent filters.
 *
 * @author benas
 */
@Component
@Order(1)
public class CorrelationIdFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CorrelationIdFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            // Get correlation ID from header or generate new one
            String correlationId = httpRequest.getHeader(CorrelationIdUtils.getCorrelationIdHeader());
            if (correlationId == null || correlationId.trim().isEmpty()) {
                correlationId = CorrelationIdUtils.generateCorrelationId();
                logger.debug("Generated new correlation ID: {}", correlationId);
            } else {
                logger.debug("Using existing correlation ID: {}", correlationId);
            }

            // Set in MDC for logging
            CorrelationIdUtils.setCorrelationId(correlationId);

            // Add to response header
            httpResponse.setHeader(CorrelationIdUtils.getCorrelationIdHeader(), correlationId);

            // Continue the filter chain
            chain.doFilter(request, response);

        } finally {
            // Always clear MDC after request processing
            CorrelationIdUtils.clearCorrelationId();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("CorrelationIdFilter initialized");
    }

    @Override
    public void destroy() {
        logger.info("CorrelationIdFilter destroyed");
    }
}
