package org.rodnansol.committeller.core.language.openai;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import jakarta.enterprise.context.ApplicationScoped;
import org.rodnansol.committeller.core.language.LanguageProcessor;
import org.rodnansol.committeller.core.language.ProcessPromptCommand;
import org.rodnansol.committeller.core.language.ProcessResult;

import java.time.Duration;

@ApplicationScoped
public class OpenAILanguageProcessor implements LanguageProcessor {

    private final OpenAIProperties openAIProperties;

    public OpenAILanguageProcessor(OpenAIProperties openAIProperties) {
        this.openAIProperties = openAIProperties;
    }

    @Override
    public ProcessResult processPrompt(ProcessPromptCommand processPromptCommand) {
        OpenAiService openAiService = new OpenAiService(openAIProperties.apiKey(), Duration.ofSeconds(60));
        CompletionResult completion = openAiService.createCompletion(CompletionRequest.builder()
            .model(openAIProperties.model())
            .prompt(processPromptCommand.prompt())
            .temperature(openAIProperties.temperature())
            .maxTokens(openAIProperties.maxToken())
            .build());

        CompletionChoice completionChoice = completion.getChoices().get(0);
        return new ProcessResult(completionChoice.getText());
    }
}
