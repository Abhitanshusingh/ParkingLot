package com.bridgelabz.utility;

public class ParkingBill {
    private final double COST_PER_MINUTE = 0.5;
    private static int arrivingHour;
    private static int departingHour;
    private static int arrivingMinute;
    private static int departingMinute;

    public void parkingTime(int arrivingHour, int arrivingMinute) {
        this.arrivingHour = arrivingHour;
        this.arrivingMinute = arrivingMinute;
    }

    public void unParkingTime(int departingHour, int departingMinute) {
        this.departingHour = departingHour;
        this.departingMinute = departingMinute;
    }

    public double generateParkingBill() {
        int hour = departingHour - arrivingHour;
        int min = departingMinute - arrivingMinute;
        double parkingBill = ((hour / 60) + min) * COST_PER_MINUTE;
        System.out.println("Hour: " + hour + " Minute: " + min);
        System.out.println("Parking Charges: " + parkingBill);
        return parkingBill;
    }
}
