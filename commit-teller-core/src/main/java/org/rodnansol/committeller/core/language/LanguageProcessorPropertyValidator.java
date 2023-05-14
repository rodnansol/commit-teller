package org.rodnansol.committeller.core.language;

/**
 * Interface to collect language processor associated property validation.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public interface LanguageProcessorPropertyValidator {

    /**
     * Validates the properties.
     */
    void validateProperties() throws LanguageProcessorPropertyValidationException;

}
