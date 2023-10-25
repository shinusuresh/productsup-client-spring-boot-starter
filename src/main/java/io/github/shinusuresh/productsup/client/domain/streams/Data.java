package io.github.shinusuresh.productsup.client.domain.streams;

import java.util.List;
import java.util.SortedMap;

/**
 * Data object that maps stream response.
 *
 * @param data  - Stream data.
 * @param links - Links for pagination.
 */
public record Data(
    List<Stream> data,
    SortedMap<String, String> links
) {
}
