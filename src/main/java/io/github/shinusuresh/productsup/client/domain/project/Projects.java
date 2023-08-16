package io.github.shinusuresh.productsup.client.domain.project;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Project response.
 *
 * @param success
 * @param projects
 */
public record Projects(boolean success,
                       @JsonProperty("Projects") List<Project> projects) {
}

