package org.rodnansol.committeller.core.language.openai;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

/**
 * Interface grouping together the OpenAPI related configuration keys.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ConfigMapping(prefix = "commit-teller.openai")
interface OpenAIProperties {

    /**
     * Returns the OpenAI API key.
     *
     * @since 0.1.0
     */
    String apiKey();

    /**
     * Returns the model to be used.
     *
     * @since 0.1.0
     */
    String model();

    /**
     * Returns the temperature.
     *
     * @since 0.1.0
     */
    @WithDefault("0.2")
    double temperature();

    /**
     * Returns the maximum token to be returned by the API.
     *
     * @since 0.1.0
     */
    @WithDefault("1024")
    int maxToken();

}
