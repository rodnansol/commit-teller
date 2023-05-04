package org.rodnansol.commands;

import jakarta.enterprise.context.Dependent;
import org.rodnansol.committeller.core.action.GenerateStoryAction;
import org.rodnansol.committeller.core.action.GenerateStoryCommand;
import picocli.CommandLine;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@Dependent
@CommandLine.Command(versionProvider = VersionProvider.class, name = "create-story", description = """
    Creates a story for a given issue/pull request and posts it as a comment.
    """)
public class CreateStoryCommand implements Runnable {

    private final GenerateStoryAction generateStoryAction;
    @CommandLine.Option(names = "-pr", description = "Number of the pull request that should be analyzed and commented.")
    private int pullRequestNumber;

    public CreateStoryCommand(GenerateStoryAction generateStoryAction) {
        this.generateStoryAction = generateStoryAction;
    }


    @Override
    public void run() {
        generateStoryAction.generateStory(new GenerateStoryCommand(pullRequestNumber));
    }
}
