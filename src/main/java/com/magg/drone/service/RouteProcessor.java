package com.magg.drone.service;

import com.magg.drone.model.Drone;
import java.util.List;

public interface RouteProcessor
{

    /**
     *
     * Method that processed a list of routes for a given drone. it returns routes in processed format.
     *
     * @param textRoute the List of routes to process
     * @param drone the drone
     * @return a List with the route processed
     */
    List<String> process(List<String> textRoute, Drone drone);


}
