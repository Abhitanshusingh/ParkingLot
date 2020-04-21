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

    public static Integer getPakringSlotToPakr(HashMap<Integer, String> vehicleParkedDetail) {
        for (int parkingSlots = 1; parkingSlots <= vehicleParkedDetail.size(); parkingSlots++) {
            if (vehicleParkedDetail.get(parkingSlots) == null)
                return parkingSlots;
        }
        return null;
    }
}
