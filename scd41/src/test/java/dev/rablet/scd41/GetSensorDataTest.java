package dev.rablet.scd41;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Set;

import javax.print.attribute.standard.PrintQuality;

import org.junit.Test;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.platform.Platform;
import com.pi4j.provider.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.rablet.scd41.mockplugin.platform.MockPlatform;
import dev.rablet.scd41.model.SCD41Data;

public class GetSensorDataTest {

    private static final Logger logger = LoggerFactory.getLogger(GetSensorDataTest.class);

    @Test
    public void test() throws IOException {

        SCD41Client scd41Client = new SCD41Client("MockI2CProviderId", "MOCKBME280", 1, 0x12);

        SCD41Data data = scd41Client.getSCD41Data();

        logger.info("Data = {}", data);


        assertEquals(data.getCo2(), 500);
        assertEquals(data.getTemperature(), 25, 0.01);
        assertEquals(data.getHumidity(), 37, 0.01);
    }
}
