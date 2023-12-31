package io.github.shinusuresh.productsup.client.domain.streams.upload;

import io.github.shinusuresh.productsup.client.domain.streams.ResponseData;

/**
 * Upload products response.
 *
 * @param data - {@link ResponseData}
 */
public record UploadResponse(
        ResponseData data
) {
}
