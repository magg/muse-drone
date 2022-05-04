package com.magg.drone.exception;

public class NumberOfBlocksExceededException extends RuntimeException
{
    public NumberOfBlocksExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberOfBlocksExceededException(String message) {
        super(message);
    }
}
