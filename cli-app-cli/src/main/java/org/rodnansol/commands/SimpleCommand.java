package org.rodnansol.commands;

import jakarta.enterprise.context.Dependent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@Dependent
@CommandLine.Command(versionProvider = VersionProvider.class, name = "hello", description = "Hello command")
public class SimpleCommand implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCommand.class);

    @Override
    public void run() {
        LOGGER.info("Hello World");
    }
}
