package io.github.shinusuresh.productsup.client.client;

import io.github.shinusuresh.productsup.client.data.BaseStreamData;
import io.github.shinusuresh.productsup.client.domain.streams.upload.UploadResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

/**
 * API class for Stream based upload operations.
 */
public interface StreamApiUploadClient {

    /**
     * Uploads NDJSON chunked data to streams.
     *
     * @param streamId - Stream Id
     * @param data     - Data to upload.
     * @return - {@link UploadResponse}
     */
    @PostExchange(value = "/streams/{streamId}/products", contentType = MediaType.APPLICATION_NDJSON_VALUE)
    UploadResponse uploadChunkeddData(@PathVariable(value = "streamId") String streamId,
                                      @RequestBody List<? extends BaseStreamData> data);
}
