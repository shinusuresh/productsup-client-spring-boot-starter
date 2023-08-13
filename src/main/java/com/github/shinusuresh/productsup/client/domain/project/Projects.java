package com.github.shinusuresh.productsup.client.domain.project;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Projects(String success,
                       @JsonProperty("Projects") List<Project> projects) {
}

