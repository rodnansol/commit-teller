package org.rodnansol.committeller.core.action;

/**
 * Class describing the options for the file saving mechanism.
 *
 * @param saveAsFile sets if a file must be created.
 * @param extension  extension of the file.
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record FileOptions(
    boolean saveAsFile,
    FileExtension extension) {

    public enum FileExtension {
        MD(".md"),
        ADOC(".adoc");

        private final String rawExtension;

        FileExtension(String rawExtension) {
            this.rawExtension = rawExtension;
        }

        public String getRawExtension() {
            return rawExtension;
        }
    }

}
