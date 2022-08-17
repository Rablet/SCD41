package dev.rablet.scd41.mockplugin.provider.i2c;

import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;
import com.pi4j.io.i2c.I2CProviderBase;

public class MockI2CProviderImpl extends I2CProviderBase implements MockI2CProvider {

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
