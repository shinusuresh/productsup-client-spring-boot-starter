package io.github.shinusuresh.productsup.client.domain.streams.list;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Import type for feeds.
 */
public enum ImportType {

    /**
     * Main data Feed.
     */
    MAIN_DATA_FEED(1),

    /**
     * Additional data feed.
     */
    ADDITIONAL_DATA_FEED(2);

    private final int feedType;

    ImportType(int feedType) {
        this.feedType = feedType;
    }

    @JsonValue
    public int getFeedType() {
        return this.feedType;
    }
}
