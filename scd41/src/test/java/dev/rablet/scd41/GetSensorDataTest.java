package dev.rablet.scd41;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import dev.rablet.scd41.model.SCD41Data;

public class GetSensorDataTest {

    @Test
    public void testGetSCD41Data() throws IOException {
        // Uses the mockprovider which is set to return
        // the example data from the SCD41 data sheet here (section 3.5.2):
        // https://sensirion.com/media/documents/C4B87CE6/627C2DCD/CD_DS_SCD40_SCD41_Datasheet_D1.pdf
        // This is run through the parsing functionality to ensure we end up with the
        // correct values
        SCD41Client scd41Client = new SCD41Client("scd41mockplugin-i2c", "MOCKSCD41", 1, 0x12);

        SCD41Data data = scd41Client.getSCD41Data();

        assertEquals(500, data.getCo2());
        assertEquals(25, data.getTemperature(), 0.01);
        assertEquals(37, data.getHumidity(), 0.01);
    }
}
