package io.github.shinusuresh.productsup.client.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Base data class for stream upload based operations.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseStreamData {
    private String id;
}
