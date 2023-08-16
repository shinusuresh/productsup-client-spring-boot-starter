package io.github.shinusuresh.productsup.client.domain.sites.channels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Channels(boolean success,
                       @JsonProperty("Channels")
                       List<Channel> channels) {
}
