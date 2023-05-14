package org.rodnansol.committeller.core.action;

import io.smallrye.config.ConfigMapping;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ConfigMapping(prefix = "commit-teller.story")
public interface StoryProperties {

    /**
     * Returns the template that will be used by the language processor.
     *
     * @since 0.1.0
     */
    String template();

    /**
     * Returns the characteristics of the story.
     *
     * @since 0.1.0
     */
    String characteristics();
}
