package org.rodnansol.committeller.core.github;

/**
 * Exception to be thrown when an error occurs during any GitHub API interaction.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class GitHubException extends RuntimeException {

    public GitHubException(String message) {
        super(message);
    }

    public GitHubException(String message, Throwable cause) {
        super(message, cause);
    }
}
