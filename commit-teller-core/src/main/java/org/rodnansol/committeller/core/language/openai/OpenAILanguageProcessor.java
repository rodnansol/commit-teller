package org.rodnansol.committeller.core.language.openai;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.rodnansol.committeller.core.language.LanguageProcessor;
import org.rodnansol.committeller.core.language.LanguageProcessorException;
import org.rodnansol.committeller.core.language.ProcessPromptCommand;
import org.rodnansol.committeller.core.language.ProcessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class OpenAILanguageProcessor implements LanguageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenAILanguageProcessor.class);
    private final OpenAIProperties openAIProperties;
    private final OpenAIClient openAIClient;

    public OpenAILanguageProcessor(OpenAIProperties openAIProperties, @RestClient OpenAIClient openAIClient) {
        this.openAIProperties = openAIProperties;
        this.openAIClient = openAIClient;
    }

    @Override
    public ProcessResult processPrompt(ProcessPromptCommand processPromptCommand) {
        logPromptProperties();
        CompletionResponse completionResponse = openAIClient.postCompletion(new CompletionRequest(processPromptCommand.prompt(), openAIProperties.model(), openAIProperties.temperature(), openAIProperties.maxToken()), openAIProperties.apiKey());

        List<CompletionResponse.Completion> choices = completionResponse.choices();
        if (choices == null || choices.isEmpty()) {
            throw new LanguageProcessorException("No choices are returned, unable to return result");
        }
        CompletionResponse.Completion completionChoice = choices.get(0);
        return new ProcessResult(completionChoice.text());
    }

    private void logPromptProperties() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating prompt with the following attributes. Model:[{}] - Temperature:[{}] - Max Tokes:[{}]", openAIProperties.model(), openAIProperties.temperature(), openAIProperties.maxToken());
        }
    }
}
