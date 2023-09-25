package io.github.shinusuresh.productsup.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import io.github.shinusuresh.productsup.client.domain.sites.tags.Tag;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.JsonBody;
import org.mockserver.model.MediaType;
import org.mockserver.model.Parameter;

class TagsPlatformApiClientTest extends BasePlatformApiClient {

    public TagsPlatformApiClientTest(MockServerClient mockServerClient) {
        super(mockServerClient);
    }

    @Test
    void testAllTagsForSite() {
        mockClient()
            .when(request()
                .withMethod("GET")
                .withPath("/sites/{id}/tags")
                .withPathParameters(new Parameter("id", "[0-9]+")))
            .respond(response()
                .withContentType(MediaType.APPLICATION_JSON)
                .withBody(new JsonBody(
                    """
                       {
                           "success": true,
                           "Tags": [
                               {
                                   "id": "123",
                                   "site_id": "345",
                                   "key": "siteTagKey",
                                   "value": "siteTagValue",
                                   "readonly": "0",
                                   "links": [
                                       {
                                           "self": "http://platform-api.productsup.io/platform/v2/tags/123"
                                       },
                                       {
                                           "site": "http://platform-api.productsup.io/platform/v2/sites/345"
                                       }
                                   ]
                               }
                           ]
                       }
                        """
                )));
        var siteTagsResponse = platformApiClient().getAllTags(123);
        assertThat(siteTagsResponse.tags())
            .first()
            .extracting(Tag::id, Tag::siteId, Tag::key, Tag::value, Tag::readOnly, tag -> tag.links().size())
            .containsExactly("123", "345", "siteTagKey", "siteTagValue", "0", 2);
    }
}
