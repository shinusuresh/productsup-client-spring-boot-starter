package io.github.shinusuresh.productsup.client.domain.streams.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Stream source used in List Stream.
 *
 * @param id          - Id.
 * @param siteId      - Site id.
 * @param description - Stream description.
 * @param source      - Source.
 * @param importType  - Import type of Feed {@link ImportType}.
 * @param importId    - Import Id.
 * @param status      - Status.
 * @param settings    - Settings that contains stream id.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record StreamSource(
        String id,
        @JsonProperty("site_id")
        String siteId,
        String description,
        String source,
        @JsonProperty("import_type")
        ImportType importType,
        @JsonProperty("import_id")
        String importId,
        String status,
        List<String> settings

) {
}
