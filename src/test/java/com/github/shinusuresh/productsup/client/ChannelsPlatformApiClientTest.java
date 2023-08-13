package com.github.shinusuresh.productsup.client;

import com.github.shinusuresh.productsup.client.domain.sites.channels.Channel;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.mockserver.model.Parameter;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class ChannelsPlatformApiClientTest extends BasePlatformApiClient {

    public ChannelsPlatformApiClientTest(MockServerClient mockServerClient) {
        super(mockServerClient);
    }

    @Test
    void testChannelsById() {
        mockClient()
                .when(request()
                        .withMethod("GET")
                        .withPath("/sites/{id}/channels/{channelId}")
                        .withPathParameters(new Parameter("id", "[0-9]+"),
                                new Parameter("channelId", "[0-9]+")))
                .respond(response()
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(
                                """
                                        {
                                              "success": true,
                                              "Channels": [
                                                  {
                                                      "id": "321",
                                                      "site_id": "123",
                                                      "channel_id": "111",
                                                      "name": "Criteo DE",
                                                      "export_name": "Criteo",
                                                      "links": [
                                                        {
                                                            "self": "http://platform-api.productsup.io/platform/v2/channels/123"
                                                        },
                                                        {
                                                            "site": "http://platform-api.productsup.com/platform/v2/sites/321"
                                                        }
                                                      ]
                                                  }
                                              ]
                                          }
                                        """
                        ));
        var channels = platformApiClient().siteChannelsById(123, 321);
        assertThatIterable(channels.channels())
                .first()
                .returns("321", from(Channel::id))
                .returns("123", from(Channel::siteId))
                .returns("111", from(Channel::channelId))
                .returns("Criteo DE", from(Channel::name))
                .returns("Criteo", from(Channel::exportName))
                .returns(List.of(
                        Map.of("self", "http://platform-api.productsup.io/platform/v2/channels/123"),
                        Map.of("site", "http://platform-api.productsup.com/platform/v2/sites/321")
                ), from(Channel::links));

    }

    @Test
    void testChannels() {
        mockClient()
                .when(request()
                        .withMethod("GET")
                        .withPath("/sites/{id}/channels")
                        .withPathParameter("id", "[0-9]+"))
                .respond(response()
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(
                                """
                                        {
                                              "success": true,
                                              "Channels": [
                                                  {
                                                      "id": "321",
                                                      "site_id": "123",
                                                      "channel_id": "111",
                                                      "name": "Criteo DE",
                                                      "export_name": "Criteo",
                                                      "links": [
                                                        {
                                                            "self": "http://platform-api.productsup.io/platform/v2/channels/123"
                                                        },
                                                        {
                                                            "site": "http://platform-api.productsup.com/platform/v2/sites/321"
                                                        }
                                                      ]
                                                  },
                                                  {
                                                      "id": "3211",
                                                      "site_id": "1231",
                                                      "channel_id": "1111",
                                                      "name": "Criteo DE 1",
                                                      "export_name": "Criteo 1",
                                                      "links": [
                                                        {
                                                            "self": "http://platform-api.productsup.io/platform/v2/channels/1231"
                                                        },
                                                        {
                                                            "site": "http://platform-api.productsup.com/platform/v2/sites/3211"
                                                        }
                                                      ]
                                                  }
                                              ]
                                          }
                                        """
                        ));
        var channels = platformApiClient().siteChannels(123);
        assertThat(channels.channels()).hasSize(2);
        assertThatIterable(channels.channels())
                .first()
                .returns("321", from(Channel::id))
                .returns("123", from(Channel::siteId))
                .returns("111", from(Channel::channelId))
                .returns("Criteo DE", from(Channel::name))
                .returns("Criteo", from(Channel::exportName))
                .returns(List.of(
                        Map.of("self", "http://platform-api.productsup.io/platform/v2/channels/123"),
                        Map.of("site", "http://platform-api.productsup.com/platform/v2/sites/321")
                ), from(Channel::links));

    }

}
