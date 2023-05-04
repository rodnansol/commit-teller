package org.rodnansol.committeller.core.github;

/**
 * @param author
 * @param message
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record GitCommit(
    String author,
    String message) {

}
