package com.bridgelabz.exception;

public class ParkingLotException extends Exception {
   public enum ExceptionType {NO_SPACE,NO_SUCH_VEHICLE}

    public ExceptionType type;

   public ParkingLotException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
