package io.github.shinusuresh.productsup.client.domain.streams;

/**
 * Stream error.
 *
 * @param id     - Error id.
 * @param status - Error status.
 */
public record StreamError(String id,
                          String status) {
}
