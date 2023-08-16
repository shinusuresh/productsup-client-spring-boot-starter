package io.github.shinusuresh.productsup.client;

import io.github.shinusuresh.productsup.client.domain.sites.history.History;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatIterable;
import static org.assertj.core.api.Assertions.from;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class ImportHistoryPlatformApiClientTest extends BasePlatformApiClient{

    public ImportHistoryPlatformApiClientTest(MockServerClient mockServerClient) {
        super(mockServerClient);
    }

    @Test
    void testImportHistory() {
        mockClient()
                .when(request()
                        .withMethod("GET")
                        .withPath("/sites/{id}/importhistory")
                        .withPathParameter("id", "[0-9]+"))
                .respond(response()
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(
                                """
                                        {
                                             "success": true,
                                             "Importhistory": [
                                                 {
                                                     "id": "11111111",
                                                     "site_id": "1234",
                                                     "import_time": "2015-01-01 11:22:33",
                                                     "product_count": "18370",
                                                     "pid": "47e6b828-3210-3568-8ec3-85ed3e2d944c",
                                                     "links": [
                                                        {
                                                            "site": "http://platform-api.productsup.com/platform/v2/sites/23232323"
                                                        }
                                                     ]
                                                 }
                                             ]
                                         }
                                        """
                        ));
        var importHistory = platformApiClient().siteImportHistory(123);
        assertThatIterable(importHistory.histories())
                .first()
                .returns("11111111", from(History::id))
                .returns("1234", from(History::siteId))
                .returns("18370", from(History::productCount))
                .returns("47e6b828-3210-3568-8ec3-85ed3e2d944c", from(History::pid))
                .returns(Date.from(LocalDateTime.parse("2015-01-01 11:22:33",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toInstant(ZoneOffset.UTC)), from(History::importTime))
                .returns(List.of(
                        Map.of("site", "http://platform-api.productsup.com/platform/v2/sites/23232323")
                ), from(History::links));


    }
}
