package org.rodnansol.committeller.core.language.openai;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import java.time.Duration;

/**
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

    /**
     * Returns the client timeout.
     * <p>
     * By default, it is 30 seconds.
     *
     * @since 0.1.0
     */
    default Duration clientTimeout() {
        return Duration.ofSeconds(30);
    }

}
