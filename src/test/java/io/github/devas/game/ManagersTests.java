package io.github.devas.game;

import io.github.devas.util.ConfigurationManager;
import io.github.devas.util.LocalizationManager;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class ManagersTests {

    public void testLoadingLocalization() {
        LocalizationManager localizationManager = new LocalizationManager("ENG");
        assertEquals(localizationManager.get("turn"), "\n*** Turn ");
    }

    public void testLoadingConfigurationAndGettingPlayerName() {
        ConfigurationManager configurationManager = new ConfigurationManager();
        assertEquals(configurationManager.get("playera"), "John");
    }

}
