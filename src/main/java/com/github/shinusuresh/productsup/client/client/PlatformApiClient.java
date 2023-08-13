package com.github.shinusuresh.productsup.client.client;

import com.github.shinusuresh.productsup.client.domain.project.Projects;
import com.github.shinusuresh.productsup.client.domain.sites.Sites;
import com.github.shinusuresh.productsup.client.domain.sites.errors.SiteErrors;
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

    @GetExchange("/sites/{id}")
    Sites getSitesById(@PathVariable(value = "id") Integer id);

    @GetExchange("/sites/errors/{id}")
    SiteErrors siteErrors(@PathVariable(value = "id") Integer id);
}
