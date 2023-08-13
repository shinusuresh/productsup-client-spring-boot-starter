package com.github.shinusuresh.productsup.client.domain.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public record Project(String id, String name,
                      @JsonProperty("created_at")
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                      Date createdAt,
                      List<HashMap<String, String>> links) {
}
