package com.github.shinusuresh.productsup.client.domain.sites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

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
