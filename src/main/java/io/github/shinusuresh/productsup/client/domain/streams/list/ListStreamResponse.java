package io.github.shinusuresh.productsup.client.domain.streams.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Stream list response.
 *
 * @param success       - Success status.
 * @param streamSources - List of {@link StreamSource}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ListStreamResponse(
        boolean success,

        @JsonProperty("Sources")
        List<StreamSource> streamSources
) {
}
