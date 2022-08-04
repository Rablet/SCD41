package dev.rablet.scd41;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;
import com.pi4j.io.i2c.I2CProvider;
import com.pi4j.provider.exception.ProviderNotFoundException;

import dev.rablet.scd41.model.SCD41Data;

import java.io.IOException;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/**
 * Client used to read data from an SCD41 sensor.
 */
public class SCD41Client {

    private final static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(SCD41Client.class);

    /*
     * Sensor Commands
     */
    private final int CMD_GET_SERIAL = 0x3682;
    private final int CMD_ONE_OFF_READING = 0x219d;
    private final int CMD_READ_DATA = 0xEC05;
    private final int CMD_START_PERIODIC_MEASUREMENT = 0x21B1;
    private final int CMD_STOP_PERIODIC_MEASUREMENT = 0x3F86;
    private final int CMD_DATA_READY = 0xE4B8;

    private I2CProvider i2CProvider;
    private I2CConfig i2cConfig;

    private int i2cBus;
    private int i2cDevice;

    /**
     * Creates an instance of BME280 which can be used to get sensor reads.
     * Uses the following defaults:
     * * providerId = linuxfs-i2c
     * * configName = SCD41
     * * i2cBus = 1
     * * i2cDevice = 0x62
     */
    public SCD41Client() {
        this("linuxfs-i2c", "SCD41", 1, 0x62);
    }

    /**
     * Creates an instance of BME280 which can be used to get sensor reads.
     * 
     * @param provider   the pi4j provider id
     * @param configName the id to use for the configuration
     * @param i2cBus     the i2c bus
     * @param i2cDevice  the i2cDevice address
     * 
     * @throws IllegalArgumentException if the provider does not exist
     */
    public SCD41Client(String provider, String configName, Integer i2cBus, Integer i2cDevice)
            throws IllegalArgumentException {
        Context pi4j = Pi4J.newAutoContext();
        try {
            this.i2CProvider = pi4j.provider("linuxfs-i2c");
        } catch (ProviderNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        this.i2cConfig = I2C.newConfigBuilder(pi4j).id(configName).bus(i2cBus).device(i2cDevice).build();
    }

    /**
     * Gets the SCD41 sensor serial.
     * This will stop any periodic reads since getting serial is not supported
     * during periodic reads
     * 
     * @return String with the serial
     * @throws IOException if the serial could not be retrieved
     */
    public String getSerial() throws IOException {
        try (I2C ioSCD41 = i2CProvider.create(i2cConfig)) {
            LOG.info("Getting SCD41 serial");
            stopPeriodicReads(ioSCD41);
            sendCommand(ioSCD41, CMD_GET_SERIAL);
            byte[] response = new byte[9];
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                LOG.warn("Could not sleep", e);
            }
            ioSCD41.read(response, 9);

            StringJoiner sj = new StringJoiner(" ");

            for (byte b : response) {
                sj.add(String.format("%02X ", b));
            }
            LOG.trace("SCD41 Serial: {}", sj.toString());
            return sj.toString();
        } catch (Exception e) {
            throw new IOException("Could not get SCD41 serial", e);
        }
    }

    /**
     * Reads data from the SCD41 sensor using the periodic read functionality of the
     * sensor.
     * 
     * Once completed it will stop periodic reads from the sensor.
     * 
     * @return SCD41Data with the data from the sensor
     * @throws IOException if reading from the sensor failed
     */
    public SCD41Data getSCD41Data() throws IOException {
        try (I2C ioSCD41 = i2CProvider.create(i2cConfig)) {
            LOG.trace("I2C Bus: {}, Device: {}", i2cBus, i2cDevice);
            LOG.debug("Stopping any existing periodic reads");
            stopPeriodicReads(ioSCD41);
            LOG.debug("Starting periodic reads");
            startPeriodicReads(ioSCD41);
            LOG.debug("Waiting for data to be available");
            waitUntilDataIsAvailable(ioSCD41);
            LOG.debug("Data available. Reading from sensor");
            byte[] rawSensorData = readData(ioSCD41);
            LOG.debug("Stopping periodic reads");
            stopPeriodicReads(ioSCD41);
            LOG.debug("Parsing data");
            SCD41Data scd41Data = parseReadingResponse(rawSensorData);
            LOG.debug("Data == {}", scd41Data);
            return scd41Data;
        } catch (Exception e) {
            throw new IOException("Could not read SCD41 sensor data", e);
        }
    }

    /**
     * Sends a command to the I2C device
     * 
     * @param ioSCD41 the I2C device to send the command to
     * @param command the command to send
     */
    private void sendCommand(I2C ioSCD41, int command) {
        byte[] cmd = new byte[2];
        cmd[0] = (byte) ((command >> 8) & 0xFF);
        cmd[1] = (byte) (command & 0xFF);
        LOG.trace("Sending command {} {}", String.format("%02X ", cmd[0]), String.format("%02X ", cmd[1]));
        ioSCD41.write(cmd);
    }

    /**
     * Requests a one-off read from the sensor. This will stop any periodic sensor
     * reads.
     * NOTE: This appears to give inaccurate reads - use with caution
     * 
     * @return SCD41Data the data read from the sensor
     * 
     * @throws IOException if the sensor could not be read
     */
    public SCD41Data oneOffRead() throws IOException {
        try (I2C ioSCD41 = i2CProvider.create(i2cConfig)) {

            stopPeriodicReads(ioSCD41);
            sendCommand(ioSCD41, CMD_ONE_OFF_READING);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                LOG.warn("Could not sleep", e);
            }
            byte[] measurementResponse = new byte[9];
            ioSCD41.read(measurementResponse, 9);
            return parseReadingResponse(measurementResponse);
        } catch (Exception e) {
            throw new IOException("Could not read SCD41 sensor data", e);
        }
    }

    /**
     * Stops periodic measurements
     * 
     * @param ioSCD41 the I2C device to use
     */
    private void stopPeriodicReads(I2C ioSCD41) {
        sendCommand(ioSCD41, CMD_STOP_PERIODIC_MEASUREMENT);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            LOG.warn("Could not sleep", e);
        }
    }

    /**
     * Starts periodic reads
     * 
     * @param ioSCD41 the I2C device to use
     */
    private void startPeriodicReads(I2C ioSCD41) {
        sendCommand(ioSCD41, CMD_START_PERIODIC_MEASUREMENT);
        // No sleep needed after this command per data sheet
    }

    /**
     * Calls the data ready command every second until a sensor response is
     * available.
     * Returns once a response is available.
     * 
     * @param ioSCD41
     */
    private void waitUntilDataIsAvailable(I2C ioSCD41) {
        int dataReady = 0;
        while (dataReady == 0) {
            sendCommand(ioSCD41, CMD_DATA_READY);
            byte[] response = new byte[3];
            ioSCD41.read(response, 3);

            LOG.trace(
                    "Data Ready Response = {} {}", String.format("%02X ", response[0]),
                    String.format("%02X ", response[1]));
            if (!(((response[0] & 0x07) == 0) && (response[1] == 0))) {
                // Data ready
                return;
            } else {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    LOG.warn("Could not sleep");
                }
            }
        }
    }

    /**
     * Reads data from the sensor
     * 
     * @param tca9534Dev
     * @return
     */
    private byte[] readData(I2C tca9534Dev) {
        sendCommand(tca9534Dev, CMD_READ_DATA);
        byte[] measurementResponse = new byte[9];

        tca9534Dev.read(measurementResponse, 9);
        return measurementResponse;
    }

    /**
     * Parses a reading response from SCD41 a SCD41Data object
     * 
     * @param measurementResponse a byte array with the response data from the
     *                            sensor
     * @return SCD41Data with the data
     */
    private SCD41Data parseReadingResponse(byte[] measurementResponse) {
        int co2 = ((measurementResponse[0] & 0xff) << 8) | (measurementResponse[1] & 0xff);

        int temp = ((measurementResponse[3] & 0xff) << 8) | (measurementResponse[4] & 0xff);
        double temp2 = -45 + 175 * (temp / (Math.pow(2, 16) - 1));

        int hum = ((measurementResponse[6] & 0xff) << 8) | (measurementResponse[7] & 0xff);
        double hum2 = 100 * (hum / (Math.pow(2, 16) - 1));

        LOG.debug("CO2 = {}. Temp = {}. Humidity = {}", co2, temp2, hum2);

        SCD41Data scd41Data = new SCD41Data(temp2, hum2, co2);

        return scd41Data;
    }
}