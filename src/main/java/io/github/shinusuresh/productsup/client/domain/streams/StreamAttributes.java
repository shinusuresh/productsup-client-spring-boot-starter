package io.github.shinusuresh.productsup.client.domain.streams;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Stream attributes.
 *
 * @param name - Stream name.
 * @param type - Stream type.
 * @param createdAt - Stream created time.
 * @param updatedAt - Updated time.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record StreamAttributes(String name,
                               StreamType type,
                               @JsonProperty("createdAt")
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss+00:00")
                               Date createdAt,
                               @JsonProperty("updatedAt")
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss+00:00")
                               Date updatedAt) {
}
