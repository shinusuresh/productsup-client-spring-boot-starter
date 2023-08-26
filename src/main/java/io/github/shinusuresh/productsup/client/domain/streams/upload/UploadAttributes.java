package io.github.shinusuresh.productsup.client.domain.streams.upload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Upload attributes.
 *
 * @param status     - Indicates the overall status of the batch as it goes through various stages. {@link Status}
 * @param errorCount - Total error count from all stages.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record UploadAttributes(
        Status status,
        int errorCount,
        UploadStages stages


) {
    public enum Status {
        UPLOADED("uploaded"),
        PROCESSED("processed"),
        FAILED("failed");

        private final String statusValue;

        Status(String statusValue) {
            this.statusValue = statusValue;
        }

        @JsonValue
        public String getStatus() {
            return statusValue;
        }
    }
}
