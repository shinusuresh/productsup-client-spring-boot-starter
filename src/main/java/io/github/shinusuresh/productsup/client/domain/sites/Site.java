package io.github.shinusuresh.productsup.client.domain.sites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

/**
 * Site response.
 *
 * @param id
 * @param title
 * @param status
 * @param projectId
 * @param importSchedule
 * @param idColumn
 * @param createdAt
 * @param processingStatus
 * @param availableProjectIds
 * @param links
 */
public record Site(String id,
                   String title,
                   SiteStatus status,
                   @JsonProperty("project_id") String projectId,
                   @JsonProperty("import_schedule") String importSchedule,
                   @JsonProperty("id_column") String idColumn,
                   @JsonProperty("created_at")
                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                   Date createdAt,
                   @JsonProperty("processing_status") ProcessingStatus processingStatus,
                   List<String> availableProjectIds,
                   List<SortedMap<String, String>> links) {
}
