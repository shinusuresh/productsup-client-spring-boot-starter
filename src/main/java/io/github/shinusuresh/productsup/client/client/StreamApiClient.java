package io.github.shinusuresh.productsup.client.client;

import io.github.shinusuresh.productsup.client.domain.streams.Data;
import io.github.shinusuresh.productsup.client.domain.streams.create.CreateStream;
import io.github.shinusuresh.productsup.client.domain.streams.create.CreateStreamResponse;
import io.github.shinusuresh.productsup.client.domain.streams.delete.DeleteResponse;
import io.github.shinusuresh.productsup.client.domain.streams.status.BatchStatusResponse;
import io.github.shinusuresh.productsup.client.domain.streams.update.UpdateStream;
import io.github.shinusuresh.productsup.client.domain.streams.update.UpdateStreamResponse;
import io.github.shinusuresh.productsup.client.domain.streams.upload.UploadResponse;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PatchExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * Stream based client for ProductsUp operations <a href="https://api-docs.productsup.io/#stream-api">Stream API's</a>
 */
public interface StreamApiClient {

    /**
     * List all streams.
     * <a href="https://api-docs.productsup.io/#stream-api-stream-management-list-stream">List Stream</a>
     *
     * @return - {@link Data}
     */
    @GetExchange("/streams")
    Data listStreams();

    /**
     * Create stream.
     * <a href="https://api-docs.productsup.io/#stream-api-stream-management-stream-creation">Create Stream
     * </a>
     *
     * @param data - {@link CreateStream}
     * @return - {@link CreateStreamResponse}
     */
    @PostExchange(value = "/streams", contentType = "application/vnd.api+json")
    CreateStreamResponse createStream(@RequestBody CreateStream data);


    /**
     * Update a stream. Only stream name can be updated.
     * <a href="https://api-docs.productsup.io/#stream-api-stream-management-update-stream">Update Stream</a>
     *
     * @param updateStream - {@link UpdateStream}
     * @return - {@link UpdateStreamResponse}
     */
    @PatchExchange(value = "/streams/{streamId}", contentType = "application/vnd.api+json")
    UpdateStreamResponse updateStream(@PathVariable("streamId") String streamId, @RequestBody UpdateStream updateStream);


    /**
     * Remove a stream.
     * <p>
     * <a href="https://api-docs.productsup.io/#stream-api-stream-management-remove-stream">Remove Stream</a>
     * </p>
     *
     * @param streamId - Stream id to remove.
     */
    @DeleteExchange(value = "/streams/{streamId}", contentType = "application/vnd.api+json")
    void removeStream(@PathVariable(value = "streamId") String streamId);

    /**
     * Referenced Stream upload of product data.
     * The content type of this request <code>application/json</code>
     * <p>
     * <a href="https://api-docs.productsup.io/#stream-api-uploading-data-request-body">Uploading Data</a>
     * </p>
     *
     * @param streamId - Stream Id.
     * @return - {@link UploadResponse}
     */
    @PostExchange(value = "/streams/{streamId}/products", contentType = MediaType.APPLICATION_JSON_VALUE)
    UploadResponse uploadReferencedData(@PathVariable(value = "streamId") String streamId,
                                        @RequestBody Map<String, String>... data);


    /**
     * The DELETE method on this endpoint allows you to delete a single product.
     * <p>
     * <a href="https://api-docs.productsup.io/#stream-api-delete-products-delete-a-product-via-the-url">Delete a product via the URL</a>
     * </p>
     *
     * @param streamId  - Stream id.
     * @param productId - Product id.
     * @return - {@link DeleteResponse}
     */
    @DeleteExchange(value = "/streams/{streamId}/products/{productId}", contentType = "application/vnd.api+json")
    DeleteResponse deleteProduct(@PathVariable("streamId") String streamId, @PathVariable("productId") String productId);

    /**
     * Delete all products.
     * <p>
     * <a href="https://api-docs.productsup.io/#stream-api-delete-products-delete-a-product-via-the-url">Delete a product via the URL</a>
     * </p>
     *
     * @param streamId - Stream id
     * @return - {@link DeleteResponse}
     */
    @DeleteExchange(value = "/streams/{streamId}/products?all=true", contentType = "application/vnd.api+json")
    DeleteResponse deleteAllProducts(@PathVariable("streamId") String streamId);

    /**
     * Get batch status
     * <p>
     * <a href="https://api-docs.productsup.io/#stream-api-batches">Batch status</a>
     * </p>
     *
     * @param streamId - Stream id.
     * @param batchId  - Batch id.
     * @return - {@link BatchStatusResponse}
     */
    @GetExchange(value = "/streams/{streamId}/batches/{batchId}")
    BatchStatusResponse batchStatus(@PathVariable("streamId") String streamId, @PathVariable("batchId") String batchId);
}
