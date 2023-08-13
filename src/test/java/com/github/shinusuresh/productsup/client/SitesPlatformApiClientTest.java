package com.github.shinusuresh.productsup.client;

import com.github.shinusuresh.productsup.client.domain.sites.ProcessingStatus;
import com.github.shinusuresh.productsup.client.domain.sites.Site;
import com.github.shinusuresh.productsup.client.domain.sites.SiteStatus;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatIterable;
import static org.assertj.core.api.Assertions.from;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class SitesPlatformApiClientTest extends BasePlatformApiClient {

    public SitesPlatformApiClientTest(MockServerClient mockServerClient) {
        super(mockServerClient);
    }

    @Test
    void testSites() {
        mockClient().when(
                request()
                        .withMethod("GET")
                        .withPath("/projects/{projectId}/sites")
                        .withPathParameter("projectId", "[0-9\\-]+")
        ).respond(
                response()
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(
                                """
                                        {
                                             "success": true,
                                             "Sites": [
                                                 {
                                                     "id": "123",
                                                     "title": "site 1",
                                                     "status": "active",
                                                     "created_at": "2015-01-01 11:22:33",
                                                     "project_id": "123",
                                                     "id_column": "id",
                                                     "availableProjectIds": ["123", "345"],
                                                     "processing_status": "Done",
                                                     "import_schedule": "TZ=Europe/London\\n0 3,15 * * *",
                                                     "links": [
                                                        {
                                                            "self": "http://platform-api.productsup.io/platform/v2/projects/123"
                                                        },
                                                        {
                                                            "tags": "http://platform-api.productsup.io/platform/v2/projects/123/tags"
                                                        },
                                                        {
                                                            "projects": "http://platform-api.productsup.io/platform/v2/projects/123"
                                                        }
                                                     ]
                                                 }
                                             ]
                                         }
                                        """
                        )
        );
        var sites = platformApiClient().getSites("123");
        assertThatIterable(sites.sites())
                .first()
                .returns("123", from(Site::id))
                .returns("site 1", from(Site::title))
                .returns(SiteStatus.ACTIVE, from(Site::status))
                .returns(Date.from(LocalDateTime.parse("2015-01-01 11:22:33",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toInstant(ZoneOffset.UTC)), from(Site::createdAt))
                .returns("123", from(Site::projectId))
                .returns("id", from(Site::idColumn))
                .returns(List.of("123", "345"), from(Site::availableProjectIds))
                .returns(ProcessingStatus.DONE, from(Site::processingStatus))
                .returns("TZ=Europe/London\n0 3,15 * * *", from(Site::importSchedule))
                .returns(List.of(
                        Map.of("self", "http://platform-api.productsup.io/platform/v2/projects/123"),
                        Map.of("tags", "http://platform-api.productsup.io/platform/v2/projects/123/tags"),
                        Map.of("projects", "http://platform-api.productsup.io/platform/v2/projects/123")
                ), from(Site::links));

    }
}
