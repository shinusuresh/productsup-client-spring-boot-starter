package io.github.shinusuresh.productsup.client.domain.sites;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Sites response.
 *
 * @param success
 * @param sites
 */
public record Sites(boolean success,
                    @JsonProperty("Sites") List<Site> sites) {
}
