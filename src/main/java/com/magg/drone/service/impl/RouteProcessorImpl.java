package com.magg.drone.service.impl;

import com.magg.drone.exception.DeliveryCapacityExceededException;
import com.magg.drone.model.Drone;
import com.magg.drone.service.RouteProcessor;
import com.magg.drone.utils.Constants;
import java.util.ArrayList;
import java.util.List;

import static com.magg.drone.config.DroneDeliveryConfig.DELIVERY_CAPACITY;
import static com.magg.drone.utils.Constants.HEADER;

/**
 * RouteProcessorImpl class, it holds the business logic to process a route.
 */
public class RouteProcessorImpl implements RouteProcessor
{

    @Override
    public List<String> process(List<String> textRoute, Drone drone)
    {

        if (textRoute.size() > DELIVERY_CAPACITY) {
            throw new DeliveryCapacityExceededException(textRoute.size() + "exceeds capacity of "+ DELIVERY_CAPACITY);
        }

        List<String> processed = new ArrayList<>();
        processed.add(HEADER);

        for (String s : textRoute) {
            String[] arr = s.split("");

            for (String ch : arr) {
                processDirection(ch, drone);
            }

            processed.add(drone.getPosition().toString());
        }

        return processed;
    }

    private void processDirection(String str, Drone drone)
    {
        if (str.equals(Constants.FORWARD))
            drone.moveForward();
        if (str.equals(Constants.RIGHT))
            drone.turnRight();
        if (str.equals(Constants.LEFT))
            drone.turnLeft();
    }
}
