package com.bridgelabz.service;

import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.observer.IParkingLotObserver;
import com.bridgelabz.observer.IParkingLotSubject;

import java.util.ArrayList;

public class ParkingLotSystem implements IParkingLotSubject {

    private int parkingLotCapacity;
    private String vehicleName;
    private ArrayList<String> parkedVehicleList = new ArrayList<String>();
    private ArrayList<IParkingLotObserver> observers = new ArrayList<IParkingLotObserver>();

    public ParkingLotSystem(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
    }

    public void park(String vehicleName) throws ParkingLotException {
        if (parkedVehicleList.size() >= parkingLotCapacity)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SPACE, "Parking lot is full");
        this.vehicleName = vehicleName;
        parkedVehicleList.add(vehicleName);
        this.notifyObservers();
    }

    public void unPark(String vehicle) throws ParkingLotException {
        if (!parkedVehicleList.contains(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "Vehicle is not in parking lot");
        parkedVehicleList.remove(vehicle);
        this.notifyObservers();
    }

    public boolean isVehicleParked() {
        if (parkedVehicleList.contains(vehicleName))
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
            vehicleObserver.sendParkingStatus(parkedVehicleList.size(), parkingLotCapacity);
        }
    }
}