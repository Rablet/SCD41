package dev.rablet.scd41.mockplugin.platform;

import com.pi4j.context.Context;
import com.pi4j.platform.Platform;
import com.pi4j.platform.PlatformBase;

import dev.rablet.scd41.mockplugin.Mock;
import dev.rablet.scd41.mockplugin.provider.i2c.MockI2CProvider;

public class MockPlatform extends PlatformBase<MockPlatform> implements Platform {

    public MockPlatform() {
        super(Mock.PLATFORM_ID,
                Mock.PLATFORM_NAME,
                Mock.PLATFORM_DESCRIPTION);
    }

    /** {@inheritDoc} */
    @Override
    public int priority() {
        return 10;
    }

    /** {@inheritDoc} */
    @Override
    public boolean enabled(Context context) {
        // the Mock Platform is always available when detected
        // there are no logic checked required to determine when
        // and if the mock platforms should be enabled
        return true;
    }

    /** {@inheritDoc} */
    @Override
    protected String[] getProviders() {
        return new String[] {
                MockI2CProvider.ID };
    }
}