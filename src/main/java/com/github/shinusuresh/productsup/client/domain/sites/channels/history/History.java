package com.github.shinusuresh.productsup.client.domain.sites.channels.history;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record History(String id,
                      @JsonProperty("site_id")
                      String siteId,
                      @JsonProperty("site_channel_id")
                      String siteChannelId,
                      @JsonProperty("export_time")
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                      Date exportTime,
                      @JsonProperty("export_start")
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                      Date exportStart,
                      @JsonProperty("product_count")
                      String productCount,
                      @JsonProperty("product_count_now")
                      String productCountNow,
                      @JsonProperty("product_count_previous")
                      String productCountPrevious,
                      @JsonProperty("process_status")
                      String processStatus,
                      String pid,
                      @JsonProperty("product_count_new")
                      String productCountNew,
                      @JsonProperty("product_count_modified")
                      String productCountModified,
                      @JsonProperty("product_count_deleted")
                      String productCountDeleted,
                      @JsonProperty("product_count_unchanged")
                      String productCountUnChanged,
                      String uploaded) {
}
