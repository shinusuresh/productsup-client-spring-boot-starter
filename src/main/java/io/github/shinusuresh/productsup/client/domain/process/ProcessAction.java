package io.github.shinusuresh.productsup.client.domain.process;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Allowed process statuses.
 */
public enum ProcessAction {

    /**
     * Import data from source.
     */
    IMPORT("import"),

    /**
     * Export data.
     */
    EXPORT("export"),
    /**
     * Channel process.
     */
    CHANNEL("channel"),
    /**
     * Export all.
     */
    EXPORT_ALL("export-all"),
    /**
     * Perform import and export.
     */
    ALL("all");

    private final String status;

    ProcessAction(String status) {
        this.status = status;
    }

    /**
     * Returns status.
     *
     * @return status
     */
    @JsonValue
    public String getStatus() {
        return status;
    }
}
