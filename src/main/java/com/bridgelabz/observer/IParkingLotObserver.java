package com.bridgelabz.observer;

public interface IParkingLotObserver {
    public void sendParkingStatus(int assignedSlot, int parkingLotCapacity);
}
