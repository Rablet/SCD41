/**
 * Module for the mock Pi4j2 plugin used to test an SCD41 sensor
 */
module dev.rablet.scd41.mockplugin {
    requires com.pi4j;

    uses com.pi4j.extension.Plugin;

    exports dev.rablet.scd41.mockplugin;
    exports dev.rablet.scd41.mockplugin.platform;
    exports dev.rablet.scd41.mockplugin.provider.i2c;

    provides com.pi4j.extension.Plugin with dev.rablet.scd41.mockplugin.MockPlugin;
}
