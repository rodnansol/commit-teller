package org.rodnansol.committeller.core.language;

/**
 * Interface that groups together the methdos for the language processor.
 * <p>
 * A language processor must be able to create results based on incoming prompts.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public interface LanguageProcessor {

    /**
     * Processes the incoming command.
     */
    ProcessResult processPrompt(ProcessPromptCommand processPromptCommand);
}
