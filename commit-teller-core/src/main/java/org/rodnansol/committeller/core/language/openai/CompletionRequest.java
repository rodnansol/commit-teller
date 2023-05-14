package org.rodnansol.committeller.core.language.openai;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class describing an OpenAI completion request.
 *
 * @param prompt
 * @param model
 * @param temperature
 * @param maxTokens
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record CompletionRequest(
    String prompt,
    String model,
    double temperature,
    @JsonProperty("max_tokens")
    int maxTokens
) {
}
