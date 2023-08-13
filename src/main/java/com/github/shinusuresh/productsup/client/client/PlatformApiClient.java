package com.github.shinusuresh.productsup.client.client;

import com.github.shinusuresh.productsup.client.domain.project.Projects;
import com.github.shinusuresh.productsup.client.domain.sites.Sites;
import com.github.shinusuresh.productsup.client.domain.sites.errors.SiteErrors;
import com.github.shinusuresh.productsup.client.domain.sites.history.ImportHistory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

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
}
