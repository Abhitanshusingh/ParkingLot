package com.bridgelabz.entity;

public class ParkingLot {
    public int lotID;

    public ParkingLot(int lotId) {
        this.lotID = lotId;
    }

    @Override
    public String toString() {
        return "lotID=" + lotID;
    }
}
