package dev.rablet.scd41.mockplugin.provider.i2c;

import com.pi4j.io.i2c.I2CProvider;
import dev.rablet.scd41.mockplugin.Mock;

/**
 * Mock I2C provider for unit testing an SCD41 sensor
 */
public interface MockI2CProvider extends I2CProvider {

    /**
     * The name of the provider
     */
    static String NAME = Mock.I2C_PROVIDER_NAME;
    /**
     * The ID of the provider
     */
    static String ID = Mock.I2C_PROVIDER_ID;

    /**
     * Return an instance of this MockI2CProvider
     * 
     * @return the MockI2CProvider instance
     */
    static MockI2CProvider newInstance() {
        return new MockI2CProviderImpl();
    }
}
