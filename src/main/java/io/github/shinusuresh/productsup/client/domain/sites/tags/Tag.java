package io.github.shinusuresh.productsup.client.domain.sites.tags;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a Tag
 *
 * @param id       - Tag id.
 * @param siteId   - Site Id.
 * @param key      - Key name.
 * @param value    - Key value.
 * @param readOnly - Readonly.
 * @param links    - List of Links
 */
public record Tag(
    String id,
    @JsonProperty("site_id")
    String siteId,
    String key,
    String value,

    @JsonProperty("readonly")
    String readOnly,
    List<HashMap<String, String>> links
) {
}
