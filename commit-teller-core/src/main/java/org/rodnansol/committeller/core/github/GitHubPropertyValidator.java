package org.rodnansol.committeller.core.github;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Class that validates GitHub properties.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ApplicationScoped
public class GitHubPropertyValidator {
    private final GitHubProperties gitHubProperties;

    public GitHubPropertyValidator(GitHubProperties gitHubProperties) {
        this.gitHubProperties = gitHubProperties;
    }

    public void validateProperties() throws GitHubPropertyValidationException {
        if (gitHubProperties.token().isEmpty()) {
            throw new GitHubPropertyValidationException("GitHub token is missing, please set the following environment variable:[COMMIT_TELLER_GITHUB_TOKEN]");
        }
    }
}
