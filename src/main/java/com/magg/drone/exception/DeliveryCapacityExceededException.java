package com.magg.drone.exception;

public class DeliveryCapacityExceededException extends RuntimeException
{
    public DeliveryCapacityExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeliveryCapacityExceededException(String message) {
        super(message);
    }
}
