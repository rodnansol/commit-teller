package org.rodnansol.committeller.core.language.openai;

import jakarta.enterprise.context.ApplicationScoped;
import org.rodnansol.committeller.core.language.LanguageProcessorPropertyValidationException;
import org.rodnansol.committeller.core.language.LanguageProcessorPropertyValidator;

/**
 * OpenAI property validator.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ApplicationScoped
class OpenAIPropertyValidator implements LanguageProcessorPropertyValidator {

    private final OpenAIProperties openAIProperties;

    public OpenAIPropertyValidator(OpenAIProperties openAIProperties) {
        this.openAIProperties = openAIProperties;
    }

    @Override
    public void validateProperties() throws LanguageProcessorPropertyValidationException {
        if (openAIProperties.apiKey().isEmpty()) {
            throw new LanguageProcessorPropertyValidationException("OpenAI API key is missing. Please set the following environment variable:[COMMIT_TELLER_OPENAI_API_KEY]");
        }
    }
}
