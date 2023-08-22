package io.github.shinusuresh.productsup.client.domain.streams;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Stream type
 */
public enum StreamType {

    /**
     * Chunked stream type.
     */
    CHUNKED("chunked"),

    /**
     * Referenced stream type.
     */
    REFERENCED("referenced");

    private final String type;
    StreamType(String type) {
        this.type = type;
    }

    /**
     * Returns type of stream.
     *
     * @return Stream type.
     */
    @JsonValue
    public String getType() {
        return type;
    }
}
