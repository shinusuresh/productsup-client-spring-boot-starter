package com.github.shinusuresh.productsup.client.domain.sites;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SiteStatus {

    ACTIVE("active"),
    PAUSED_UPLOAD("paused_upload"),
    DISABLED("disabled");

    private final String status;

    SiteStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
