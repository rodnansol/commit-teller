package org.rodnansol.committeller.core.language.openai;

import java.util.List;

public record CompletionResponse(List<Completion> choices) {
    public record Completion(String text) {
    }
}
