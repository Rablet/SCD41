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
    public void testGetSCD41Data() throws IOException {
        // Uses the mockprovider which is set to return
        // the example data from the SCD41 data sheet here (section 3.5.2):
        // https://sensirion.com/media/documents/C4B87CE6/627C2DCD/CD_DS_SCD40_SCD41_Datasheet_D1.pdf
        // This is run through the parsing functionality to ensure we end up with the correct values
        SCD41Client scd41Client = new SCD41Client("scd41mockplugin-i2c", "MOCKBME280", 1, 0x12);

        SCD41Data data = scd41Client.getSCD41Data();

        assertEquals(data.getCo2(), 500);
        assertEquals(data.getTemperature(), 25, 0.01);
        assertEquals(data.getHumidity(), 37, 0.01);
    }
}
