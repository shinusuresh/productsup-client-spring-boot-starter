package com.github.shinusuresh.productsup.client.domain.sites;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Sites(boolean success,
                    @JsonProperty("Sites") List<Site> sites) {
}
