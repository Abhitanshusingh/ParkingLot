package com.bridgelabz.utility;

import com.bridgelabz.entity.ParkingLot;
import com.bridgelabz.entity.Slot;
import com.bridgelabz.entity.Vehicle;
import com.bridgelabz.enumeration.DriverType;
import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.observer.IParkingLotObserver;
import com.bridgelabz.observer.IParkingLotSubject;

import java.time.LocalTime;
import java.util.*;

public class ParkingLotAttendant implements IParkingLotSubject {
    public int parkingLotCapacity = 0;
    public int noOfParkingLots = 0;
    public int noOfSlotsPerLot = 0;
    public int slotCounter = 0;

    private ArrayList<IParkingLotObserver> observers = new ArrayList<IParkingLotObserver>();
    public HashMap<Slot, Vehicle> vehicleParkedDetail;

    public ParkingLotAttendant(int parkingLotCapacity, int noOfParkingLots, int noOfSlotsPerLot) {
        this.parkingLotCapacity = parkingLotCapacity;
        this.noOfParkingLots = noOfParkingLots;
        this.noOfSlotsPerLot = noOfSlotsPerLot;
        this.vehicleParkedDetail = new HashMap<>();
    }

    public HashMap<Slot, Vehicle> attendantPark(Vehicle vehicle, DriverType driverType) throws ParkingLotException {
        if (vehicleParkedDetail.size() > this.parkingLotCapacity)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SPACE, "Parking lot is full");
        vehicle.setDriverType(driverType);
        slotCounter = slotCounter + 1;
        Slot slot = new Slot();
        slot.setSlotID(slotCounter);
        slot.setArrivalTime(LocalTime.of(12, 10, 20));
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
                slot.setDepartureTime(LocalTime.of(11, 25, 45));
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
}