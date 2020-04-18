package com.bridgelabz.service;

import com.bridgelabz.exception.ParkingLotException;

import java.util.ArrayList;

public class ParkingLotSystem {

    private int parkingLotCapacity;
    private String vehicleName;
    private ArrayList<String> parkingLot = new ArrayList<String>();

    public ParkingLotSystem(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
    }


    public void park(String vehicleName) throws ParkingLotException {
        if (parkingLot.size() >= parkingLotCapacity)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SPACE, "Parking lot is full");
        this.vehicleName = vehicleName;
        parkingLot.add(vehicleName);
    }

    public void Unpark(String vehicle) throws ParkingLotException {
        if (!parkingLot.contains(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "Vehicle is not in parking lot");
        parkingLot.remove(vehicle);
    }

    public boolean isVehicleParked() {
        if (parkingLot.contains(vehicleName))
            return true;
        return false;
    }
}