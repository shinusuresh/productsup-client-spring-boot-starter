package io.github.shinusuresh.productsup.client.domain.sites.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Site errors response.
 *
 * @param success - Success status
 * @param errors - List of {@link Error}
 */
public record SiteErrors(boolean success,
                         @JsonProperty("Errors") List<Error> errors) {
}
