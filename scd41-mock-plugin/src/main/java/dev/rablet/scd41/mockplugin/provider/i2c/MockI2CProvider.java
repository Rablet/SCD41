package dev.rablet.scd41.mockplugin.provider.i2c;

import com.pi4j.io.i2c.I2CProvider;
import dev.rablet.scd41.mockplugin.Mock;

public interface MockI2CProvider extends I2CProvider {

    static String NAME = Mock.I2C_PROVIDER_NAME;
    static String ID = Mock.I2C_PROVIDER_ID;

    static MockI2CProvider newInstance() {
        return new MockI2CProviderImpl();
    }
}
