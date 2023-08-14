package com.github.shinusuresh.productsup.client.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record Products(boolean success,
                       @JsonProperty("products")
                       List<Map<String, String>> products) {
}
