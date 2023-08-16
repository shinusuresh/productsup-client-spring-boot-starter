package io.github.shinusuresh.productsup.client.domain.sites.history;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Import history response.
 *
 * @param success - Api status
 * @param histories - List of {@link History}
 */
public record ImportHistory(boolean success,
                            @JsonProperty("Importhistory")
                            List<History> histories) {
}
