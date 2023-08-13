package com.github.shinusuresh.productsup.client.domain.sites.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record Data(
        @JsonProperty("Filetime")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Date fileTime,
        String dataSource,

        @JsonProperty("Setup")
        String setUp) {
}
