package com.github.shinusuresh.productsup.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("productsup")
public class ProductsUpProperties {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
