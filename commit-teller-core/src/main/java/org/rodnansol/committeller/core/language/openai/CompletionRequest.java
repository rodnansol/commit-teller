package org.rodnansol.committeller.core.language.openai;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CompletionRequest(
    String prompt,
    String model,
    double temperature,
    @JsonProperty("max_tokens")
    int maxTokens
) {
}
