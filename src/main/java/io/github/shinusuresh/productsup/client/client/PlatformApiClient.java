package io.github.shinusuresh.productsup.client.client;

import io.github.shinusuresh.productsup.client.domain.process.ProcessRequest;
import io.github.shinusuresh.productsup.client.domain.process.ProcessResponse;
import io.github.shinusuresh.productsup.client.domain.product.DeleteProductsResponse;
import io.github.shinusuresh.productsup.client.domain.product.Products;
import io.github.shinusuresh.productsup.client.domain.project.Projects;
import io.github.shinusuresh.productsup.client.domain.sites.Sites;
import io.github.shinusuresh.productsup.client.domain.sites.channels.Channels;
import io.github.shinusuresh.productsup.client.domain.sites.errors.SiteErrors;
import io.github.shinusuresh.productsup.client.domain.sites.history.ImportHistory;
import io.github.shinusuresh.productsup.client.domain.streams.attach.AttachStream;
import io.github.shinusuresh.productsup.client.domain.streams.attach.AttachStreamResponse;
import io.github.shinusuresh.productsup.client.domain.streams.list.ListStreamResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * Client for Platform API's <a href="https://api-docs.productsup.io/#platform-api">PLatform API's</a>
 */
public interface PlatformApiClient {

    /**
     * List all projects in your account.
     * <a href="https://api-docs.productsup.io/#platform-api-projects-get">Get all projects</a>
     *
     * @return Projects
     */
    @GetExchange("/projects")
    Projects getProjects();


    /**
     * Get sites by project id
     * <a href="https://api-docs.productsup.io/#platform-api-sites">Get sites by project id</a>
     *
     * @param projectId - Project id
     * @return Sites
     */
    @GetExchange("/projects/{projectId}/sites")
    Sites getSitesByProjectId(@PathVariable(value = "projectId") final String projectId);

    /**
     * Get all sites
     * <a href="https://api-docs.productsup.io/#platform-api-sites">Get all sites</a>
     *
     * @return Sites
     */
    @GetExchange("/sites")
    Sites getSites();

    /**
     * Gets sites by tag.
     * <a href="https://api-docs.productsup.io/#platform-api-sites-get">Get Sites by tag</a>
     *
     * @param tag Format of parameter is tagName:tagValue
     * @return Sites
     */
    @GetExchange("/sites/{tag}")
    Sites getSitesByTags(@PathVariable(value = "tag") final String tag);


    /**
     * Get sites by id.
     * <a href="https://api-docs.productsup.io/#platform-api-sites">Get Sites by id</a>
     *
     * @param id - Site id
     * @return Sites
     */
    @GetExchange("/sites/{id}")
    Sites getSitesById(@PathVariable(value = "id") Integer id);

    /**
     * Get errors in a site.
     * <a href="https://api-docs.productsup.io/#platform-api-site-errors">Site Errors</a>
     *
     * @param id - Site id
     * @return SiteErrors
     */
    @GetExchange("/sites/errors/{id}")
    SiteErrors siteErrors(@PathVariable(value = "id") Integer id);


    /**
     * Gets sites import history.
     * <a href="https://api-docs.productsup.io/#platform-api-import-history">Import History</a>
     *
     * @param id - Site id
     * @return - {@link ImportHistory}
     */
    @GetExchange("/sites/{id}/importhistory")
    ImportHistory siteImportHistory(@PathVariable(value = "id") Integer id);

    /**
     * Get Channels configured for a site
     * <a href="https://api-docs.productsup.io/#platform-api-channels-get">Get Channels</a>
     *
     * @param id - Site id
     * @return Channels
     */
    @GetExchange("/sites/{id}/channels")
    Channels siteChannels(@PathVariable(value = "id") Integer id);

    /**
     * Get channels by site and channel id
     * <a href="https://api-docs.productsup.io/#platform-api-channels-get">Get Channels</a>
     *
     * @param id        - Site id
     * @param channelId - Channel id
     * @return Channels
     */
    @GetExchange("/sites/{id}/channels/{channelId}")
    Channels siteChannelsById(@PathVariable(value = "id") Integer id, @PathVariable(value = "channelId") Integer channelId);

    /**
     * Get history of a channel.
     * <a href="https://api-docs.productsup.io/#platform-api-channel-history">Channel History</a>
     *
     * @param id        - Site id
     * @param channelId - Channel id
     * @return Channels
     */
    @GetExchange("/sites/{id}/channels/{channelId}/history")
    Channels siteChannelHistory(@PathVariable(value = "id") Integer id, @PathVariable(value = "channelId") Integer channelId);

    /**
     * Gets products.
     * <a href="https://api-docs.productsup.io/#platform-api-product-data-read-get">Get product data</a>
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
     * Start Process data for a site.
     * <a href="https://api-docs.productsup.io/#platform-api-process-data">Process data</a>
     *
     * @param id - Site id
     * @return ProcessResponse
     */
    @PostExchange("/process/{id}")
    ProcessResponse process(@PathVariable(value = "id") Integer id, @RequestBody ProcessRequest processRequest);

    /**
     * Get streams for a site.
     * <a href="https://api-docs.productsup.io/#platform-api-site-stream-data-sources-list-stream-datasources">
     * List stream datasources</a>
     *
     * @param id - Site id.
     * @return ListStreamResponse
     */
    @GetExchange("/sites/{id}/streams")
    ListStreamResponse listStreamsBySiteId(@PathVariable(value = "id") Integer id);

    /**
     * Attach a stream to site.
     * <a href="https://api-docs.productsup.io/#platform-api-site-stream-data-sources-create-stream-datasource">
     * Create stream datasource</a>
     */
    @PostExchange("/sites/{id}/streams")
    AttachStreamResponse createStreamDataSource(@PathVariable(value = "id") Integer id, @RequestBody AttachStream attachStreamRequest);

    /**
     * Delete all products.
     * <a href="https://api-docs.productsup.io/#platform-api-product-data-write-deleting">Delete all products</a>
     *
     * @param id - Site id or tag
     * @return DeleteProductsResponse
     */
    @DeleteExchange("/sites/{id}/products")
    DeleteProductsResponse deleteAllProducts(@PathVariable(value = "id") String id);
}
