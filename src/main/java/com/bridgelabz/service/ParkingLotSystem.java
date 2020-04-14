package com.bridgelabz.service;

import com.bridgelabz.exception.ParkingLotException;

public class ParkingLotSystem {

    private Object vehicle;

    public boolean park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SPACE, "Parking lot is full");
        this.vehicle = vehicle;
        return true;
    }
}