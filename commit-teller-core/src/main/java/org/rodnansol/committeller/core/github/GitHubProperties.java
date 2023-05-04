package org.rodnansol.committeller.core.github;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "commit-teller.github")
public interface GitHubProperties {

    /**
     * Returns the GitHub token.
     *
     */
    String token();

    /**
     *
     * @return
     */
    String user();


    String repository();
}
