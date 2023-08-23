package io.github.shinusuresh.productsup.client.domain.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Project response.
 *
 * @param id        - Project id.
 * @param name      - Project name.
 * @param createdAt - Created timestamp.
 * @param links     - Links.
 */
public record Project(String id, String name,
                      @JsonProperty("created_at")
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                      Date createdAt,
                      List<HashMap<String, String>> links) {
}
