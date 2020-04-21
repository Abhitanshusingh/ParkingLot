package com.bridgelabz.utility;

import com.bridgelabz.observer.IParkingLotObserver;
import com.bridgelabz.service.ParkingLotSystem;

import java.util.HashMap;

public class AirportSecurity implements IParkingLotObserver {
    boolean isParkingFull;

    public boolean isParkingFull() {
        return isParkingFull;
    }

    @Override
    public void sendParkingStatus(HashMap<Integer, String> vehicleParkedDetail) {
        if (ParkingLotSystem.isParkingLotFull(vehicleParkedDetail))
            this.isParkingFull = true;
        else
            this.isParkingFull = false;
    }
}
