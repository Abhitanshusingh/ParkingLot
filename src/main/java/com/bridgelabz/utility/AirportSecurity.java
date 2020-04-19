package com.bridgelabz.utility;

import com.bridgelabz.observer.IParkingLotObserver;

public class AirportSecurity implements IParkingLotObserver {
    boolean isParkingFull;

    public boolean isParkingFull() {
        return isParkingFull;
    }

    @Override
    public void sendParkingStatus(int currentNumberOfVehicle, int parkingLotCapacity) {
        if (currentNumberOfVehicle >= parkingLotCapacity)
            this.isParkingFull = true;
        else
            this.isParkingFull = false;
    }
}
