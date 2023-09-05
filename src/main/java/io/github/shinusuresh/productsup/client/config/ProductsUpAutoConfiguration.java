package io.github.shinusuresh.productsup.client.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.shinusuresh.productsup.client.client.PlatformApiClient;
import io.github.shinusuresh.productsup.client.client.StreamApiClient;
import io.github.shinusuresh.productsup.client.client.StreamApiUploadClient;
import io.github.shinusuresh.productsup.client.serializers.NdJsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
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
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        var webClient = WebClient.builder()
                .baseUrl(productsUpProperties.getStreamEndpoint())
                .defaultHeaders(httpHeaders -> httpHeaders.add(HttpHeaders.AUTHORIZATION, productsUpProperties.getAuthorizationToken()))
                /*.codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                        .jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON)))*/
                .build();
        var factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return factory.createClient(StreamApiClient.class);
    }

    /**
     * Stream client used for upload operations that uses {@link org.springframework.http.MediaType#APPLICATION_NDJSON_VALUE}
     *
     * @return StreamApiUploadClient
     */
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "productsup.stream.enabled", havingValue = "true")
    @Bean
    public StreamApiUploadClient streamApiUploadClient() {
        Assert.notNull(productsUpProperties.getAuthorizationToken(), () -> "Missing productsup.authorization-token");
        var webClient = WebClient.builder()
                .baseUrl(productsUpProperties.getStreamEndpoint())
                .defaultHeaders(httpHeaders -> httpHeaders.add(HttpHeaders.AUTHORIZATION, productsUpProperties.getAuthorizationToken()))
                .codecs(configurer -> {
                    var objectMapper = new ObjectMapper();
                    objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
                    var module = new SimpleModule("Nd json");
                    module.addSerializer(new NdJsonSerializer());
                    objectMapper.registerModule(module);
                    var encoder = new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_NDJSON);
                    configurer.customCodecs().registerWithDefaultConfig(encoder);
                })
                .build();
        var factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return factory.createClient(StreamApiUploadClient.class);
    }
}
