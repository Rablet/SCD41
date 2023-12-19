package dev.rablet.scd41.mockplugin;

import com.pi4j.extension.Plugin;
import com.pi4j.extension.PluginService;
import com.pi4j.provider.Provider;

import dev.rablet.scd41.mockplugin.platform.MockPlatform;
import dev.rablet.scd41.mockplugin.provider.i2c.MockI2CProvider;

/**
 * Mock plugin for testing SCD41 sensor
 */
public class MockPlugin implements Plugin {

    /**
     * This is only created to avoid javadoc warnings
     */
    public MockPlugin() {
        super();
    }

    /**
     * The providers supported by this plugin. Only I2C
     */
    private Provider[] providers = {
            MockI2CProvider.newInstance(),
    };

    /**
     * Registers the providers
     */
    @Override
    public void initialize(PluginService service) {
        service.register(new MockPlatform())
                .register(providers);
    }
}
