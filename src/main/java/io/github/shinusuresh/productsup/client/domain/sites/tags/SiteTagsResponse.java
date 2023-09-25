package io.github.shinusuresh.productsup.client.domain.sites.tags;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Get all tags for a site response.
 *
 * @param success - State of request
 * @param tags    - List of {@link Tag}
 */
public record SiteTagsResponse(
    boolean success,

    @JsonProperty("Tags")
    List<Tag> tags
) {
}
