package com.github.shinusuresh.productsup.client.domain.sites.channels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.SortedMap;

public record Channel(String id,
                      @JsonProperty("site_id")
                      String siteId,
                      @JsonProperty("channel_id")
                      String channelId,
                      String name,
                      @JsonProperty("export_name")
                      String exportName,
                      List<SortedMap<String, String>> links) {
}
