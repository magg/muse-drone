package com.magg.drone.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

import static com.magg.drone.utils.Constants.HEADER;
import static org.junit.Assert.assertTrue;

public class DroneDeliveryTest
{



    @Test
    public void validDelivery() throws URISyntaxException, IOException
    {

        URL resource = getClass().getClassLoader().getResource("files");
        String path = Paths.get(resource.toURI()).toString();

        DroneDelivery droneDelivery = new DroneDelivery(path+"/");

        List<String> output = droneDelivery.processSingleDelivery(1);

        String outStr = output
            .stream()
            .map(Object::toString)
            .collect(Collectors.joining(","));

        assertTrue(output.contains(HEADER));
        assertTrue(outStr.contains("(-2, 4) dirección Norte"));
        assertTrue(outStr.contains("(-1, 3) dirección Sur"));
        assertTrue(outStr.contains("(0, 0) dirección Occidente"));

        boolean exists = Files.exists(Paths.get(path, "out01.txt"));

        Files.deleteIfExists(Paths.get(path, "out01.txt"));

        assertTrue(exists);
    }
}
