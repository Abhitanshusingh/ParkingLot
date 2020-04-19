package com.bridgelabz.utility;

import com.bridgelabz.observer.IParkingLotObserver;

public class ParkingLotOwner implements IParkingLotObserver {

    public enum Sign {PARKING_IS_FULL, PARKING_IS_VACANT};
    Sign sign;

    public Sign getSign() {
        return sign;
    }

    @Override
    public void sendParkingStatus(int currentNumberOfVehicle, int parkingLotCapacity) {
        if (currentNumberOfVehicle >= parkingLotCapacity)
            sign = sign.PARKING_IS_FULL;
        else
            sign = sign.PARKING_IS_VACANT;
    }
}
