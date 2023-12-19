package dev.rablet.scd41.mockplugin;

/**
 * Mock for testing an SCD41 sensor. Contains constants for unique IDs and names
 */
public class Mock {

    /**
     * The name of the plugin
     */
    public static final String NAME = "SCD41MockPlugin";
    /**
     * The ID of the plugin
     */
    public static final String ID = "scd41mockplugin";

    /**
     * The platform name
     */
    public static final String PLATFORM_NAME = NAME + " Platform";
    /**
     * The platform ID
     */
    public static final String PLATFORM_ID = ID;
    /**
     * The platform description
     */
    public static final String PLATFORM_DESCRIPTION = "Pi4J Platform for mocking SCD41.";
    /**
     * The I2C provider name
     */
    public static final String I2C_PROVIDER_NAME = NAME + " I2C Provider";
    /**
     * The I2C provider ID
     */
    public static final String I2C_PROVIDER_ID = ID + "-i2c";
}
