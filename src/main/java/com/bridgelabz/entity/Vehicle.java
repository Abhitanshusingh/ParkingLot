package com.bridgelabz.entity;

import java.util.Objects;

public class Vehicle {
    public String name;
    public int plateNumber;
    public String brand;
    public String colour;

    public Vehicle(String name, int plateNumber, String brand, String colour) {
        this.name = name;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", plateNumber=" + plateNumber +
                ", brand='" + brand + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return plateNumber == vehicle.plateNumber &&
                Objects.equals(name, vehicle.name) &&
                Objects.equals(brand, vehicle.brand) &&
                Objects.equals(colour, vehicle.colour);
    }
}
