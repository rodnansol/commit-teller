package org.rodnansol.committeller.core.github;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;

@ApplicationScoped
public class GitHubConfiguration {

    @Produces
    @ApplicationScoped
    public GitHub github(GitHubProperties gitHubProperties) throws IOException {
        return new GitHubBuilder().withOAuthToken(gitHubProperties.token())
            .build();
    }

}
