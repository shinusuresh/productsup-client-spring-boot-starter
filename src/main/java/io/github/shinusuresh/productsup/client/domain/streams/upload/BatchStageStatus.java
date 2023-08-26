package io.github.shinusuresh.productsup.client.domain.streams.upload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Date;
import java.util.List;

/**
 * Batch stage status.
 *
 * @param completedAt  - Completed date time.
 * @param status       - {@link Status}
 * @param successCount - Number of successful products.
 * @param errorCount   - Total number of errors.
 * @param errors       - Distinct error samples grouped by the error message.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BatchStageStatus(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
        Date completedAt,
        Status status,

        int successCount,
        int errorCount,
        List<BatchStageError> errors
) {

    public enum Status {
        SUCCESS("success"),
        WARNING("warning"),
        FAILURE("failure");

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
