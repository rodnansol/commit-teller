package org.rodnansol.commands;

import jakarta.enterprise.context.Dependent;
import org.rodnansol.committeller.core.action.CommentOptions;
import org.rodnansol.committeller.core.action.FileOptions;
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

    @CommandLine.Option(names = {"-o", "--owner"}, description = "Owner GitHub identifier.")
    private String owner;

    @CommandLine.Option(names = {"-r", "--repository"}, description = "Name of the repository owned by the owner option.")
    private String repository;

    @CommandLine.Option(names = "-pr", description = "Number of the pull request that should be analyzed and commented.")
    private int pullRequestNumber;

    @CommandLine.Option(names = "-pac", description = "Post as comment the result.")
    private boolean postAsComment = true;

    @CommandLine.Option(names = "-saf", description = "Save as file. The ")
    private boolean saveAsFile = false;

    @CommandLine.Option(names = {"-fe", "--file-extension"}, description = """
        Extension of the file.
        Supported formats: MD for Markdown, ADOC for AsciiDoc.
        Default is Markdown
        """)
    private FileOptions.FileExtension fileExtension = FileOptions.FileExtension.MD;

    public CreateStoryCommand(GenerateStoryAction generateStoryAction) {
        this.generateStoryAction = generateStoryAction;
    }

    @Override
    public void run() {
        CommentOptions commentOptions = new CommentOptions(postAsComment);
        FileOptions fileOptions = new FileOptions(saveAsFile, fileExtension);
        generateStoryAction.generateStory(new GenerateStoryCommand(owner, repository, pullRequestNumber, commentOptions, fileOptions));
    }
}


