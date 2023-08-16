package io.github.shinusuresh.productsup.client.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Products response.
 *
 * @param success - Status of api call.
 * @param products - Products response as key value pair.
 */
public record Products(boolean success,
                       @JsonProperty("products")
                       List<Map<String, String>> products) {
}
