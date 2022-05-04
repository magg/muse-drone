package com.magg.drone.service;

import com.magg.drone.exception.DeliveryCapacityExceededException;
import com.magg.drone.exception.NumberOfBlocksExceededException;
import com.magg.drone.model.Drone;
import com.magg.drone.service.impl.RouteProcessorImpl;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

import static com.magg.drone.model.Direction.NORTH;
import static com.magg.drone.utils.Constants.HEADER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class RouteProcessorTest
{

    RouteProcessor routeProcessor;

    @Before
    public void setup() {
        routeProcessor = new RouteProcessorImpl();
    }

    @Test
    public void testValidRouote() {

        List<String> route = List.of("AAAAIAAD");

        Drone drone =  new Drone();
        List<String> output = routeProcessor.process(route, drone);
        String outStr = output
            .stream()
            .map(Object::toString)
            .collect(Collectors.joining(","));

        assertTrue(output.contains(HEADER));
        assertTrue(outStr.contains("(-2, 4) dirección Norte"));

        assertEquals(4, drone.getPosition().getY());
        assertEquals(-2, drone.getPosition().getX());
        assertEquals(NORTH, drone.getPosition().getDirection());

    }

    @Test
    public void testInvalidRouote() {

        List<String> route = List.of("A", "A", "A", "A" , "A", "A" ,"A", "A" , "A", "A", "A", "A" );

        Drone drone =  new Drone();
        DeliveryCapacityExceededException ex = assertThrows(
            DeliveryCapacityExceededException.class,
            () -> routeProcessor.process(route, new Drone()));
    }

    @Test
    public void testInValidBlocksRouote() {

        List<String> route = List.of("AAAAIAADAAAAAAAAAAAAAAAAAAAAAAA");

        Drone drone =  new Drone();
        NumberOfBlocksExceededException ex = assertThrows(
            NumberOfBlocksExceededException.class,
            () -> routeProcessor.process(route, new Drone()));

    }


}
