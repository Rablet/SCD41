package dev.rablet.scd41.mockplugin;

import com.pi4j.extension.Plugin;
import com.pi4j.extension.PluginService;
import com.pi4j.provider.Provider;

import dev.rablet.scd41.mockplugin.platform.MockPlatform;
import dev.rablet.scd41.mockplugin.provider.i2c.MockI2CProvider;

public class MockPlugin implements Plugin {

    private Provider providers[] = {
            MockI2CProvider.newInstance(),
    };

    @Override
    public void initialize(PluginService service) {
        service.register(new MockPlatform())
                .register(providers);
    }
}
