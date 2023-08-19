package io.github.shinusuresh.productsup.client.domain.streams.create;

import io.github.shinusuresh.productsup.client.domain.streams.Stream;

/**
 * Create stream response.
 * @param data - Response data.
 */
public record CreateStreamResponse(Stream data) {
}
