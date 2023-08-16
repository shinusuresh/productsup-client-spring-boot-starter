package io.github.shinusuresh.productsup.client.domain.streams;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.SortedMap;

/**
 * Stream response.
 *
 * @param id - Stream id.
 * @param type - Stream type - chunked or referenced
 * @param attributes - Stream attributes
 * @param links - Links
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Stream(String id,
                     String type,
                     StreamAttributes attributes,
                     Relationships relationships,
                     List<SortedMap<String, String>> links) {
}
