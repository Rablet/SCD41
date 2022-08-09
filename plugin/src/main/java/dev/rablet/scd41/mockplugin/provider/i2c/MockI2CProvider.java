package dev.rablet.scd41.mockplugin.provider.i2c;

import com.pi4j.io.i2c.I2CProvider;

public interface MockI2CProvider extends I2CProvider {

    static String NAME = "MockI2CProvider";
    static String ID = "MockI2CProviderId";

    static MockI2CProvider newInstance() {
        return new MockI2CProviderImpl();
    }
}
