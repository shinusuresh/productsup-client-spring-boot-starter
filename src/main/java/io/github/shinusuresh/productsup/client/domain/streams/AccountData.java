package io.github.shinusuresh.productsup.client.domain.streams;

/**
 * Account information of where the stream belongs to.
 *
 * @param type - Account type.
 * @param id - Site id.
 */
public record AccountData(String type, String id) {
}
