package org.rodnansol.committeller.core.action;

import jakarta.enterprise.context.Dependent;
import org.rodnansol.committeller.core.github.PullRequestSummary;
import org.rodnansol.committeller.core.language.ProcessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class dealing with the documentation creation.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@Dependent
class DocumentationWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentationWriter.class);

    private static final String MARKDOWN_TEMPLATE = """
        ## %s
        %s
        """;

    private static final String ASCIIDOC_TEMPLATE = """
        == %s
        %s
        """;

    /**
     * Writes the results to a document, the document's extension is based on the incoming parameter.
     *
     * @throws DocumentGenerationException when an error occurs during any file operation.
     */
    void writePullRequestStoryIntoFile(String fileName, FileOptions.FileExtension extension,
                                       PullRequestSummary pullRequestSummary, ProcessResult processResult) {
        LOGGER.debug("Writing content to file:[{}] with extension:[{}]", fileName, extension);
        String templateToBeUsed = switch (extension) {
            case MD -> MARKDOWN_TEMPLATE;
            case ADOC -> ASCIIDOC_TEMPLATE;
        };
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(templateToBeUsed.formatted(pullRequestSummary.name(), processResult.resultContent()));
        } catch (IOException e) {
            throw new DocumentGenerationException("Error during writing file with name:[" + fileName + "]", e);
        }
    }
}

