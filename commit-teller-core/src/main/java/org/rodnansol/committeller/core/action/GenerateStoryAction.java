package org.rodnansol.committeller.core.action;

import jakarta.enterprise.context.ApplicationScoped;
import org.rodnansol.committeller.core.github.GitHubService;
import org.rodnansol.committeller.core.github.PullRequestSummary;
import org.rodnansol.committeller.core.language.LanguageProcessor;
import org.rodnansol.committeller.core.language.ProcessPromptCommand;
import org.rodnansol.committeller.core.language.ProcessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ApplicationScoped
public class GenerateStoryAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateStoryAction.class);
    private final GitHubService gitHubService;
    private final LanguageProcessor languageProcessor;
    private final DocumentationWriter documentationWriter;
    private final StoryProperties storyProperties;

    public GenerateStoryAction(GitHubService gitHubService,
                               LanguageProcessor languageProcessor,
                               DocumentationWriter documentationWriter,
                               StoryProperties storyProperties) {
        this.gitHubService = gitHubService;
        this.languageProcessor = languageProcessor;
        this.documentationWriter = documentationWriter;
        this.storyProperties = storyProperties;
    }

    /**
     * Generates the story for the given pull request and posts it as a comment.
     */
    public void generateStory(GenerateStoryCommand command) {
        LOGGER.info("Creating story based on command:[{}]", command);
        Objects.requireNonNull(command, "command is NULL");
        PullRequestSummary pullRequestSummary = gitHubService.getCommitsByPullRequestNumber(command.owner(), command.repository(), command.issueNumber());
        ProcessResult processResult = generateStory(pullRequestSummary);
        postAsComment(command, processResult);
        writeToFile(command, pullRequestSummary, processResult);
    }

    private void writeToFile(GenerateStoryCommand command, PullRequestSummary pullRequestSummary, ProcessResult processResult) {
        if (command.fileOptions().saveAsFile()) {
            String fileName = getFileName(command, pullRequestSummary);
            LOGGER.info("Writing result to file named:[{}]", fileName);
            documentationWriter.writePullRequestStoryIntoFile(fileName, command.fileOptions().extension(), pullRequestSummary, processResult);
        }
    }

    private String getFileName(GenerateStoryCommand command, PullRequestSummary pullRequestSummary) {
        return "#%d - %s%s".formatted(pullRequestSummary.pullRequestNumber(), pullRequestSummary.name(), command.fileOptions().extension().getRawExtension());
    }

    private void postAsComment(GenerateStoryCommand command, ProcessResult processResult) {
        if (command.commentOptions().postAsComment()) {
            LOGGER.info("Posting result as comment...");
            gitHubService.createComment(command.owner(), command.repository(), command.issueNumber(), processResult.resultContent());
        } else {
            LOGGER.info("No comment will be post, did you intentionally disable the comment creation?");
        }
    }

    private ProcessResult generateStory(PullRequestSummary pullRequestSummary) {
        StringBuilder basePrompt = new StringBuilder(storyProperties.template())
            .append(storyProperties.characteristics())
            .append("\n")
            .append("Commits:\n");
        pullRequestSummary.commits()
            .forEach(commit -> {
                basePrompt.append("Author:").append(commit.author()).append("\n");
                basePrompt.append(commit.message()).append("\n\n");
            });
        return languageProcessor.processPrompt(new ProcessPromptCommand(basePrompt.toString()));
    }


}
