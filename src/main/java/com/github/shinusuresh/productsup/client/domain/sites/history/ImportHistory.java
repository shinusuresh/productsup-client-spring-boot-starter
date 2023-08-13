package com.github.shinusuresh.productsup.client.domain.sites.history;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ImportHistory(boolean success,

                            @JsonProperty("Importhistory")
                            List<History> histories) {
}
