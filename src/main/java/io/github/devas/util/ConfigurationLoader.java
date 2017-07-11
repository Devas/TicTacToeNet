package io.github.devas.util;

import java.io.PrintStream;
import java.util.Properties;

public class ConfigurationLoader {

    private final String configFileName = "src/main/resources/config.properties";
    private final Properties properties = PropertiesLoader.loadProperties(configFileName);
    private PrintStream printStream = System.out;

    public ConfigurationLoader() {
        initOutputMessagesTarget();
    }

    public String get(String key) {
        return properties.get(key).toString();
    }

    public void print(String message) {
        printStream.print(message);
    }

    public void println(String message) {
        printStream.println(message);
    }

    public void println() {
        printStream.println();
    }

    /**
     * Add new targets here and also add options to .properties file
     */
    private void initOutputMessagesTarget() {
        switch (get("target")) {
            case "out":
                printStream = System.out;
                break;
            case "err":
                printStream = System.err;
                break;
            default:
                printStream = System.out;
        }
    }

}
