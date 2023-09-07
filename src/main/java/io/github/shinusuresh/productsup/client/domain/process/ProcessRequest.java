package io.github.shinusuresh.productsup.client.domain.process;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request for Process data api.
 *
 * @param action    - Process action.
 * @param channelId - Export or channel id,  only required for action types export and channel. For import set to null.
 * @param batchId   - A batch id that was recently committed. For import, set to null
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProcessRequest(
        ProcessAction action,

        @JsonProperty("id")
        String channelId,
        String batchId
) {

}
