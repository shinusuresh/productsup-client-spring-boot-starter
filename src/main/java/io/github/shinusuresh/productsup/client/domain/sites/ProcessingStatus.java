package io.github.shinusuresh.productsup.client.domain.sites;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Processing status.
 */
public enum ProcessingStatus {
    /**
     * Done processing status.
     */
    DONE("Done"),
    /**
     * Running processing status
     */
    RUNNING("Running");

    private final String status;

    ProcessingStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the status.
     * @return status
     */
    @JsonValue
    public String getStatus() {
        return status;
    }
}
