package com.bridgelabz.service;

import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.observer.IParkingLotObserver;
import com.bridgelabz.observer.IParkingLotSubject;
import com.bridgelabz.utility.ParkingBill;
import com.bridgelabz.utility.ParkingLotAttendant;
import com.bridgelabz.utility.ParkingLotOwner;

import java.time.LocalTime;
import java.util.*;

public class ParkingLotSystem implements IParkingLotSubject {
    public int parkingHour;
    public int parkingMinute;
    private int parkingLotCapacity;
    public HashMap<Integer, String> vehicleParkedDetail = new HashMap<Integer, String>();
    private ArrayList<IParkingLotObserver> observers = new ArrayList<IParkingLotObserver>();
    LocalTime localTime = java.time.LocalTime.now();
    ParkingBill parkingBill = new ParkingBill();

    ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
    public ParkingLotSystem(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
        for (int parkingSlots = 1; parkingSlots <= parkingLotCapacity; parkingSlots++) {
            vehicleParkedDetail.put(parkingSlots, null);
        }
    }

    public void park(String vehicleName) throws ParkingLotException {
        if (isParkingLotFull(vehicleParkedDetail))
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SPACE, "Parking lot is full");
        vehicleParkedDetail = ParkingLotAttendant.attendantPark(vehicleParkedDetail, vehicleName);
        this.getCurrentTime();
        parkingBill.parkingTime(parkingHour,parkingMinute);
        this.notifyObservers();
    }

    public void unPark(String vehicleName) throws ParkingLotException {
        if (!isVehicleInParkingLot(vehicleName))
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "Vehicle is not in parking lot");
        vehicleParkedDetail = ParkingLotAttendant.attendantUnPark(vehicleParkedDetail, vehicleName);
        this.getCurrentTime();
        parkingBill.unParkingTime(parkingHour,parkingMinute);
        this.notifyObservers();
    }

    public boolean isVehicleInParkingLot(String vehicle) {
        Iterator<String> parkingLotIterator = vehicleParkedDetail.values().iterator();
        while (parkingLotIterator.hasNext()) {
            if (Objects.equals(parkingLotIterator.next(), vehicle))
                return true;
        }
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
        for (int parkingSlots = 1; parkingSlots <= carParkedDetail.size(); parkingSlots++) {
            if (carParkedDetail.get(parkingSlots) == null)
                return false;
        }
        return true;
    }

    public void getCurrentTime(){
        this.parkingHour = localTime.getHour();
        this.parkingMinute = localTime.getMinute();
    }
}