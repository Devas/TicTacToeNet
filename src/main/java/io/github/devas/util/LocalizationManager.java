package io.github.devas.util;

import java.util.Properties;

public class LocalizationManager {

    private final String resourcesPath;
    private final String localizationName;
    private final String localizationFileName;
    private final Properties properties;

    public LocalizationManager(String localizationName) {
        this.resourcesPath = "src/main/resources/";
        this.localizationName = localizationName.toUpperCase();
        this.localizationFileName = "localization" + this.localizationName + ".properties";
        this.properties = PropertiesLoader.loadProperties(resourcesPath + localizationFileName);
    }

    public String get(String key) {
        return properties.get(key).toString();
    }

}
