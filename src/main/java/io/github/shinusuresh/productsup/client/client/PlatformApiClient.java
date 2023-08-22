package io.github.shinusuresh.productsup.client.client;

import io.github.shinusuresh.productsup.client.domain.process.ProcessRequest;
import io.github.shinusuresh.productsup.client.domain.process.ProcessResponse;
import io.github.shinusuresh.productsup.client.domain.product.Products;
import io.github.shinusuresh.productsup.client.domain.project.Projects;
import io.github.shinusuresh.productsup.client.domain.sites.Sites;
import io.github.shinusuresh.productsup.client.domain.sites.channels.Channels;
import io.github.shinusuresh.productsup.client.domain.sites.errors.SiteErrors;
import io.github.shinusuresh.productsup.client.domain.sites.history.ImportHistory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * Client for Platform API's <a href="https://api-docs.productsup.io/#platform-api">PLatform API's</a>
 */
public interface PlatformApiClient {

    /**
     * List all projects in your account.
     *
     * @return Projects
     */
    @GetExchange("/projects")
    Projects getProjects();


    /**
     * Get sites by project id
     *
     * @param projectId - Project id
     * @return Sites
     */
    @GetExchange("/projects/{projectId}/sites")
    Sites getSitesByProjectId(@PathVariable(value = "projectId") final String projectId);

    /**
     * Get all sites
     *
     * @return Sites
     */
    @GetExchange("/sites")
    Sites getSites();

    /**
     * Gets sites by tag.
     *
     * @param tag Format of parameter is tagName:tagValue
     * @return Sites
     */
    @GetExchange("/sites/{tag}")
    Sites getSitesByTags(@PathVariable(value = "tag") final String tag);


    /**
     * Get sites by id.
     *
     * @param id - Site id
     * @return Sites
     */
    @GetExchange("/sites/{id}")
    Sites getSitesById(@PathVariable(value = "id") Integer id);

    /**
     * Get errors in a site.
     *
     * @param id - Site id
     * @return SiteErrors
     */
    @GetExchange("/sites/errors/{id}")
    SiteErrors siteErrors(@PathVariable(value = "id") Integer id);


    /**
     * Gets sites import history.
     *
     * @param id - Site id
     * @return - {@link ImportHistory}
     */
    @GetExchange("/sites/{id}/importhistory")
    ImportHistory siteImportHistory(@PathVariable(value = "id") Integer id);

    /**
     * Get Channels configured for a site
     *
     * @param id - Site id
     * @return Channels
     */
    @GetExchange("/sites/{id}/channels")
    Channels siteChannels(@PathVariable(value = "id") Integer id);

    /**
     * Get channels by site and channel id
     *
     * @param id        - Site id
     * @param channelId - Channel id
     * @return Channels
     */
    @GetExchange("/sites/{id}/channels/{channelId}")
    Channels siteChannelsById(@PathVariable(value = "id") Integer id, @PathVariable(value = "channelId") Integer channelId);

    /**
     * Get history of a channel.
     *
     * @param id        - Site id
     * @param channelId - Channel id
     * @return Channels
     */
    @GetExchange("/sites/{id}/channels/{channelId}/history")
    Channels siteChannelHistory(@PathVariable(value = "id") Integer id, @PathVariable(value = "channelId") Integer channelId);

    /**
     * Gets products.
     *
     * @param id    - Site Id
     * @param stage - Stage names. Valid stages are import, intermediate, export, channel.
     * @return Products
     */
    @GetExchange("/site/{id}/stage/{stageName}")
    Products getProducts(@PathVariable(value = "id") Integer id, @PathVariable(value = "stageName") String stage,
                         @RequestParam(value = "limit", defaultValue = "100") Integer limit,
                         @RequestParam(value = "offset", defaultValue = "0") Integer offset);


    /**
     * Process data.
     *
     * @param id - Site id
     * @return ProcessResponse
     */
    @PostExchange("/process/{id}")
    ProcessResponse process(@PathVariable(value = "id") Integer id, @RequestBody ProcessRequest processRequest);
}
