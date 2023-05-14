package org.rodnansol.committeller.core.language;

/**
 * Record containing the result of the language processor.
 *
 * @param resultContent content created by the language processor.
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record ProcessResult(
    String resultContent) {

}
