package io.github.shinusuresh.productsup.client.domain.sites.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Site errors response.
 *
 * @param success
 * @param errors
 */
public record SiteErrors(boolean success,
                         @JsonProperty("Errors") List<Error> errors) {
}
