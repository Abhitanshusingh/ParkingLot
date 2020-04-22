package com.bridgelabz.utility;

import java.util.HashMap;
import java.util.Iterator;

public class ParkingLotAttendant {

    public static HashMap<Integer, String> attendantPark(HashMap<Integer, String> carParkedDetail, String vehicleName) {
        Integer emptyParkingSlot = ParkingLotOwner.getPakringSlotToPakr(carParkedDetail);
        carParkedDetail.put(emptyParkingSlot, vehicleName);
        return carParkedDetail;
    }

    public static HashMap<Integer, String> attendantUnPark(HashMap<Integer, String> vehicleParkedDetail, String vehicleName) {
        Iterator<String> parkingLotIterator = vehicleParkedDetail.values().iterator();
        while (parkingLotIterator.hasNext()) {
            if (parkingLotIterator.next().equals(vehicleName)) {
                parkingLotIterator.remove();
                return vehicleParkedDetail;
            }
        }
        return null;
    }
}