package com.github.shinusuresh.productsup.client.client;

import com.github.shinusuresh.productsup.client.config.ProductsUpAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ProductsUpAutoConfiguration.class, properties = "productsup.token=1:1")
class PlatformApiClientTest {


    @Autowired
    private PlatformApiClient platformApiClient;

    @Test
    void test() {
        var projects = platformApiClient.getProjects();
        assertNotNull(projects);
    }

}
