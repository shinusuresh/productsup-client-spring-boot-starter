package io.github.shinusuresh.productsup.client;

import io.github.shinusuresh.productsup.client.domain.sites.ProcessingStatus;
import io.github.shinusuresh.productsup.client.domain.sites.Site;
import io.github.shinusuresh.productsup.client.domain.sites.SiteStatus;
import io.github.shinusuresh.productsup.client.domain.sites.Sites;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import org.mockserver.model.RequestDefinition;

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

class SitesPlatformApiClientTest extends BasePlatformApiClient {

    public SitesPlatformApiClientTest(MockServerClient mockServerClient) {
        super(mockServerClient);
    }

    @Test
    void testSitesByProjectId() {
        mockClient().when(sitesByProjectId())
                .respond(mockResponse());
        assertSites(platformApiClient().getSitesByProjectId("123"));
    }

    @Test
    void testAllSites() {
        mockClient().when(allSites())
                .respond(mockResponse());
        assertSites(platformApiClient().getSites());
    }

    @Test
    void testSiteByTag() {
        mockClient().when(sitesByTag())
                .respond(mockResponse());
        assertSites(platformApiClient().getSitesByTags("siteName:value"));
    }

    @Test
    void testSiteById() {
        mockClient().when(sitesById())
                .respond(mockResponse());
        assertSites(platformApiClient().getSitesById(123));
    }

    private RequestDefinition sitesByProjectId() {
        return request()
                .withMethod("GET")
                .withPath("/projects/{projectId}/sites")
                .withPathParameter("projectId", "[0-9\\-]+");
    }

    private RequestDefinition allSites() {
        return request()
                .withMethod("GET")
                .withPath("/sites");
    }

    private RequestDefinition sitesByTag() {
        return request()
                .withMethod("GET")
                .withPath("/sites/{tag}")
                .withPathParameter("tag", "[0-9a-zA-Z:0-9a-zA-Z%3A\\-]+");
    }

    private RequestDefinition sitesById() {
        return request()
                .withMethod("GET")
                .withPath("/sites/{id}")
                .withPathParameter("id", "[0-9]+");
    }

    private HttpResponse mockResponse() {
        return response()
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
                );
    }

    private void assertSites(final Sites sites) {
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
