package io.github.shinusuresh.productsup.client.domain.streams;

import io.github.shinusuresh.productsup.client.domain.streams.upload.UploadAttributes;

/**
 * Upload response data.
 *
 * @param type - Type.
 * @param id - Batch id.
 * @param attributes - Upload attributes - {@link UploadAttributes}
 */
public record ResponseData(
        String type,
        String id,
        UploadAttributes attributes
) {
}
