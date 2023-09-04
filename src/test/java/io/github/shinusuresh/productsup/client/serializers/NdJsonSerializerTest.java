package io.github.shinusuresh.productsup.client.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.shinusuresh.productsup.client.data.BaseStreamData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class NdJsonSerializerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void beforeEach() {
        var module = new SimpleModule("Nd json");
        module.addSerializer(new NdJsonSerializer());
        objectMapper.registerModule(module);
    }

    static Stream<Arguments> testData() {
        return Stream.of(
                arguments(named("Test with single object", List.of(
                        new TestStreamData("1", "Test", "100")
                )), """
                        {"id":"1","name":"Test","price":"100"}\s\n"""),
                arguments(named("Test with multiple object", List.of(
                        new TestStreamData("1", "Test", "100"),
                        new TestStreamData("2", "Test 2", "200")
                )), """
                        {"id":"1","name":"Test","price":"100"}\s
                        \s{"id":"2","name":"Test 2","price":"200"} \n"""),
                arguments(named("Empty list", Collections.emptyList()), ""),
                arguments(named("Null object", null), "null")
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testNdJsonSerialization(List<? extends BaseStreamData> data, String expected) throws JsonProcessingException {
        assertThat(objectMapper.writeValueAsString(data))
                .isEqualTo(expected);
    }

}
