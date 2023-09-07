package io.github.shinusuresh.productsup.client;

import io.github.shinusuresh.productsup.client.domain.process.ProcessAction;
import io.github.shinusuresh.productsup.client.domain.process.ProcessRequest;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.JsonBody;
import org.mockserver.model.MediaType;
import org.mockserver.model.Parameter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class ProcessDataPlatformApiClientTest extends BasePlatformApiClient {

    public ProcessDataPlatformApiClientTest(MockServerClient mockServerClient) {
        super(mockServerClient);
    }

    @Test
    void testProcessData() {
        mockClient()
                .when(request()
                        .withMethod("POST")
                        .withPath("/process/{id}")
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withPathParameters(new Parameter("id", "[0-9]+"))
                        .withBody(new JsonBody("""
                                {
                                    "action": "import"
                                }""")))
                .respond(response()
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(new JsonBody(
                                """
                                        {
                                              "success": true,
                                              "process_id": "adasd-sdsdsd-sdsdsd-sdsd"
                                          }
                                        """
                        )));
        var processResponse = platformApiClient().process(123, new ProcessRequest(ProcessAction.IMPORT,
                null, null));
        assertThat(processResponse.success()).isTrue();
    }
}
