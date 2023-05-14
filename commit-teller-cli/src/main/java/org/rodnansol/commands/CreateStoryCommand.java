package org.rodnansol.commands;

import jakarta.enterprise.context.Dependent;
import org.rodnansol.committeller.core.action.CommentOptions;
import org.rodnansol.committeller.core.action.FileOptions;
import org.rodnansol.committeller.core.action.GenerateStoryAction;
import org.rodnansol.committeller.core.action.GenerateStoryCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@Dependent
@CommandLine.Command(versionProvider = VersionProvider.class,
    name = "create-story",
    description = """
        Creates a story for a given issue/pull request and posts it as a comment.
        """)
class CreateStoryCommand implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateStoryCommand.class);

    private final GenerateStoryAction generateStoryAction;

    /**
     * Repository owner's username.
     * <p>
     * It can definitely be an organization name too.
     *
     * @since 0.1.0
     */
    @CommandLine.Option(names = {"-o", "--owner"},
        description = """
            Owner GitHub identifier.
            It can be a standalone user or an organization.
            """, required = true)
    String owner;

    /**
     * Identifier of the repository.
     *
     * @since 0.1.0
     */
    @CommandLine.Option(names = {"-r", "--repository"}, description = "Name of the repository owned by the owner option.", required = true)
    String repository;

    /**
     * Pull request number.
     *
     * @since 0.1.0
     */
    @CommandLine.Option(names = "-pr", description = "Number of the pull request that should be analyzed and commented.", required = true)
    int pullRequestNumber;

    /**
     * If the created story should be posted as a comment to the associated pull request.
     * <p>
     * Enabled by default.
     *
     * @since 0.1.0
     */
    @CommandLine.Option(names = "-pac", description = "Post as comment the result.")
    boolean postAsComment = true;

    /**
     * If the created story should be saved to a file as well.
     *
     * @since 0.1.0
     */
    @CommandLine.Option(names = "-saf", description = "Save as file. The ")
    boolean saveAsFile = false;

    /**
     * File extension.
     * <p>
     * It can be Markdown or AsciiDoc. The templates are really simple for them.
     *
     * @since 0.1.0
     */
    @CommandLine.Option(names = {"-fe", "--file-extension"}, description = """
        Extension of the file.
        Supported formats: MD for Markdown, ADOC for AsciiDoc.
        Default is Markdown
        """)
    FileOptions.FileExtension fileExtension = FileOptions.FileExtension.MD;

    /**
     * If the created story should be saved to a file as well.
     *
     * @since 0.1.0
     */
    @CommandLine.Option(names = {"-ican", "--include-commit-author-names"}, description = """
        If the commit author names should be included in the prompt for the language processor based text generation.
         """)
    boolean includeCommitAuthorNames = false;

    public CreateStoryCommand(GenerateStoryAction generateStoryAction) {
        this.generateStoryAction = generateStoryAction;
    }

    @Override
    public void run() {
        CommentOptions commentOptions = new CommentOptions(postAsComment);
        FileOptions fileOptions = new FileOptions(saveAsFile, fileExtension);
        try {
            generateStoryAction.generateStory(new GenerateStoryCommand(owner, repository, pullRequestNumber, includeCommitAuthorNames, commentOptions, fileOptions));
        } catch (Exception e) {
            LOGGER.error("\u001B[31m[ERROR]: {}", e.getMessage());
        }
    }
}


