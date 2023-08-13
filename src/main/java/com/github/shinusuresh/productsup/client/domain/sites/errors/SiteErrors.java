package com.github.shinusuresh.productsup.client.domain.sites.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SiteErrors(boolean success,
                         @JsonProperty("Errors") List<Error> errors) {
}
