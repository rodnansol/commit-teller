package org.rodnansol.commands;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@TopCommand
@CommandLine.Command(
    versionProvider = VersionProvider.class,
    description = "Entry command. Please check the instructions.",
    mixinStandardHelpOptions = true,
    subcommands = {CreateStoryCommand.class})
public class EntryCommand {

}

