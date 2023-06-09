package org.rodnansol.committeller.core.action;

/**
 * @param issueNumber
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record GenerateStoryCommand(
    String owner,
    String repository,
    int issueNumber,
    boolean includeCommitAuthorNames,
    CommentOptions commentOptions,
    FileOptions fileOptions) {

}
