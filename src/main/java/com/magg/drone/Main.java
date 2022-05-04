package com.magg.drone;

import com.magg.drone.service.DroneDelivery;

public class Main
{

    public static void main(String [] args) {

        DroneDelivery droneDelivery = new DroneDelivery("files/");

        droneDelivery.makeDeliveries();

    }
}
