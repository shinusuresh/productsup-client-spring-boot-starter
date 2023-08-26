package io.github.shinusuresh.productsup.client.domain.streams.upload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Example occurrence of the error.
 *
 * @param lineNumber - Line number.
 * @param value      - error value. example <code>{"id":"123""name":"Product"}</code>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BatchStageErrorExample(
        int lineNumber,
        String value
) {
}
