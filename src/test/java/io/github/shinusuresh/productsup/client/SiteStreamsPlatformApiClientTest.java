package io.github.shinusuresh.productsup.client;

import io.github.shinusuresh.productsup.client.domain.sites.SiteStatus;
import io.github.shinusuresh.productsup.client.domain.streams.attach.AttachStream;
import io.github.shinusuresh.productsup.client.domain.streams.attach.AttachStreamResponse;
import io.github.shinusuresh.productsup.client.domain.streams.list.ImportType;
import io.github.shinusuresh.productsup.client.domain.streams.list.StreamSource;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.JsonBody;
import org.mockserver.model.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIterable;
import static org.assertj.core.api.Assertions.from;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class SiteStreamsPlatformApiClientTest extends BasePlatformApiClient {

    public SiteStreamsPlatformApiClientTest(MockServerClient mockServerClient) {
        super(mockServerClient);
    }

    @Test
    void testListSiteStreamDataSource() {
        mockClient()
                .when(request()
                        .withMethod("GET")
                        .withPath("/sites/{id}/streams")
                        .withPathParameter("id", "[0-9]+"))
                .respond(response()
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(new JsonBody(
                                """
                                        {
                                             "success": true,
                                             "Sources": [
                                                 {
                                                     "id": 1,
                                                     "site_id": 123,
                                                     "description": "stream api",
                                                     "source": "",
                                                     "import_type": 1,
                                                     "import_id": 331,
                                                     "status": "active",
                                                     "settings": [
                                                         "stream : 1"
                                                     ]
                                                 }
                                             ],
                                             "meta": {
                                                 "cursor": {
                                                     "current": 0,
                                                     "prev": 0,
                                                     "next": 1,
                                                     "count": 1
                                                 }
                                             }
                                         }
                                        """
                        )));
        var listStreams = platformApiClient().listStreamsBySiteId(123);
        assertThatIterable(listStreams.streamSources())
                .first()
                .returns("1", from(StreamSource::id))
                .returns("123", from(StreamSource::siteId))
                .returns("stream api", from(StreamSource::description))
                .returns("", from(StreamSource::source))
                .returns(ImportType.MAIN_DATA_FEED, from(StreamSource::importType))
                .returns("331", from(StreamSource::importId))
                .returns("active", from(StreamSource::status))
                .returns(List.of("stream : 1"), from(StreamSource::settings));

    }

    @Test
    void attachStreamToSite() {
        mockClient()
                .when(request()
                        .withMethod("POST")
                        .withPath("/sites/{id}/streams")
                        .withPathParameter("id", "[0-9]+")
                        .withBody(new JsonBody(
                                """
                                                {
                                                      "import_type": 2,
                                                      "stream_id": 123,
                                                      "description": "Attach stream api to site",
                                                      "status": "active"
                                                }
                                        """
                        )))
                .respond(response()
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(new JsonBody(
                                """
                                                {
                                                      "success": true
                                                }
                                        """
                        )));
        var response = platformApiClient().createStreamDataSource(123, new AttachStream(
                ImportType.ADDITIONAL_DATA_FEED, "Attach stream api to site", 123, SiteStatus.ACTIVE
        ));
        assertThat(response)
                .extracting(AttachStreamResponse::success)
                .isEqualTo(true);
    }
}
