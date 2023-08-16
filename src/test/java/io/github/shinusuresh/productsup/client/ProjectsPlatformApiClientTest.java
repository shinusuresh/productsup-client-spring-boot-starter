package io.github.shinusuresh.productsup.client;

import io.github.shinusuresh.productsup.client.domain.project.Project;
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

class ProjectsPlatformApiClientTest extends BasePlatformApiClient {

    public ProjectsPlatformApiClientTest(MockServerClient mockServerClient) {
        super(mockServerClient);
    }

    @Test
    void testProjects() {
        mockClient().when(
                request()
                        .withMethod("GET")
                        .withPath("/projects")
        ).respond(
                response()
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(
                                """
                                        {
                                            "success": true,
                                            "Projects": [
                                                {
                                                    "id": "1",
                                                    "name": "default project",
                                                    "created_at": "2013-03-21 12:47:57",
                                                    "links": [
                                                        {
                                                            "self": "http://platform-api.productsup.io/platform/v2/projects/123"
                                                        },
                                                        {
                                                            "sites": "http://platform-api.productsup.io/platform/v2/projects/123/sites"
                                                        }
                                                    ]
                                                }
                                            ]
                                        }
                                        """
                        )
        );
        var projects = platformApiClient().getProjects();
        assertThatIterable(projects.projects())
                .first()
                .returns("1", from(Project::id))
                .returns("default project", from(Project::name))
                .returns(Date.from(LocalDateTime.parse("2013-03-21 12:47:57",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toInstant(ZoneOffset.UTC)), from(Project::createdAt))
                .returns(List.of(
                        Map.of("self", "http://platform-api.productsup.io/platform/v2/projects/123"),
                        Map.of("sites", "http://platform-api.productsup.io/platform/v2/projects/123/sites")
                ), from(Project::links));
    }

}
