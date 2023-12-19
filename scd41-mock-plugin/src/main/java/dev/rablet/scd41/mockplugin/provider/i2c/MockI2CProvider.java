package dev.rablet.scd41.mockplugin.provider.i2c;

import com.pi4j.io.i2c.I2CProvider;
import dev.rablet.scd41.mockplugin.Mock;

/**
 * Mock I2C provider for unit testing an SCD41 sensor
 */
public interface MockI2CProvider extends I2CProvider {

    /** {@inheritDoc} */
    static String NAME = Mock.I2C_PROVIDER_NAME;
    /** {@inheritDoc} */
    static String ID = Mock.I2C_PROVIDER_ID;

    /** {@inheritDoc} */
    static MockI2CProvider newInstance() {
        return new MockI2CProviderImpl();
    }
}
