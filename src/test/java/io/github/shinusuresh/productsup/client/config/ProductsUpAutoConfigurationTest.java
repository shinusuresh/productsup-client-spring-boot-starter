package io.github.shinusuresh.productsup.client.config;

import io.github.shinusuresh.productsup.client.client.PlatformApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ProductsUpAutoConfigurationTest {

    @Test
    void testPlatformApiClient() {
        final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
                .withSystemProperties("productsup.token=1:1")
                .withInitializer(new ConditionEvaluationReportLoggingListener())
                .withUserConfiguration(ProductsUpProperties.class, ProductsUpAutoConfiguration.class);

        contextRunner
                .run(context -> assertThat(context).hasSingleBean(PlatformApiClient.class));
    }

    static Stream<Arguments> streamTestData() {
        return Stream.of(
                arguments(named("Stream is enabled in config", "true"), true),
                arguments(named("Stream is enabled in config", "false"), false)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "streamTestData")
    void testStreamApiClient(final String enabled, final boolean beanExist) {
        final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
                .withSystemProperties(
                        "productsup.token=1:1",
                        "productsup.stream.enabled=" + enabled,
                        "productsup.authorization-token=xyz")
                .withInitializer(new ConditionEvaluationReportLoggingListener())
                .withUserConfiguration(ProductsUpProperties.class, ProductsUpAutoConfiguration.class);

        contextRunner
                .run(context -> assertThat(context.containsBean("streamApiClient")).isEqualTo(beanExist));
    }

}
