package org.rodnansol.committeller.core.language;

/**
 * Exception to be thrown when the language processor required properties are not configured.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class LanguageProcessorPropertyValidationException extends RuntimeException {

    public LanguageProcessorPropertyValidationException(String message) {
        super(message);
    }
}
