package io.github.shinusuresh.productsup.client.domain.sites.history;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

/**
 * History response.
 *
 * @param id - Unique id
 * @param siteId - Site id
 * @param importTime - Import time
 * @param productCount - Product count
 * @param pid - Process id
 * @param links - Links map
 */
public record History(String id,
                      @JsonProperty("site_id")
                      String siteId,
                      @JsonProperty("import_time")
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                      Date importTime,
                      @JsonProperty("product_count")
                      String productCount,

                      String pid,
                      List<SortedMap<String, String>> links) {
}
