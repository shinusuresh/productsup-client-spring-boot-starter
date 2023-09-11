package io.github.shinusuresh.productsup.client.domain.streams.attach;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.shinusuresh.productsup.client.domain.streams.list.StreamSource;

import java.util.List;

/**
 * Attaching stream to site response.
 *
 * @param success - boolean
 * @param sources - List of {@link StreamSource}.
 */
public record AttachStreamResponse(
        boolean success,
        @JsonProperty("Sources")
        List<StreamSource> sources
) {
}
