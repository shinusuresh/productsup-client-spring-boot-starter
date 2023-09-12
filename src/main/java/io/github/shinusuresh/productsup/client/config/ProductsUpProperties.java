package io.github.shinusuresh.productsup.client.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Products up related configurations.
 */
@ConfigurationProperties("productsup")
@Getter
@Setter
public class ProductsUpProperties {

    /**
     * Token for platform api calls
     */
    private String token;

    /**
     * Timeout for API calls.
     */
    private long timeout = 5L;

    /**
     * Authorization token for Stream api calls.
     */
    private String authorizationToken;


    /**
     *  Platform endpoint.
     */
    private String platformEndpoint = "https://platform-api.productsup.io/platform/v2";

    /**
     * Stream api endpoint.
     */
    private String streamEndpoint = "https://stream-api.productsup.com/";

}
