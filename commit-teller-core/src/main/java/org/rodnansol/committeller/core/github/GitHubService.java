package org.rodnansol.committeller.core.github;

import jakarta.enterprise.context.Dependent;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHPullRequestCommitDetail;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Class handling interaction with the GitHub API.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@Dependent
public class GitHubService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubService.class);

    private final GitHub github;

    public GitHubService(GitHub github) {
        this.github = github;
    }

    /**
     * Returns the commits for a given pull request.
     *
     * @throws GitHubException if the commits can not be extracted from the pull request.
     */
    public PullRequestSummary getCommitsByPullRequestNumber(String owner, String repository, int pullRequestNumber) {
        try {
            LOGGER.debug("Listing commits for pull request:[{}]", pullRequestNumber);
            GHPullRequest pullRequest = getPullRequest(owner, repository, pullRequestNumber);
            List<GitCommit> gitCommits = extractCommitsFromPullRequest(pullRequest);
            return new PullRequestSummary(pullRequest.getTitle(), pullRequestNumber, gitCommits);
        } catch (IOException e) {
            throw new GitHubException("Error during collecting commits for pull request:" + pullRequestNumber, e);
        }
    }

    private List<GitCommit> extractCommitsFromPullRequest(GHPullRequest pullRequest) {
        return StreamSupport.stream(pullRequest.listCommits().spliterator(), false)
            .map(this::extractCommit)
            .toList();
    }

    /**
     * Creates a comment on a given issue.
     * <p>
     * In the GitHub API there are differences between the <b>Issues</b> and <b>Pull Request</b> but in case of standard comments,
     * the logic/endpoint is the same.
     *
     * @throws GitHubException if the comment can not be created for the issue.
     */
    public void createComment(String owner, String repository, int issueNumber, String comment) {
        LOGGER.debug("Creating comment on issue/pull with number:[{}]", issueNumber);
        try {
            getIssue(owner, repository, issueNumber).comment(comment);
        } catch (IOException e) {
            throw new GitHubException("Error during creating a comment for issue:" + issueNumber, e);
        }
    }

    private GHPullRequest getPullRequest(String owner, String repository, int pullRequestNumber) throws IOException {
        return github.getRepository(owner + "/" + repository)
            .getPullRequest(pullRequestNumber);
    }

    private GHIssue getIssue(String owner, String repository, int issueNumber) throws IOException {
        return github.getRepository(owner + "/" + repository)
            .getIssue(issueNumber);
    }

    private GitCommit extractCommit(GHPullRequestCommitDetail commitDetail) {
        GHPullRequestCommitDetail.Commit commit = commitDetail.getCommit();
        return new GitCommit(commit.getAuthor().getName(), commit.getMessage());
    }
}
