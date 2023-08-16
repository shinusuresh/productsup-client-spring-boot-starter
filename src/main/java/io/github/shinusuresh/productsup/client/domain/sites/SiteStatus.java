package io.github.shinusuresh.productsup.client.domain.sites;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Site statuses
 */
public enum SiteStatus {

    /**
     * Active site status
     */
    ACTIVE("active"),
    /**
     * Paused upload site status
     */
    PAUSED_UPLOAD("paused_upload"),
    /**
     * Disabled site status
     */
    DISABLED("disabled");

    private final String status;

    SiteStatus(String status) {
        this.status = status;
    }

    /**
     * Returns status of site.
     *
     * @return status
     */
    @JsonValue
    public String getStatus() {
        return status;
    }
}
