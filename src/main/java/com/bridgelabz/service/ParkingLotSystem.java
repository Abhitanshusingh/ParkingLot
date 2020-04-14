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

    public boolean Unpark(Object vehicle) throws ParkingLotException {
        if (this.vehicle == null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE,"No vehicle in parking lot");
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}