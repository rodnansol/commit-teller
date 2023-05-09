package org.rodnansol.committeller.core.action;

/**
 * Exception to be thrown when an error occurs during the document generation.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class DocumentGenerationException extends RuntimeException {

    public DocumentGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
