package org.rodnansol.commands;

import picocli.CommandLine;

import java.io.InputStream;
import java.util.Properties;

/**
 * Version provider.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class VersionProvider implements CommandLine.IVersionProvider {

    @Override
    public String[] getVersion() throws Exception {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("build.properties");
        if (resourceAsStream == null) {
            return new String[]{};
        }
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        return new String[]{properties.getProperty("build.version")};
    }
}
