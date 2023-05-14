package org.rodnansol.committeller.core.github;

import io.smallrye.config.ConfigMapping;

import java.util.Optional;

/**
 * Interface grouping together the OpenAPI related configuration keys.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ConfigMapping(prefix = "commit-teller.github")
interface GitHubProperties {

    /**
     * Returns the GitHub token.
     *
     */
    Optional<String> token();

}
