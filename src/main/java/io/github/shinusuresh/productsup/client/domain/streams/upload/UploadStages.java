package io.github.shinusuresh.productsup.client.domain.streams.upload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Upload stages.
 *
 * @param upload     - Upload stage {@link BatchStageStatus}
 * @param processing - Processing stage {@link BatchStageStatus}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record UploadStages(
        BatchStageStatus upload,
        BatchStageStatus processing
) {
}
