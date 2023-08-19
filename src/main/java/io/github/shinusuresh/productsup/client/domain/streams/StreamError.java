package io.github.shinusuresh.productsup.client.domain.streams;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Stream error.
 *
 * @param id     - Error id.
 * @param status - Error status.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("errors")
public record StreamError(String id,
                          String status,
                          String code,
                          String title,
                          String detail) {
}
