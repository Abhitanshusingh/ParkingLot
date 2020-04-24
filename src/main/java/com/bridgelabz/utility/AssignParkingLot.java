package com.bridgelabz.utility;

public class AssignParkingLot {
    public int parkingLotCapacity = 0;
    public static int lotNumber = 0;
    public static int noOfParkingLots = 0;

    public AssignParkingLot(int parkingLotCapacity, int noOfParkingLots) {
        this.parkingLotCapacity = parkingLotCapacity;
        this.noOfParkingLots = noOfParkingLots;
    }

    public int getNoOfSlotsPerLot() {
        return parkingLotCapacity / noOfParkingLots;
    }

    public static int assignLot(int slotID) {
        switch (slotID % noOfParkingLots) {
            case 0:
                lotNumber = 4;
                break;
            case 1:
                lotNumber = 1;
                break;
            case 2:
                lotNumber = 2;
                break;
            case 3:
                lotNumber = 3;
        }
        return lotNumber;
    }
}
