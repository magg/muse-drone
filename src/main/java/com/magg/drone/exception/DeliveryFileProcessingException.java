package com.magg.drone.exception;

public class DeliveryFileProcessingException extends RuntimeException
{
    public DeliveryFileProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeliveryFileProcessingException(String message) {
        super(message);
    }
}
