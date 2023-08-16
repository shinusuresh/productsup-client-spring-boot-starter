package io.github.shinusuresh.productsup.client.config;

import io.github.shinusuresh.productsup.client.client.PlatformApiClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * Autoconfiguration class for ProductsUp client.
 */
@Configuration
@EnableConfigurationProperties(ProductsUpProperties.class)
public class ProductsUpAutoConfiguration {


    private final ProductsUpProperties productsUpProperties;

    /**
     * Auto configuration.
     *
     * @param productsUpProperties - {@link ProductsUpProperties}
     */
    public ProductsUpAutoConfiguration(final ProductsUpProperties productsUpProperties) {
        this.productsUpProperties = productsUpProperties;
        Assert.notNull(productsUpProperties.getToken(), () -> "productsup.token missing in properties. " +
                "Please add it in your profile files application.yml or application.properties");
    }

    /**
     * HTTP client for ProductsUp platform API calls.
     *
     * @return PlatformApiClient
     */
    @ConditionalOnMissingBean
    @Bean
    public PlatformApiClient platformApiClient() {
        var webClient = WebClient.builder()
                .baseUrl(productsUpProperties.getPlatformEndpoint())
                .defaultHeader("X-Auth-Token", productsUpProperties.getToken())
                .build();
        var factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return factory.createClient(PlatformApiClient.class);
    }
}
