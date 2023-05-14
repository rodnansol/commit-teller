package org.rodnansol.committeller.core.language.openai;

import java.util.List;

/**
 * Class describing an OpenAI comletion response.
 *
 * @param choices returned choices.
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record CompletionResponse(List<Completion> choices) {
    public record Completion(String text) {
    }
}
