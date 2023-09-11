package io.github.shinusuresh.productsup.client.domain.streams.attach;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.shinusuresh.productsup.client.domain.sites.SiteStatus;
import io.github.shinusuresh.productsup.client.domain.streams.list.ImportType;

/**
 * Attach stream to site.
 *
 * @param type - {@link ImportType}
 * @param description - Description
 * @param streamId - Stream id
 * @param status - {@link SiteStatus}
 */
public record AttachStream(
        @JsonProperty("import_type")
        ImportType type,
        String description,

        @JsonProperty("stream_id")
        int streamId,
        SiteStatus status

) {
}
