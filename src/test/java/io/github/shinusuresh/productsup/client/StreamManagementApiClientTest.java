package io.github.shinusuresh.productsup.client;

import io.github.shinusuresh.productsup.client.client.StreamApiClient;
import io.github.shinusuresh.productsup.client.config.ProductsUpAutoConfiguration;
import io.github.shinusuresh.productsup.client.domain.streams.*;
import io.github.shinusuresh.productsup.client.domain.streams.create.CreateStream;
import io.github.shinusuresh.productsup.client.domain.streams.update.UpdateStream;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.mockserver.mock.OpenAPIExpectation;
import org.mockserver.model.JsonBody;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootTest(classes = ProductsUpAutoConfiguration.class, properties = {
        "productsup.token=1:1",
        "productsup.authorization-token=Bearer xyz",
        "productsup.stream.enabled=true"
})
@ActiveProfiles(value = "test")
@MockServerSettings(ports = {8787})
class StreamManagementApiClientTest {

    private final MockServerClient mockServerClient;

    @Autowired
    private StreamApiClient streamApiClient;

    StreamManagementApiClientTest(MockServerClient mockServerClient) {
        this.mockServerClient = mockServerClient;
        this.mockServerClient.upsert(
                OpenAPIExpectation.openAPIExpectation("https://api-docs.productsup.io/spec/stream-api-oas3-definition.yaml")
                        .withOperationsAndResponses(Map.of(
                                "GET /streams", "200",
                                "POST /streams", "201",
                                "DELETE /streams/{streamId}", "204",
                                "PATCH /streams/{streamId}", "200"
                        ))
        );
    }

    @Test
    void testListStreams() {

        var list = streamApiClient.listStreams();
        assertThatIterable(list.data())
                .first()
                .returns("some_string_value", from(Stream::id))
                .returns("stream", from(Stream::type))
                .returns("Product data stream", from((val) -> val.attributes().name()))
                .returns(StreamType.CHUNKED, from((val) -> val.attributes().type()))
                .returns("some_string_value", from((val) -> val.relationships().account().accountData().id()))
                .returns("account", from((val) -> val.relationships().account().accountData().type()));

    }

    @Test
    void testSuccessCreateStreams() {
        var data = new CreateStream(new Stream(null, "stream",
                new StreamAttributes("Test Stream", StreamType.CHUNKED, null, null), null, null));
        var createStreamResponse = streamApiClient.createStream(data);
        assertThat(createStreamResponse.data().id()).isEqualTo("some_string_value");
    }

    @Test
    void removeStream() {
        assertDoesNotThrow(() -> streamApiClient.removeStream("83543218"));
    }

    @Test
    void testUpdateStream() {
        var updateStreamRequest = new UpdateStream(new Stream("123", "stream",
                new StreamAttributes("Product data stream", null, null, null), null, null));
        var updateStreamResponse = streamApiClient.updateStream("83543218", updateStreamRequest);
        assertThat(updateStreamResponse.data())
                .extracting(Stream::id, Stream::type,
                        data -> data.attributes().name(),
                        data -> data.attributes().type())
                .containsExactly("some_string_value", "stream", "Product data stream", StreamType.CHUNKED);


    }

    @Test()
    void testFailCreateStreams() {
        this.mockServerClient.reset();
        this.mockServerClient
                .when(request()
                        .withMethod("POST")
                        .withPath("/streams")
                        .withContentType(MediaType.parse("application/vnd.api+json"))
                        .withHeader("Authorization", "Bearer xyz")
                        .withBody(new JsonBody("""
                                {
                                    "data": {
                                        "type": "stream",
                                        "attributes": {
                                          "name": "Test error Stream",
                                          "type": "chunked"
                                        }
                                      }
                                 }
                                """)))
                .respond(response()
                        .withStatusCode(409)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(new JsonBody("""
                                 {
                                   "errors": [{
                                       "id": "e1a6f644-3d72-44e9-83e0-f64a640cb04c",
                                       "status": "409",
                                       "code": "13579",
                                       "title": "Validation error happened with stream payload",
                                       "detail": "",
                                       "meta": {
                                         "duplicate": {
                                           "type": "stream",
                                           "id": "123",
                                           "links": {
                                             "self": "/streams/123"
                                           }
                                         }
                                       }
                                     }]
                                 }
                                """
                        )));
        var data = new CreateStream(new Stream(null, "stream",
                new StreamAttributes("Test error Stream", StreamType.CHUNKED, null, null), null, null));

        var exception = assertThrows(WebClientResponseException.class, () -> streamApiClient.createStream(data));
        var errors = exception.getResponseBodyAs(StreamErrors.class);
        assert errors != null;
        assertThat(errors.errors()).hasSize(1);
        assertThatIterable(errors.errors())
                .first()
                .returns("e1a6f644-3d72-44e9-83e0-f64a640cb04c", from(StreamError::id))
                .returns("409", from(StreamError::status))
                .returns("13579", from(StreamError::code))
                .returns("Validation error happened with stream payload", from(StreamError::title));
    }
}
