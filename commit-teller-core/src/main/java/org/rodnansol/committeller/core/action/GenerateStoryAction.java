package org.rodnansol.committeller.core.action;

import jakarta.enterprise.context.ApplicationScoped;
import org.rodnansol.committeller.core.github.GitCommit;
import org.rodnansol.committeller.core.github.GitHubService;
import org.rodnansol.committeller.core.language.LanguageProcessor;
import org.rodnansol.committeller.core.language.ProcessPromptCommand;
import org.rodnansol.committeller.core.language.ProcessResult;

import java.util.List;
import java.util.Objects;


/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ApplicationScoped
public class GenerateStoryAction {

    private final GitHubService gitHubService;
    private final LanguageProcessor languageProcessor;
    private final StoryProperties storyProperties;

    public GenerateStoryAction(GitHubService gitHubService, LanguageProcessor languageProcessor, StoryProperties storyProperties) {
        this.gitHubService = gitHubService;
        this.languageProcessor = languageProcessor;
        this.storyProperties = storyProperties;
    }

    public void generateStory(GenerateStoryCommand generateStoryCommand) {
        Objects.requireNonNull(generateStoryCommand, "generateStoryCommand is NULL");
        List<GitCommit> commits = gitHubService.getCommitsByPullRequestNumber(generateStoryCommand.issueNumber());
        StringBuilder basePrompt = new StringBuilder(storyProperties.template());
        commits.forEach(commit -> {
            basePrompt.append("Author:").append(commit.author()).append("\n");
            basePrompt.append("-").append(commit.message()).append("\n");
        });
        ProcessResult processResult = languageProcessor.processPrompt(new ProcessPromptCommand(basePrompt.toString()));
        gitHubService.createComment(generateStoryCommand.issueNumber(), processResult.resultContent());
    }


}
