package io.github.shinusuresh.productsup.client.domain.streams.upload;

/**
 * Upload response data.
 *
 * @param type - Type.
 * @param id - Batch id.
 * @param attributes - Upload attributes - {@link UploadAttributes}
 */
public record UploadResponseData(
        String type,
        String id,
        UploadAttributes attributes
) {
}
