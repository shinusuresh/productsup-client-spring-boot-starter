package io.github.shinusuresh.productsup.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Products up related configurations.
 */
@ConfigurationProperties("productsup")
public class ProductsUpProperties {

    private String token;

    private String authorizationToken;

    private String platformEndpoint = "https://platform-api.productsup.io/platform/v2";

    private String streamEndpoint = "https://stream-api.productsup.com/";

    /**
     * Returns platform endpoint.
     *
     * @return platform endpoint url
     */
    public String getPlatformEndpoint() {
        return platformEndpoint;
    }

    /**
     * Sets platform endpoint.
     *
     * @param platformEndpoint - endpoint for platform calls.
     */
    public void setPlatformEndpoint(String platformEndpoint) {
        this.platformEndpoint = platformEndpoint;
    }


    /**
     * Returns Products up token configured for a client.
     *
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token
     * @param token - token for the client.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Returns stream endpoint.
     *
     * @return Stream api endpoint.
     */
    public String getStreamEndpoint() {
        return streamEndpoint;
    }

    /**
     * Sets stream endpoint.
     *
     * @param streamEndpoint - stream endpoint.
     */
    public void setStreamEndpoint(String streamEndpoint) {
        this.streamEndpoint = streamEndpoint;
    }

    /**
     * Returns authorization token for Stream api calls.
     *
     * @return authorization token
     */
    public String getAuthorizationToken() {
        return authorizationToken;
    }

    /**
     * Sets authorization token for stream api calls.
     *
     * @param authorizationToken - Authorization token.
     */
    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }
}
