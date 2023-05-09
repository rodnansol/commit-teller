package org.rodnansol.committeller.core.language.openai;

import io.quarkus.rest.client.reactive.NotBody;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface OpenAIClient {

    @POST
    @Path("/v1/completions")
    @ClientHeaderParam(name = "Authorization", value = "Bearer {token}")
    CompletionResponse postCompletion(CompletionRequest request, @NotBody String token);
}
