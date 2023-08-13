package com.github.shinusuresh.productsup.client;

import com.github.shinusuresh.productsup.client.client.PlatformApiClient;
import com.github.shinusuresh.productsup.client.config.ProductsUpAutoConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProductsUpAutoConfiguration.class, properties = "productsup.token=1:1")
@ActiveProfiles(value = "test")
@ExtendWith(value = MockitoExtension.class)
@MockServerSettings(ports = {8787})
public abstract class BasePlatformApiClient {

    private final MockServerClient mockServerClient;

    @Autowired
    private PlatformApiClient platformApiClient;

    public BasePlatformApiClient(MockServerClient mockServerClient) {
        this.mockServerClient = mockServerClient;
    }

    protected MockServerClient mockClient() {
        return this.mockServerClient;
    }

    protected PlatformApiClient platformApiClient() {
        return this.platformApiClient;
    }
}
