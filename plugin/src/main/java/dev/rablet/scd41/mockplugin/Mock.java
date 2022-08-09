package dev.rablet.scd41.mockplugin;

/**
 * <p>
 * RaspberryPiPlugin class.
 * </p>
 *
 * @author Robert Savage (<a href=
 *         "http://www.savagehomeautomation.com">http://www.savagehomeautomation.com</a>)
 * @version $Id: $Id
 */
public class Mock {

    /**
     * Constant <code>NAME="LinuxFS"</code>
     */
    public static final String NAME = "RobMockPlugin";
    /**
     * Constant <code>ID="linuxfs"</code>
     */
    public static final String ID = "robmockplugin";

    // Platform name and unique ID
    /** Constant <code>PLATFORM_NAME="NAME +  Platform"</code> */
    public static final String PLATFORM_NAME = NAME + " Platform";
    /** Constant <code>PLATFORM_ID="ID"</code> */
    public static final String PLATFORM_ID = ID;
    /**
     * Constant
     * <code>PLATFORM_DESCRIPTION="Pi4J Platform for the RaspberryPi serie"{trunked}</code>
     */
    public static final String PLATFORM_DESCRIPTION = "Pi4J Platform for mocking SCD41.";

    // // Analog Input (GPIO) Provider name and unique ID
    // public static final String ANALOG_INPUT_PROVIDER_NAME = NAME + " Analog Input
    // (GPIO) Provider";
    // public static final String ANALOG_INPUT_PROVIDER_ID = ID + "-analog-input";

    // Analog Output (GPIO) Provider name and unique ID
    /**
     * Constant
     * <code>ANALOG_OUTPUT_PROVIDER_NAME="NAME +  Analog Output (GPIO) Provider"</code>
     */
    public static final String ANALOG_OUTPUT_PROVIDER_NAME = NAME + " Analog Output (GPIO) Provider";
    /**
     * Constant <code>ANALOG_OUTPUT_PROVIDER_ID="ID + -analog-output"</code>
     */
    public static final String ANALOG_OUTPUT_PROVIDER_ID = ID + "-analog-output";

    // Digital Input (GPIO) Provider name and unique ID
    /**
     * Constant
     * <code>DIGITAL_INPUT_PROVIDER_NAME="NAME +   Digital Input (GPIO) Provider"</code>
     */
    public static final String DIGITAL_INPUT_PROVIDER_NAME = NAME + " Digital Input (GPIO) Provider";
    /**
     * Constant <code>DIGITAL_INPUT_PROVIDER_ID="ID + -digital-input"</code>
     */
    public static final String DIGITAL_INPUT_PROVIDER_ID = ID + "-digital-input";

    // Digital Output (GPIO) Provider name and unique ID
    /**
     * Constant
     * <code>DIGITAL_OUTPUT_PROVIDER_NAME="NAME +   Digital Output (GPIO) Provider"</code>
     */
    public static final String DIGITAL_OUTPUT_PROVIDER_NAME = NAME + " Digital Output (GPIO) Provider";
    /**
     * Constant <code>DIGITAL_OUTPUT_PROVIDER_ID="ID + -digital-output"</code>
     */
    public static final String DIGITAL_OUTPUT_PROVIDER_ID = ID + "-digital-output";

    // // PWM Provider name and unique ID
    // public static final String PWM_PROVIDER_NAME = NAME + " PWM Provider";
    // public static final String PWM_PROVIDER_ID = ID + "-pwm";

    // I2C Provider name and unique ID
    public static final String I2C_PROVIDER_NAME = NAME + " I2C Provider";
    public static final String I2C_PROVIDER_ID = ID + "-i2c";

    // // SPI Provider name and unique ID
    // public static final String SPI_PROVIDER_NAME = NAME + " SPI Provider";
    // public static final String SPI_PROVIDER_ID = ID + "-spi";
    //
    // // Serial Provider name and unique ID
    // public static final String SERIAL_PROVIDER_NAME = NAME + " Serial Provider";
    // public static final String SERIAL_PROVIDER_ID = ID + "-serial";
}
