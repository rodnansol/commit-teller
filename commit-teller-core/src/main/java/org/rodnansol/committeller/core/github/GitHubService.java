package org.rodnansol.committeller.core.github;

import com.jcabi.github.Commit;
import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Issue;
import com.jcabi.github.Pull;
import jakarta.enterprise.context.Dependent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Dependent
public class GitHubService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubService.class);


    private final GitHubProperties gitHubProperties;
    private final Github github;

    public GitHubService(GitHubProperties gitHubProperties, Github github) {
        this.gitHubProperties = gitHubProperties;
        this.github = github;
    }

    public List<GitCommit> getCommitsByPullRequestNumber(int pullRequestNumber) {
        try {
            Iterable<Commit> commits = getPullRequest(pullRequestNumber)
                .commits();
            return StreamSupport.stream(commits.spliterator(), false)
                .map(this::extractCommit)
                .filter(Objects::nonNull)
                .toList();
        } catch (IOException e) {
            throw new GitHubException("Error during collecting commits for pull request:" + pullRequestNumber, e);
        }
    }

    public void createComment(int issueNumber, String comment) {
        Issue issue = getIssue(issueNumber);
        try {
            issue.comments()
                .post(comment);
        } catch (IOException e) {
            throw new GitHubException("Error during creating a comment for issue:" + issueNumber, e);
        }
    }

    private Pull getPullRequest(int pullRequestNumber) {
        return github.repos()
            .get(new Coordinates.Simple(gitHubProperties.user(), gitHubProperties.repository()))
            .pulls()
            .get(pullRequestNumber);
    }

    private Issue getIssue(int issueNumber) {
        return github.repos()
            .get(new Coordinates.Simple(gitHubProperties.user(), gitHubProperties.repository()))
            .issues()
            .get(issueNumber);
    }

    private GitCommit extractCommit(Commit cmt) {
        try {
            JsonObject json = cmt.json();
            return new GitCommit(json.get("author").asJsonObject().get("name").toString(), json.get("message").toString());
        } catch (IOException e) {
            LOGGER.error("Error during extracting commit information, returning null", e);
            return null;
        }
    }

}
