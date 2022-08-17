package dev.rablet.scd41;

/**
 * Enum for the supported SCD41 commands
 */
public enum SCD41Commands {

    /**
     * Command to get the serial number from SCD41
     */
    CMD_GET_SERIAL(0x3682),
    /**
     * Command to get a one-off reading.
     * Appears to result in inaccurate reads
     */
    CMD_ONE_OFF_READING(0x219d),
    /**
     * Reads data from SCD41.
     * Used to read data after sending a command
     */
    CMD_READ_DATA(0xEC05),
    /**
     * Starts periodic measurements
     */
    CMD_START_PERIODIC_MEASUREMENT(0x21B1),
    /**
     * Stops preiodic measurements
     */
    CMD_STOP_PERIODIC_MEASUREMENT(0x3F86),
    /**
     * Used to check if data is ready. 
     * Used after starting periodic measurements to determined when to read data
     */
    CMD_DATA_READY(0xE4B8);

    private final int value;

    /**
     * Constructor for the SCD41Commands enum. Allows for storing the command
     */
    SCD41Commands(final int newValue) {
        value = newValue;
    }


    /** 
     * 
     * Get the command for an enum
     * 
     * @return int the sensor command
     */
    public int getValue() {
        return value;
    }
}
