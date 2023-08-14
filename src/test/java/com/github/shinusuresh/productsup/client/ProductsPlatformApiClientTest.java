package com.github.shinusuresh.productsup.client;

import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.mockserver.model.Parameter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class ProductsPlatformApiClientTest extends BasePlatformApiClient {

    public ProductsPlatformApiClientTest(MockServerClient mockServerClient) {
        super(mockServerClient);
    }

    @Test
    void testProducts() {
        try (var mockClient = mockClient()) {
            mockClient.when(request()
                            .withMethod("GET")
                            .withPath("/site/{id}/stage/{stageName}")
                            .withPathParameters(
                                    new Parameter("id", "[0-9]+"),
                                    new Parameter("stageName", "[a-z]+")
                            )
                    )
                    .respond(response()
                            .withContentType(MediaType.APPLICATION_JSON)
                            .withBody(
                                    """

                                                {
                                                 "success": true,
                                                 "products": [{
                                                     "id": "123",
                                                     "gtin": "42"
                                                 }]
                                             }
                                            """
                            ));
            var productsList = platformApiClient().getProducts(123, "import", null, null);
            var products = productsList.products()
                    .stream()
                    .findFirst()
                    .get();
            assertThat(products)
                    .containsExactly(
                            entry("id", "123"),
                            entry("gtin", "42")
                    );
        }
    }
}
