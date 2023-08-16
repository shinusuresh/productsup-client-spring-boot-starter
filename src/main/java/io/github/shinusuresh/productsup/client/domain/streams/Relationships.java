package io.github.shinusuresh.productsup.client.domain.streams;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Stream relationship.
 *
 * @param account - {@link Account}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Relationships(Account account) {
}
