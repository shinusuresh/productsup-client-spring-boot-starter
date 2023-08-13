package com.github.shinusuresh.productsup.client;

import com.github.shinusuresh.productsup.client.domain.sites.errors.Error;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class SiteErrorsPlatformApiClientTest extends BasePlatformApiClient {

    public SiteErrorsPlatformApiClientTest(MockServerClient mockServerClient) {
        super(mockServerClient);
    }

    @Test
    void testSiteErrors() {
        mockClient()
                .when(request()
                        .withMethod("GET")
                        .withPath("/sites/errors/{id}")
                        .withPathParameter("id", "[0-9]+"))
                .respond(response()
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(
                                """
                                        {
                                            "success": true,
                                            "Errors": [
                                                {
                                                    "id": "1802017",
                                                    "pid": "537cb0659a7dc",
                                                    "error": "10012",
                                                    "data": {
                                                         "Metadata": {
                                                             "rate": 19
                                                         },
                                                         "Filetime": "2023-08-13 09:01:47",
                                                         "Datasource": "https://server/products.csv",
                                                         "Unchanged Runs": 19,
                                                         "Setup": "https://platform.productsup.com/configure-dataflow/123/import-edit/123"
                                                     },
                                                    "site_id": "123",
                                                    "datetime": "2003-11-15 00:00:00",
                                                    "links": [
                                                        {
                                                            "self": "http://platform-api.productsup.com/platform/v2/errors/2323232323"
                                                        },
                                                        {
                                                            "site": "http://platform-api.productsup.com/platform/v2/sites/23232323"
                                                        }
                                                    ]
                                                }
                                            ]
                                        }
                                        """
                        ));
        var errors = platformApiClient().siteErrors(123);
        assertThatIterable(errors.errors())
                .first()
                .returns("1802017", from(Error::id))
                .returns("537cb0659a7dc", from(Error::pid))
                .returns("10012", from(Error::error))
                .returns("123", from(Error::siteId))
                .returns(Date.from(LocalDateTime.parse("2003-11-15 00:00:00",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toInstant(ZoneOffset.UTC)), from(Error::dateTime))
                .returns(List.of(
                        Map.of("self", "http://platform-api.productsup.com/platform/v2/errors/2323232323"),
                        Map.of("site", "http://platform-api.productsup.com/platform/v2/sites/23232323")
                ), from(Error::links));
        assertThatIterable(errors.errors())
                .first()
                .usingRecursiveComparison()
                .comparingOnlyFields("dataSource").isEqualTo("https://server/products.csv");
    }
}
