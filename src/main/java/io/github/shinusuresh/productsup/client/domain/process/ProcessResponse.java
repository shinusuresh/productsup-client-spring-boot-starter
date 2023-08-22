package io.github.shinusuresh.productsup.client.domain.process;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response class to bind Process api reponse.
 */
public record ProcessResponse(
        boolean success,
        @JsonProperty("process_id")
        String processId

) {
}
