package org.rodnansol.committeller.core.github;

/**
 * Class describing a git commit.
 *
 * @param author  author of the commit.
 * @param message message of the commit.
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record GitCommit(
    String author,
    String message) {

}
