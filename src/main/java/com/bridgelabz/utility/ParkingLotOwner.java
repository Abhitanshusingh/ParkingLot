package com.bridgelabz.utility;

import com.bridgelabz.observer.IParkingLotObserver;
import com.bridgelabz.service.ParkingLotSystem;

import java.util.HashMap;

public class ParkingLotOwner implements IParkingLotObserver {

    public enum Sign {PARKING_IS_FULL, PARKING_IS_VACANT};
    Sign sign;

    public Sign getSign() {
        return sign;
    }

    @Override
    public void sendParkingStatus(HashMap<Integer, String> vehicleParkedDetail) {
        if (ParkingLotSystem.isParkingLotFull(vehicleParkedDetail))
            sign = sign.PARKING_IS_FULL;
        else
            sign = sign.PARKING_IS_VACANT;
    }

    public static Integer getPakringSlotToPakr(HashMap<Integer, String> parkingLot) {
        for (int parkingSlot = 1; parkingSlot <= parkingLot.size(); parkingSlot++) {
            if (parkingLot.get(parkingSlot) == null)
                return parkingSlot;
        }
        return null;
    }
}
