package io.github.shinusuresh.productsup.client.config;

import io.github.shinusuresh.productsup.client.client.PlatformApiClient;
import io.github.shinusuresh.productsup.client.client.StreamApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * Autoconfiguration class for ProductsUp client.
 */
@Configuration
@EnableConfigurationProperties(ProductsUpProperties.class)
@Slf4j
public class ProductsUpAutoConfiguration {


    private final ProductsUpProperties productsUpProperties;

    /**
     * Auto configuration.
     *
     * @param productsUpProperties - {@link ProductsUpProperties}
     */
    public ProductsUpAutoConfiguration(final ProductsUpProperties productsUpProperties) {
        this.productsUpProperties = productsUpProperties;
        Assert.notNull(productsUpProperties.getToken(), () -> "Missing productsup.token");
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

    /**
     * Stream API client for Products up stream based operations.
     * Stream clients will be enabled only if you set the property
     * <code>
     * productsup.stream.enabled=true
     * </code>
     *
     * @return StreamApiClient
     */
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "productsup.stream.enabled", havingValue = "true")
    @Bean
    public StreamApiClient streamApiClient() {
        Assert.notNull(productsUpProperties.getAuthorizationToken(), () -> "Missing productsup.authorization-token");
        var webClient = WebClient.builder()
                .baseUrl(productsUpProperties.getStreamEndpoint())
                .defaultHeaders(httpHeaders -> httpHeaders.add(HttpHeaders.AUTHORIZATION, productsUpProperties.getAuthorizationToken()))
                .build();
        var factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return factory.createClient(StreamApiClient.class);
    }
}
