package org.rodnansol.committeller.core.language;

/**
 * Class representing a command that contains the prompt and additional configurations.
 *
 * @param prompt prompt containing the message that should be processed by the language processor.
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record ProcessPromptCommand(
    String prompt) {

}
