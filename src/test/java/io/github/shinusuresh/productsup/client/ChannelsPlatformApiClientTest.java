package io.github.shinusuresh.productsup.client;

import io.github.shinusuresh.productsup.client.domain.sites.channels.Channel;
import io.github.shinusuresh.productsup.client.domain.sites.channels.history.History;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.mockserver.model.Parameter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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

    @Test
    void testChannelsHistory() {
        mockClient()
                .when(request()
                        .withMethod("GET")
                        .withPath("/sites/{id}/channels/{channelId}/history")
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
                                                       "channel_id": "1",
                                                       "name": "Google Merchant Center DE",
                                                       "export_name": "Google Merchant Center",
                                                       "links": [],
                                                       "history": [
                                                           {
                                                               "id": "333",
                                                               "site_id": "123",
                                                               "site_channel_id": "444",
                                                               "export_time": "2015-09-30 10:18:56",
                                                               "export_start": "2015-09-30 10:18:54",
                                                               "product_count": "18697",
                                                               "product_count_now": "20904",
                                                               "product_count_previous": "0",
                                                               "process_status": "0",
                                                               "pid": "560b96899e334",
                                                               "product_count_new": "0",
                                                               "product_count_modified": "0",
                                                               "product_count_deleted": "0",
                                                               "product_count_unchanged": "0",
                                                               "uploaded": "0"
                                                           }
                                                       ]
                                                  }
                                              ]
                                          }
                                        """
                        ));
        var channelHistory = platformApiClient().siteChannelHistory(123, 321);
        var histories = channelHistory.channels().stream().findFirst().get().histories();
        assertThatIterable(histories)
                .first()
                .returns("333", from(History::id))
                .returns("123", from(History::siteId))
                .returns("444", from(History::siteChannelId))
                .returns(Date.from(LocalDateTime.parse("2015-09-30 10:18:56",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toInstant(ZoneOffset.UTC)), from(History::exportTime))
                .returns(Date.from(LocalDateTime.parse("2015-09-30 10:18:54",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toInstant(ZoneOffset.UTC)), from(History::exportStart))
                .returns("18697", from(History::productCount))
                .returns("20904", from(History::productCountNow))
                .returns("0", from(History::productCountPrevious))
                .returns("0", from(History::processStatus))
                .returns("560b96899e334", from(History::pid))
                .returns("0", from(History::productCountNew))
                .returns("0", from(History::productCountModified))
                .returns("0", from(History::productCountDeleted))
                .returns("0", from(History::productCountUnChanged))
                .returns("0", from(History::uploaded))
        ;

    }

}
