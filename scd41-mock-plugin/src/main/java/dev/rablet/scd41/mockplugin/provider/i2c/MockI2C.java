package dev.rablet.scd41.mockplugin.provider.i2c;

import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CBase;
import com.pi4j.io.i2c.I2CConfig;
import com.pi4j.io.i2c.I2CProvider;

import dev.rablet.scd41.mockplugin.SCD41Commands;

import java.util.Arrays;

/**
 * Mocks an I2C provider for the purpose of unit testing an SCD41 sensor reader.
 * Attempts to simulate the real business rules of an SCD41 sensor:
 * - Sensor Reads only allowed when periodic reads are on
 * - Have to check if data is ready before reading sensor
 * - Returns the exact sensor data used as an example in the spec sheet
 */
public class MockI2C extends I2CBase implements I2C {

    /** {@inheritDoc} */
    public MockI2C(I2CProvider provider, I2CConfig config) {
        super(provider, config);
    }

    /** {@inheritDoc} */
    @Override
    public void close() {

        super.close();
    }

    int sensorReadAllowed = 0;
    int periodicReadsOn = 0;
    int waitedForData = 0;

    byte[] stopPeriodicReadsBytes = buildCommmandArray(SCD41Commands.CMD_STOP_PERIODIC_MEASUREMENT.getValue());
    byte[] startPeriodicReadsBytes = buildCommmandArray(SCD41Commands.CMD_START_PERIODIC_MEASUREMENT.getValue());
    byte[] dataReadyBytes = buildCommmandArray(SCD41Commands.CMD_DATA_READY.getValue());
    byte[] readDataBytes = buildCommmandArray(SCD41Commands.CMD_READ_DATA.getValue());

    byte[] nextBytesToRead = { 0, 0 };

    /** {@inheritDoc} */
    @Override
    public int read(byte[] data, int length) {

        for (int i = 0; i < length - 1; i++) {
            data[i] = nextBytesToRead[i];
        }

        return 1;
    }

    /**
     * Converts a string representation of a hexadecimal value to a byte array
     * 
     * @param s String representation of a hexadecimal value to be converted
     * @return byte array for the same value
     */
    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /** {@inheritDoc} */
    @Override
    public int write(byte[] data) {

        if (Arrays.equals(data, stopPeriodicReadsBytes)) {
            sensorReadAllowed = 0;
            periodicReadsOn = 0;
        } else if (Arrays.equals(data, startPeriodicReadsBytes)) {
            sensorReadAllowed = 1;
            periodicReadsOn = 1;
        } else if (Arrays.equals(data, dataReadyBytes)) {
            waitedForData = 1;
            this.nextBytesToRead[0] = 127;
            this.nextBytesToRead[1] = 127;

        } else if (Arrays.equals(data, readDataBytes)) {
            if (waitedForData == 1) {
                nextBytesToRead = hexStringToByteArray("01f47b6667a25eb93c");
            } else {
                waitedForData = 1;
                nextBytesToRead[0] = 127;
                nextBytesToRead[1] = 127;
            }
        }

        return 1;
    }

    /**
     * Builds a byte array representation of a command to be sent based on a hex
     * value
     * 
     * @param int the command to be converted to a byte array
     * @return the byte array representation of the command
     */
    private byte[] buildCommmandArray(int command) {
        byte[] cmd = new byte[2];
        cmd[0] = (byte) ((command >> 8) & 0xFF);
        cmd[1] = (byte) (command & 0xFF);
        return cmd;
    }

    @Override
    public int write(byte b) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int write(byte[] data, int offset, int length) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int read() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int read(byte[] buffer, int offset, int length) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int readRegister(int register) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int readRegister(int register, byte[] buffer, int offset, int length) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int writeRegister(int register, byte b) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int writeRegister(int register, byte[] data, int offset, int length) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
