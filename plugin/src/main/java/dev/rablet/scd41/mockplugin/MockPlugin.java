package dev.rablet.scd41.mockplugin;

import com.pi4j.extension.Plugin;
import com.pi4j.extension.PluginService;
import com.pi4j.provider.Provider;

import dev.rablet.scd41.mockplugin.platform.MockPlatform;
import dev.rablet.scd41.mockplugin.provider.i2c.MockI2CProvider;

/**
 * <p>
 * RaspberryPiPlugin class.
 * </p>
 *
 * @author Robert Savage (<a href=
 *         "http://www.savagehomeautomation.com">http://www.savagehomeautomation.com</a>)
 * @version $Id: $Id
 */
public class MockPlugin implements Plugin {

    private Provider providers[] = {
            MockI2CProvider.newInstance(),
    };

    @Override
    public void initialize(PluginService service) {

        // register the Mock Platform and all Mock I/O Providers with the plugin service
        service.register(new MockPlatform())
                .register(providers);

    }
}
