package io.github.shinusuresh.productsup.client.domain.streams.upload;

/**
 * Upload products response.
 *
 * @param data - {@link UploadResponseData}
 */
public record UploadResponse(
        UploadResponseData data
) {
}
