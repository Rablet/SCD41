# SCD41

Library for reading a SCD41 sensor via pi4j2

## Example Usage:

Include as a dependency in pom.xml (or equivalent if not using maven):

    <dependency>
        <groupId>dev.rablet</groupId>
        <artifactId>scd41</artifactId>
        <version>0.0.2</version>
    </dependency>

Import in your class:

    import dev.rablet.scd41.SCD41Client;
    import dev.rablet.scd41.model.SCD41Data;

Then fetch data:

    SCD41Client client = new SCD41Client("linuxfs-i2c", "SCD41", i2cBus, i2cDevice);
    SCD41Data scd41Data = client.getSCD41Data();

    System.out.println(scd41Data.getTemperature());
    sSystem.out.println(scd41Data.getHumidity());
    System.out.println(scd41Data.getCo2());
