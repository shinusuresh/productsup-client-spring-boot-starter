package io.github.shinusuresh.productsup.client;

import io.github.shinusuresh.productsup.client.client.StreamApiUploadClient;
import io.github.shinusuresh.productsup.client.config.ProductsUpAutoConfiguration;
import io.github.shinusuresh.productsup.client.data.BaseStreamData;
import io.github.shinusuresh.productsup.client.domain.streams.ResponseData;
import io.github.shinusuresh.productsup.client.domain.streams.upload.BatchStageStatus;
import io.github.shinusuresh.productsup.client.domain.streams.upload.UploadAttributes;
import io.github.shinusuresh.productsup.client.domain.streams.upload.UploadResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.mockserver.model.JsonBody;
import org.mockserver.model.MediaType;
import org.mockserver.model.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

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
class UploadChunkedDataStreamApiClientTest {

    private final MockServerClient mockServerClient;

    @Autowired
    private StreamApiUploadClient streamApiUploadClient;

    public UploadChunkedDataStreamApiClientTest(MockServerClient mockServerClient) {
        this.mockServerClient = mockServerClient;
    }

    @Test
    void uploadChunkedData() {
        this.mockServerClient.reset();
        this.mockServerClient
                .when(request()
                        .withMethod("POST")
                        .withPath("/streams/{id}/products")
                        .withPathParameters(new Parameter("id", "[0-9]+"))
                        .withContentType(MediaType.parse("application/x-ndjson"))
                        .withHeader("Authorization", "Bearer xyz")
                        .withBody(new JsonBody("""
                                    {"id":"ASWHJ4-123-J","title":"My Product","price":"4"}
                                    {"id":"ASWHJ4-124-J","title":"My Product","price":"4"}
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
        var uploadResponse = streamApiUploadClient.uploadChunkeddData("83543218",
                List.of(
                        new SampleData("ASWHJ4-123-J", "My Product", "4"),
                        new SampleData("ASWHJ4-124-J", "My Product", "4")
                )
        );
        assertThat(uploadResponse.data())
                .extracting(ResponseData::type, ResponseData::id,
                        data -> data.attributes().status(),
                        data -> data.attributes().errorCount(),
                        data -> data.attributes().stages().upload().status(),
                        data -> data.attributes().stages().upload().errors().size())
                .containsExactly("batch", "4b5dd7c1-afc6-4ad9-96a6-c5d4542d0228",
                        UploadAttributes.Status.FAILED,
                        0, BatchStageStatus.Status.FAILURE, 1);

    }

    @Test
    void testUploadEmptyPayloadTest() {
        this.mockServerClient.reset();
        this.mockServerClient
                .when(request()
                        .withMethod("POST")
                        .withPath("/streams/{id}/products")
                        .withPathParameters(new Parameter("id", "[0-9]+"))
                        .withContentType(MediaType.parse("application/x-ndjson"))
                        .withHeader("Authorization", "Bearer xyz")
                        .withBody(""))
                .respond(response()
                        .withStatusCode(422)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(new JsonBody("""
                                  {
                                        "data": {
                                            "type": "batch",
                                            "id": "6895f313-5fe1-4fa5-b057-c9934b21e322",
                                            "attributes": {
                                                "status": "failed",
                                                "errorCount": 1,
                                                "stages": {
                                                    "upload": {
                                                        "completedAt": "2023-09-05T13:45:21+02:00",
                                                        "status": "failure",
                                                        "successCount": 0,
                                                        "errorCount": 1,
                                                        "errors": [
                                                            {
                                                                "message": "Empty payload",
                                                                "occurrences": 1,
                                                                "example": {
                                                                    "lineNumber": 1,
                                                                    "value": ""
                                                                }
                                                            }
                                                        ]
                                                    },
                                                    "processing": null
                                                }
                                            },
                                            "relationships": {
                                                "stream": {
                                                    "data": {
                                                        "type": "stream",
                                                        "id": "15271"
                                                    }
                                                }
                                            }
                                        },
                                        "relationships": {
                                            "stream": {
                                                "data": {
                                                    "type": "stream",
                                                    "id": "15271"
                                                }
                                            }
                                        }
                                    }
                                """
                        )));

        try {
            streamApiUploadClient.uploadChunkeddData("83543218", List.of(new SampleData()));
        } catch (WebClientResponseException exception) {
            var errors = exception.getResponseBodyAs(UploadResponse.class);
            assert errors != null;
            assertThat(errors.data())
                    .extracting(ResponseData::type, ResponseData::id,
                            responseData -> responseData.attributes().errorCount(),
                            responseData -> responseData.attributes().status(),
                            responseData -> responseData.attributes().stages().upload().status(),
                            responseData -> responseData.attributes().stages().upload().errors().size())
                    .containsExactly("batch", "6895f313-5fe1-4fa5-b057-c9934b21e322",
                            1, UploadAttributes.Status.FAILED,
                            BatchStageStatus.Status.FAILURE, 1);
        }
    }


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SampleData extends BaseStreamData {

        private String title;
        private String price;

        public SampleData() {
            super();
        }

        public SampleData(String id, String title, String price) {
            super(id);
            this.title = title;
            this.price = price;
        }
    }
}


