package org.rodnansol.committeller.core.language;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public interface LanguageProcessor {

    /**
     * Processes the incoming command.
     */
    ProcessResult processPrompt(ProcessPromptCommand processPromptCommand);
}
