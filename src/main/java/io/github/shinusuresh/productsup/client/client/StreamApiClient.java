package io.github.shinusuresh.productsup.client.client;

import io.github.shinusuresh.productsup.client.domain.streams.Data;
import io.github.shinusuresh.productsup.client.domain.streams.create.CreateStream;
import io.github.shinusuresh.productsup.client.domain.streams.create.CreateStreamResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * Stream based client for ProductsUp operations <a href="https://api-docs.productsup.io/#stream-api">Stream API's</a>
 */
public interface StreamApiClient {

    @GetExchange("/streams")
    Data listStreams();

    @PostExchange(value = "/streams", contentType = "application/vnd.api+json")
    CreateStreamResponse createStream(@RequestBody CreateStream data);
}
