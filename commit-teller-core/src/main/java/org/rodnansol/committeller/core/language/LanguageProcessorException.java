package org.rodnansol.committeller.core.language;

/**
 * Exception to be thrown when an error occurs during an execution in the language processor.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class LanguageProcessorException extends RuntimeException {

    public LanguageProcessorException(String message) {
        super(message);
    }

}
