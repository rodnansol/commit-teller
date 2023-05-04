package org.rodnansol.committeller.core.github;


import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class GitHubConfiguration {

    @Produces
    @ApplicationScoped
    public Github github(GitHubProperties gitHubProperties) {
        return new RtGithub(gitHubProperties.token());
    }

}
