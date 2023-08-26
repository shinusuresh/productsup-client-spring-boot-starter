package io.github.shinusuresh.productsup.client;

import io.github.shinusuresh.productsup.client.client.StreamApiClient;
import io.github.shinusuresh.productsup.client.config.ProductsUpAutoConfiguration;
import io.github.shinusuresh.productsup.client.domain.streams.upload.BatchStageStatus;
import io.github.shinusuresh.productsup.client.domain.streams.upload.UploadAttributes;
import io.github.shinusuresh.productsup.client.domain.streams.upload.UploadResponseData;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.mockserver.model.JsonBody;
import org.mockserver.model.MediaType;
import org.mockserver.model.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootTest(classes = ProductsUpAutoConfiguration.class, properties = {
        "productsup.token=1:1",
        "productsup.authorization-token=Bearer xyz",
        "productsup.stream.enabled=true"
})
@ActiveProfiles(value = "test")
@MockServerSettings(ports = {8787})
class UploadDataStreamApiClientTest {

    private final MockServerClient mockServerClient;

    @Autowired
    private StreamApiClient streamApiClient;

    public UploadDataStreamApiClientTest(MockServerClient mockServerClient) {
        this.mockServerClient = mockServerClient;
    }

    @SuppressWarnings("unchecked")
    @Test
    void uploadReferencedData() {
        this.mockServerClient
                .when(request()
                        .withMethod("POST")
                        .withPath("/streams/{id}/products")
                        .withPathParameters(new Parameter("id", "[0-9]+"))
                        .withContentType(MediaType.parse("application/json"))
                        .withHeader("Authorization", "Bearer xyz")
                        .withBody(new JsonBody("""
                                [
                                    {
                                        "id": "ASWHJ4-123-J",
                                        "additionalProp1": "string",
                                        "additionalProp2": "string",
                                        "additionalProp3": "string"
                                      },
                                      {
                                        "id": "ASWHJ4-124-J",
                                        "additionalProp1": "string",
                                        "additionalProp2": "string",
                                        "additionalProp3": "string"
                                      }
                                ]
                                """)))
                .respond(response()
                        .withStatusCode(202)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(new JsonBody("""
                                  {
                                    "data": {
                                      "type": "batch",
                                      "id": "4b5dd7c1-afc6-4ad9-96a6-c5d4542d0228",
                                      "attributes": {
                                        "status": "failed",
                                        "errorCount": 0,
                                        "stages": {
                                          "upload": {
                                            "completedAt": "2021-12-21T21:12:21+00:00",
                                            "status": "failure",
                                            "successCount": 0,
                                            "errorCount": 1,
                                            "errors": [
                                              {
                                                "message": "Syntax error",
                                                "occurrences": 1,
                                                "example": {
                                                  "lineNumber": 0,
                                                  "value": "{\\"id\\":\\"123\\"\\"name\\":\\"Product\\"}"
                                                }
                                              }
                                            ]
                                          },
                                          "processing": null
                                        }
                                      }
                                    }
                                  }
                                """
                        )));
        var uploadResponse = streamApiClient.uploadReferencedData("83543218",
                Map.of(
                        "id", "ASWHJ4-123-J",
                        "additionalProp1", "string",
                        "additionalProp2", "string",
                        "additionalProp3", "string"
                ),
                Map.of(
                        "id", "ASWHJ4-124-J",
                        "additionalProp1", "string",
                        "additionalProp2", "string",
                        "additionalProp3", "string"
                ));
        assertThat(uploadResponse.data())
                .extracting(UploadResponseData::type, UploadResponseData::id,
                        data -> data.attributes().status(),
                        data -> data.attributes().errorCount(),
                        data -> data.attributes().stages().upload().status(),
                        data -> data.attributes().stages().upload().errors().size())
                .containsExactly("batch", "4b5dd7c1-afc6-4ad9-96a6-c5d4542d0228",
                        UploadAttributes.Status.FAILED,
                        0, BatchStageStatus.Status.FAILURE, 1);

    }
}
