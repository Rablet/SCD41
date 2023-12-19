package dev.rablet.scd41.mockplugin.provider.i2c;

import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;
import com.pi4j.io.i2c.I2CProviderBase;

/**
 * Implementation of a mocked I2C provider for testing an SCD41 sensor
 */
public class MockI2CProviderImpl extends I2CProviderBase implements MockI2CProvider {

    /**
     * Create an instance of this provider
     */
    public MockI2CProviderImpl() {
        this.id = ID;
        this.name = NAME;
    }

    /** {@inheritDoc} */
    @Override
    public I2C create(I2CConfig config) {
        return new MockI2C(this, config);
    }
}
