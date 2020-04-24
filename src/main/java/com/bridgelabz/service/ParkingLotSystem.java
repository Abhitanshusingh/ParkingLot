package com.bridgelabz.service;

import com.bridgelabz.entity.Slot;
import com.bridgelabz.entity.Vehicle;
import com.bridgelabz.enumeration.DriverType;
import com.bridgelabz.enumeration.VehicleType;
import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.utility.*;

import java.time.LocalTime;
import java.util.*;

public class ParkingLotSystem {

    public int noOfSlotsPerLot;
    public LocalTime arrivalTime;
    ParkingLotAttendant parkingLotAttendant = null;
    AssignParkingLot assignParkingLot = null;

    public HashMap<Slot, Vehicle> vehicleParkedDetail = new HashMap<Slot, Vehicle>();

    public ParkingLotSystem(int parkingLotCapacity, int noOfParkingLots) {
        this.assignParkingLot = new AssignParkingLot(parkingLotCapacity, noOfParkingLots);
        this.noOfSlotsPerLot = assignParkingLot.getNoOfSlotsPerLot();
        this.parkingLotAttendant = new ParkingLotAttendant(parkingLotCapacity, noOfParkingLots, assignParkingLot.getNoOfSlotsPerLot());
    }

    public void park(Vehicle vehicle, DriverType driverType, VehicleType vehicleType) throws ParkingLotException {
        vehicleParkedDetail = parkingLotAttendant.attendantPark(vehicle, driverType, vehicleType);
    }

    public void unPark(Vehicle vehicle) throws ParkingLotException {
        vehicleParkedDetail = parkingLotAttendant.attendantUnPark(vehicle);
    }

    public void register(ParkingLotOwner owner) {
        parkingLotAttendant.register(owner);
    }

    public void register(AirportSecurity airportPersonnel) {
        parkingLotAttendant.register(airportPersonnel);
    }

    public boolean isVehiclePresentInLot(Vehicle vehicle) {
        return vehicleParkedDetail.containsValue(vehicle);
    }

    public LocalTime getArrivalTime(Vehicle vehicle) {
        for (Slot slot : vehicleParkedDetail.keySet()) {
            if (vehicleParkedDetail.get(slot).equals(vehicle)) {
                arrivalTime = slot.getArrivalTime();
            }
        }
        return arrivalTime;
    }

    public int getWhiteCars() {
        int counter = 0;
        for (Map.Entry<Slot, Vehicle> entry : vehicleParkedDetail.entrySet()) {
            if (entry.getValue().colour.equals("White")) {
                counter++;
            }
        }
        return counter;
    }

    public int getBlueToyotaCars() {
        int counter = 0;
        for (Map.Entry<Slot, Vehicle> entry : vehicleParkedDetail.entrySet()) {
            Vehicle value = entry.getValue();
            if (value.brand.equals("Toyota") && value.colour.equals("Blue")) {
                counter++;
            }
        }
        return counter;
    }
}