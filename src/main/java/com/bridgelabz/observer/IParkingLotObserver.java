package com.bridgelabz.observer;

import java.util.HashMap;

public interface IParkingLotObserver {
    public void sendParkingStatus(HashMap<Integer, String> vehicleParkedDetail);
}
