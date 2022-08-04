package dev.rablet.scd41.model;

/**
 * Stores the temperature, humidity, and pressure information retrieved from a
 * SCD41 sensor
 */
public class SCD41Data {
    private double temperature;
    private double humidity;
    private int co2;

    /**
     * Creates an instance of BME280Data for storing data from a BME280 sensor
     * 
     * @param temperature the temperature in celsius
     * @param humidity    the relative humidity
     * @param co2         the co2 concentration
     */
    public SCD41Data(double temperature, double humidity, int co2) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return this.humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getCo2() {
        return this.co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    @Override
    public String toString() {
        return "{" +
                " temperature='" + getTemperature() + "'" +
                ", humidity='" + getHumidity() + "'" +
                ", co2='" + getCo2() + "'" +
                "}";
    }
}
