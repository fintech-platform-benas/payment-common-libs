package com.paymentchain.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for JSON serialization and deserialization.
 *
 * @author benas
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    private JsonUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Get the shared ObjectMapper instance.
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * Convert object to JSON string.
     *
     * @param obj The object to serialize
     * @return JSON string representation
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Error converting object to JSON", e);
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    /**
     * Convert object to pretty JSON string.
     *
     * @param obj The object to serialize
     * @return Pretty-printed JSON string representation
     */
    public static String toPrettyJson(Object obj) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Error converting object to pretty JSON", e);
            throw new RuntimeException("Failed to convert object to pretty JSON", e);
        }
    }

    /**
     * Convert JSON string to object.
     *
     * @param json  The JSON string
     * @param clazz The target class
     * @param <T>   The type of the target object
     * @return Deserialized object
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            logger.error("Error converting JSON to object", e);
            throw new RuntimeException("Failed to convert JSON to object", e);
        }
    }

    /**
     * Convert JSON string to object using TypeReference (for generic types).
     *
     * @param json          The JSON string
     * @param typeReference The type reference
     * @param <T>           The type of the target object
     * @return Deserialized object
     */
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            logger.error("Error converting JSON to object", e);
            throw new RuntimeException("Failed to convert JSON to object", e);
        }
    }

    /**
     * Convert JSON string to object, returning null if parsing fails.
     *
     * @param json  The JSON string
     * @param clazz The target class
     * @param <T>   The type of the target object
     * @return Deserialized object or null if parsing fails
     */
    public static <T> T fromJsonSafe(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            logger.warn("Failed to parse JSON, returning null", e);
            return null;
        }
    }

    /**
     * Check if a string is valid JSON.
     *
     * @param json The string to validate
     * @return true if valid JSON, false otherwise
     */
    public static boolean isValidJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            return false;
        }
        try {
            objectMapper.readTree(json);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    /**
     * Convert object to JSON bytes.
     *
     * @param obj The object to serialize
     * @return JSON byte array
     */
    public static byte[] toJsonBytes(Object obj) {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            logger.error("Error converting object to JSON bytes", e);
            throw new RuntimeException("Failed to convert object to JSON bytes", e);
        }
    }

    /**
     * Convert JSON bytes to object.
     *
     * @param bytes The JSON bytes
     * @param clazz The target class
     * @param <T>   The type of the target object
     * @return Deserialized object
     */
    public static <T> T fromJsonBytes(byte[] bytes, Class<T> clazz) {
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (Exception e) {
            logger.error("Error converting JSON bytes to object", e);
            throw new RuntimeException("Failed to convert JSON bytes to object", e);
        }
    }
}
