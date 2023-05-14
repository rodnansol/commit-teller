package org.rodnansol.committeller.core.language.openai;

import io.quarkus.rest.client.reactive.NotBody;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * Client interface to the <a href="https://platform.openai.com/docs/api-reference">OpenAI's API</a>.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@RegisterRestClient
public interface OpenAIClient {

    @POST
    @Path("/v1/completions")
    @ClientHeaderParam(name = "Authorization", value = "Bearer {token}")
    CompletionResponse postCompletion(CompletionRequest request, @NotBody String token);
}
