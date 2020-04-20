package com.bridgelabz.utility;

import java.util.HashMap;

public class ParkingLotAttendant {

    public static HashMap<Integer, String> park(HashMap<Integer, String> carParkedDetail, String vehicleName) {
        Integer emptyParkingSlot = ParkingLotOwner.getPakringSlotToPakr(carParkedDetail);
        carParkedDetail.put(emptyParkingSlot, vehicleName);
        return carParkedDetail;
    }
}