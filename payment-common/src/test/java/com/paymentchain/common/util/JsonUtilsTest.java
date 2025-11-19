package com.paymentchain.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for JsonUtils.
 *
 * @author benas
 */
class JsonUtilsTest {

    @Test
    void shouldSerializeObjectToJson() {
        // Arrange
        TestObject obj = new TestObject("test", 123);

        // Act
        String json = JsonUtils.toJson(obj);

        // Assert
        assertThat(json).isNotNull();
        assertThat(json).contains("test");
        assertThat(json).contains("123");
    }

    @Test
    void shouldSerializeObjectToPrettyJson() {
        // Arrange
        TestObject obj = new TestObject("test", 123);

        // Act
        String json = JsonUtils.toPrettyJson(obj);

        // Assert
        assertThat(json).isNotNull();
        assertThat(json).contains("test");
        assertThat(json).contains("123");
        assertThat(json).contains("\n"); // Pretty printed
    }

    @Test
    void shouldDeserializeJsonToObject() {
        // Arrange
        String json = "{\"name\":\"test\",\"value\":123}";

        // Act
        TestObject obj = JsonUtils.fromJson(json, TestObject.class);

        // Assert
        assertThat(obj).isNotNull();
        assertThat(obj.getName()).isEqualTo("test");
        assertThat(obj.getValue()).isEqualTo(123);
    }

    @Test
    void shouldDeserializeJsonToGenericType() {
        // Arrange
        String json = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        TypeReference<Map<String, String>> typeRef = new TypeReference<>() {};

        // Act
        Map<String, String> map = JsonUtils.fromJson(json, typeRef);

        // Assert
        assertThat(map).isNotNull();
        assertThat(map).containsEntry("key1", "value1");
        assertThat(map).containsEntry("key2", "value2");
    }

    @Test
    void shouldReturnNullForInvalidJsonInSafeMode() {
        // Arrange
        String invalidJson = "{invalid json}";

        // Act
        TestObject obj = JsonUtils.fromJsonSafe(invalidJson, TestObject.class);

        // Assert
        assertThat(obj).isNull();
    }

    @Test
    void shouldThrowExceptionForInvalidJson() {
        // Arrange
        String invalidJson = "{invalid json}";

        // Act & Assert
        assertThatThrownBy(() -> JsonUtils.fromJson(invalidJson, TestObject.class))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Failed to convert JSON to object");
    }

    @Test
    void shouldValidateValidJson() {
        // Arrange
        String validJson = "{\"name\":\"test\"}";

        // Act
        boolean isValid = JsonUtils.isValidJson(validJson);

        // Assert
        assertThat(isValid).isTrue();
    }

    @Test
    void shouldDetectInvalidJson() {
        // Act & Assert
        assertThat(JsonUtils.isValidJson("{invalid}")).isFalse();
        assertThat(JsonUtils.isValidJson(null)).isFalse();
        assertThat(JsonUtils.isValidJson("")).isFalse();
        assertThat(JsonUtils.isValidJson("   ")).isFalse();
    }

    @Test
    void shouldConvertObjectToJsonBytes() {
        // Arrange
        TestObject obj = new TestObject("test", 123);

        // Act
        byte[] bytes = JsonUtils.toJsonBytes(obj);

        // Assert
        assertThat(bytes).isNotNull();
        assertThat(bytes.length).isGreaterThan(0);
    }

    @Test
    void shouldConvertJsonBytesToObject() {
        // Arrange
        TestObject original = new TestObject("test", 123);
        byte[] bytes = JsonUtils.toJsonBytes(original);

        // Act
        TestObject deserialized = JsonUtils.fromJsonBytes(bytes, TestObject.class);

        // Assert
        assertThat(deserialized).isNotNull();
        assertThat(deserialized.getName()).isEqualTo("test");
        assertThat(deserialized.getValue()).isEqualTo(123);
    }

    @Test
    void shouldGetObjectMapper() {
        // Act & Assert
        assertThat(JsonUtils.getObjectMapper()).isNotNull();
    }

    // Test helper class
    static class TestObject {
        private String name;
        private int value;

        public TestObject() {
        }

        public TestObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
