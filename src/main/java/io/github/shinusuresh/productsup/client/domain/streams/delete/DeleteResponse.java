package io.github.shinusuresh.productsup.client.domain.streams.delete;

import io.github.shinusuresh.productsup.client.domain.streams.ResponseData;

/**
 * Delete response.
 *
 * @param data - {@link ResponseData}
 */
public record DeleteResponse(
        ResponseData data
) {
}
