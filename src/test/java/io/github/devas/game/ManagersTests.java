package io.github.devas.game;

import io.github.devas.util.ConfigurationLoader;
import io.github.devas.util.LocalizationLoader;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class ManagersTests {

    public void testLoadingLocalization() {
        LocalizationLoader localizationLoader = new LocalizationLoader("ENG");
        assertEquals(localizationLoader.get("turn"), "\n*** Turn ");
    }

    public void testLoadingConfigurationAndGettingPlayerName() {
        ConfigurationLoader configurationLoader = new ConfigurationLoader();
        assertEquals(configurationLoader.get("playera"), "John");
    }

}
