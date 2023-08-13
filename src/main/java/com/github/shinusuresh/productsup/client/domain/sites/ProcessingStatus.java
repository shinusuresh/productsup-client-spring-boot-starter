package com.github.shinusuresh.productsup.client.domain.sites;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProcessingStatus {
    DONE("Done"),
    RUNNING("Running");

    private final String status;

    ProcessingStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
