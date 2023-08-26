package io.github.shinusuresh.productsup.client.domain.streams.upload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Distinct error samples grouped by the error message.
 *
 * @param message     - The error message
 * @param occurrences - Number of the error occurrences.
 * @param errors      - List of {@link BatchStageErrorExample}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BatchStageError(
        String message,
        int occurrences,
        @JsonProperty("example")
        BatchStageErrorExample errors

) {
}
