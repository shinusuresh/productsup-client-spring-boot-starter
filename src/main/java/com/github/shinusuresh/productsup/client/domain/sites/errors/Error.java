package com.github.shinusuresh.productsup.client.domain.sites.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

public record Error(String id,
                    String pid,
                    String error,
                    Data data,
                    @JsonProperty("site_id")
                    String siteId,
                    @JsonProperty("datetime")
                    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                    Date dateTime,
                    List<SortedMap<String, String>> links) {
}
