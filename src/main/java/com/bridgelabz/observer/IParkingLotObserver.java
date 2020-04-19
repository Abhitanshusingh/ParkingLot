package com.bridgelabz.observer;

public interface IParkingLotObserver {
    public void sendParkingStatus(int currentNumberOfVehicle, int parkingLotCapacity);
}
