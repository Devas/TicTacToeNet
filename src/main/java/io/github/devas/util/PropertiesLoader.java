package io.github.devas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for loading properties.
 */
public class PropertiesLoader {

    /**
     * Loads properties from file if no IOException.
     *
     * @param propertiesFilePathName path to properties
     * @return Properties object with loaded properties
     */
    public static Properties loadProperties(String propertiesFilePathName) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(new File(propertiesFilePathName))) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Couldn't load properties");
            e.printStackTrace();
        }
        return properties;
    }

}
