package io.github.shinusuresh.productsup.client.domain.process;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Allowed process statuses.
 */
public enum ProcessAction {

    IMPORT("import"),
    EXPORT("export"),
    CHANNEL("channel"),
    EXPORT_ALL("export-all"),
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
