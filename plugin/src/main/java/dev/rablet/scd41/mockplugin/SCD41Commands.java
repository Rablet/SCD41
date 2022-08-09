package dev.rablet.scd41.mockplugin;

public enum SCD41Commands {

    CMD_GET_SERIAL(0x3682),
    CMD_ONE_OFF_READING(0x219d),
    CMD_READ_DATA(0xEC05),
    CMD_START_PERIODIC_MEASUREMENT(0x21B1),
    CMD_STOP_PERIODIC_MEASUREMENT(0x3F86),
    CMD_DATA_READY(0xE4B8);

    private final int value;

    SCD41Commands(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
