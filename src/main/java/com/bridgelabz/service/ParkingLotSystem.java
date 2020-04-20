package com.bridgelabz.service;

import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.observer.IParkingLotObserver;
import com.bridgelabz.observer.IParkingLotSubject;
import com.bridgelabz.utility.ParkingLotAttendant;

import java.util.*;

public class ParkingLotSystem implements IParkingLotSubject {

    private int parkingLotCapacity;
    private String vehicleName;
    public HashMap<Integer, String> vehicleParkedDetail = new HashMap<Integer, String>();
    private ArrayList<IParkingLotObserver> observers = new ArrayList<IParkingLotObserver>();


    public ParkingLotSystem(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
        for (int slotNumber = 1; slotNumber <= parkingLotCapacity; slotNumber++) {
            vehicleParkedDetail.put(slotNumber, null);
        }
    }

    public void park(String vehicleName) throws ParkingLotException {
        if (isParkingLotFull(vehicleParkedDetail))
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SPACE, "Parking lot is full");
        this.vehicleName = vehicleName;
        vehicleParkedDetail = ParkingLotAttendant.park(vehicleParkedDetail, vehicleName);
        this.notifyObservers();
    }

    public boolean unPark(String vehicle) throws ParkingLotException {
        if (!isVehicleInParkingLot(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "Vehicle is not in parking lot");
        Iterator<String> parkingLotIterator = vehicleParkedDetail.values().iterator();
        this.vehicleName = vehicle;
        while (parkingLotIterator.hasNext()) {
            if (parkingLotIterator.next().equals(vehicle)) {
                parkingLotIterator.remove();
                return true;
            }
        }
        this.notifyObservers();
        return true;
    }

    public boolean isVehicleInParkingLot(String vehicle) {
        Iterator<String> parkingLotIterator = vehicleParkedDetail.values().iterator();
        while (parkingLotIterator.hasNext()) {
            if (Objects.equals(parkingLotIterator.next(), vehicle))
                return true;
        }
        return false;
    }

    public boolean isVehicleParked() {
        if (vehicleParkedDetail.containsValue(vehicleName))
            return true;
        return false;
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
    public void notifyObservers() {
        for (IParkingLotObserver vehicleObserver : observers) {
            vehicleObserver.sendParkingStatus(vehicleParkedDetail);
        }
    }

    public static boolean isParkingLotFull(HashMap<Integer, String> carParkedDetail) {
        for (int parkingSlot = 1; parkingSlot <= carParkedDetail.size(); parkingSlot++) {
            if (carParkedDetail.get(parkingSlot) == null)
                return false;
        }
        return true;
    }
}