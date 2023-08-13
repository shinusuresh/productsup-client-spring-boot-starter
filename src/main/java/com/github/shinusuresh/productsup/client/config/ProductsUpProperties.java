package com.github.shinusuresh.productsup.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("productsup")
public class ProductsUpProperties {

    private String token;

    private String platformEndpoint = "https://platform-api.productsup.io/platform/v2";

    public String getPlatformEndpoint() {
        return platformEndpoint;
    }

    public void setPlatformEndpoint(String platformEndpoint) {
        this.platformEndpoint = platformEndpoint;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
