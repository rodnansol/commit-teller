package org.rodnansol.committeller.core.github;

/**
 * Exception to be thrown when the GitHub properties are not properly set.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class GitHubPropertyValidationException extends RuntimeException {

    public GitHubPropertyValidationException(String message) {
        super(message);
    }

}
