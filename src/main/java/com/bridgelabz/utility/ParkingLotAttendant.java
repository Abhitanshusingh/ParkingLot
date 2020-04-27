package com.bridgelabz.utility;

import com.bridgelabz.entity.*;
import com.bridgelabz.enumeration.DriverType;
import com.bridgelabz.enumeration.VehicleType;
import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.observer.IParkingLotObserver;
import com.bridgelabz.observer.IParkingLotSubject;

import java.time.LocalTime;
import java.util.*;

public class ParkingLotAttendant implements IParkingLotSubject {
    public int parkingLotCapacity;
    public int noOfParkingLots;
    public int noOfSlotsPerLot;
    public int slotCounter = 0;
    public static double parkedCharge =0;
    LocalTime localTime = java.time.LocalTime.now();
    private ArrayList<IParkingLotObserver> observers = new ArrayList<IParkingLotObserver>();
    public HashMap<Slot, Vehicle> vehicleParkedDetail;

    public ParkingLotAttendant(int parkingLotCapacity, int noOfParkingLots, int noOfSlotsPerLot) {
        this.parkingLotCapacity = parkingLotCapacity;
        this.noOfParkingLots = noOfParkingLots;
        this.noOfSlotsPerLot = noOfSlotsPerLot;
        this.vehicleParkedDetail = new HashMap<>();
    }

    public HashMap<Slot, Vehicle> attendantPark(Vehicle vehicle, DriverType driverType, VehicleType vehicleType) throws ParkingLotException {
        if (vehicleParkedDetail.size() > this.parkingLotCapacity)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SPACE, "Parking lot is full");
        if (vehicleType.equals(VehicleType.LARGE))
            largeParking(vehicle, driverType);
        vehicle.setDriverType(driverType);
        vehicle.setVehicleType(vehicleType);
        slotCounter = slotCounter + 1;
        Slot slot = new Slot();
        slot.setSlotID(slotCounter);
        slot.setArrivalTime(localTime.getHour(), localTime.getMinute());
        ParkingLot lot = new ParkingLot(AssignParkingLot.assignLot(slot.getSlotID()));
        slot.setLot(lot);
        vehicleParkedDetail.put(slot, vehicle);
        this.notifyObservers(vehicleParkedDetail.size());
        return vehicleParkedDetail;
    }

    public HashMap<Slot, Vehicle> attendantUnPark(Vehicle vehicle) throws ParkingLotException {
        if (!vehicleParkedDetail.containsValue(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "Vehicle is not in parking lot");
        Set<Slot> slots = vehicleParkedDetail.keySet();
        for (Slot slot : slots) {
            if (vehicleParkedDetail.get(slot).equals(vehicle)) {
                slot.setDepartureTime(localTime.getHour(), localTime.getMinute());
                this.generateBillOfParkedCar(slot);
                vehicleParkedDetail.remove(slot);
                this.notifyObservers(this.vehicleParkedDetail.size());
            }
        }
        return vehicleParkedDetail;
    }

    @Override
    public void register(IParkingLotObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unRegister(IParkingLotObserver observer) {
        observers.remove(observers.indexOf(observer));
    }

    @Override
    public void notifyObservers(int assignedSlot) {
        for (IParkingLotObserver vehicleObserver : observers) {
            vehicleObserver.sendParkingStatus(assignedSlot, this.parkingLotCapacity);
        }
    }

    public void largeParking(Vehicle vehicle, DriverType driverType) {
        vehicle.setDriverType(driverType);
        vehicle.setVehicleType(VehicleType.LARGE);
        Slot slot = new Slot();
        slotCounter = slotCounter + 1;
        slot.setSlotID(slotCounter);
        slot.setArrivalTime(localTime.getHour(), localTime.getMinute());
        ParkingLot lot = new ParkingLot(AssignParkingLot.assignLot(slot.getSlotID()));
        slot.setLot(lot);
        vehicleParkedDetail.put(slot, vehicle);
        slotCounter = slotCounter + 1;
        slot.setSlotID(slotCounter);
        vehicleParkedDetail.put(slot, vehicle);
        this.notifyObservers(vehicleParkedDetail.size());
    }

    public int getBeforeThirtyMinuteParkedCar() {
        int counter = 0;
        int currentHour = localTime.getHour();
        int currentMinute = localTime.getMinute();
        Set<Slot> slots = vehicleParkedDetail.keySet();
        for (Slot slot : slots) {
            {
                int hour = 0;
                int min = 0;
                hour = currentHour - slot.arrivalHour;
                min = currentMinute - slot.arrivalMinute;
                int totalTime = ((hour * 60) + min);
                if (totalTime <= 30) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public double generateBillOfParkedCar(Slot slot) {
        double chargePerMinute = 0.5;
        int currentHour = localTime.getHour();
        int currentMinute = localTime.getMinute();
        int hour = currentHour - slot.arrivalHour;
        int min = currentMinute - slot.arrivalMinute;
        int totalTime = ((hour * 60) + min);
        parkedCharge = totalTime * chargePerMinute;
        return parkedCharge;
    }
}