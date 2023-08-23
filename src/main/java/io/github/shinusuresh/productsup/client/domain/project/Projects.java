package io.github.shinusuresh.productsup.client.domain.project;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Project response.
 *
 * @param success - Success status.
 * @param projects - List of {@link Project}.
 */
public record Projects(boolean success,
                       @JsonProperty("Projects") List<Project> projects) {
}

