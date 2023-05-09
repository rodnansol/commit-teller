package org.rodnansol.committeller.core.github;

import java.util.List;

/**
 * @param name    Name of the pull request.
 * @param commits associated commits.
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record PullRequestSummary(
    String name,
    int pullRequestNumber,
    List<GitCommit> commits) {
}
