package com.github.shinusuresh.productsup.client.client;

import com.github.shinusuresh.productsup.client.domain.project.Projects;
import com.github.shinusuresh.productsup.client.domain.sites.Sites;
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

    @GetExchange("/projects/{projectId}/sites")
    Sites getSites(@PathVariable(value = "projectId") final String projectId);

}
