import com.bridgelabz.entity.Slot;
import com.bridgelabz.entity.Vehicle;
import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.service.ParkingLotSystem;
import com.bridgelabz.utility.AirportSecurity;
import com.bridgelabz.utility.ParkingLotOwner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Map;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    ParkingLotOwner parkingLotOwner = null;
    AirportSecurity airportSecurity = null;
    int parkingSlot = 0;
    public final int PARKING_LOT_CAPACITY = 100;
    Vehicle vehicle1 = new Vehicle("Mustang GT500", 1234, "Ford", "Black");
    Vehicle vehicle2 = new Vehicle("Thar", 5555, "Mahindra", "Black");
    Vehicle vehicle3 = new Vehicle("Camaro LT", 9999, "Chevrolet", "White");
    Vehicle vehicle4 = new Vehicle("Chiron", 1212, "Bugatti", "White");

    @Before
    public void SetUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(100, 4);
        vehicle = new Object();
        parkingLotOwner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenAVehicle_whenParked_shouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        Assert.assertTrue(parkingLotSystem.isVehiclePresentInLot(vehicle1));
    }

    @Test
    public void givenAVehicle_whenUnParked_shouldReturnFalse() throws ParkingLotException {
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.unPark(vehicle2);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Vehicle is not in parking lot", e.getMessage());
        }
    }

    @Test
    public void givenNoVehicle_whenUnParked_shouldThrowException() {
        try {
            parkingLotSystem.unPark(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, e.type);
        }
    }

    @Test
    public void givenAVehicle_whenUnParkedDifferentVehicle_shouldThrowException() throws ParkingLotException {
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.unPark(vehicle2);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, e.type);
        }
    }

    @Test
    public void givenAVehicle_whenParkingLotFull_shouldThorowException() {
        try {
            while (parkingSlot <= PARKING_LOT_CAPACITY) {
                parkingLotSystem.park(vehicle1);
                parkingSlot++;
            }
        } catch (ParkingLotException e) {
            Assert.assertEquals(e.getMessage(), "Parking lot is full");
        }
    }

    @Test
    public void givenParkringLot_whenParkingLotFull_shouldShowFullSign() throws ParkingLotException {
        parkingLotSystem.register(parkingLotOwner);
        while (parkingSlot < PARKING_LOT_CAPACITY) {
            parkingLotSystem.park(vehicle1);
            parkingSlot++;
        }
        Assert.assertEquals(parkingLotOwner.getSign(), ParkingLotOwner.Sign.PARKING_IS_FULL);
    }

    @Test
    public void givenParkingLot_whenParkingLotFull_securityStaffShouldBeUpdate() throws ParkingLotException {
        parkingLotSystem.register(airportSecurity);
        while (parkingSlot <= PARKING_LOT_CAPACITY) {
            parkingLotSystem.park(vehicle1);
            parkingSlot++;
        }
        Assert.assertTrue(airportSecurity.isParkingFull());
    }

    @Test
    public void givenParkingLot_whenSpace_securityStaffShouldBeUpdate() throws ParkingLotException {
        parkingLotSystem.register(airportSecurity);
        while (parkingSlot <= PARKING_LOT_CAPACITY) {
            parkingLotSystem.park(vehicle1);
            parkingSlot++;
        }
        Assert.assertFalse(airportSecurity.isParkingFull());
    }

    @Test
    public void givenParkingLot_whenParkingLotHaveSpace_ownerPutVacantSign() throws ParkingLotException {
        parkingLotSystem.register(parkingLotOwner);
        while (parkingSlot < 100) {
            parkingLotSystem.park(vehicle1);
            parkingSlot++;
        }
        parkingLotSystem.unPark(vehicle1);
        Assert.assertEquals(ParkingLotOwner.Sign.PARKING_IS_VACANT, parkingLotOwner.getSign());
    }

    @Test
    public void givenAVehicle_GetInParkingLot_shouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        Assert.assertTrue(parkingLotSystem.isVehiclePresentInLot(vehicle2));
    }

    @Test
    public void givenAVehicle_whenNotGetInParkingLot_shouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        Assert.assertFalse(parkingLotSystem.isVehiclePresentInLot(vehicle4));
    }

    @Test
    public void givenAVehicle_whenParkedAndThenUnparked_shouldReturnTotalTimeParked() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        Assert.assertEquals(parkingLotSystem.getArrivalTime(vehicle1), LocalTime.of(12, 10, 20));
    }

    @Test
    public void givenManyVehicles_WhenParkedEvenly_ShouldReturnPosition() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        for (Map.Entry<Slot, Vehicle> entry : parkingLotSystem.vehicleParkedDetail.entrySet()) {
            if (entry.getValue().equals(vehicle1)) {
                Assert.assertEquals(1, entry.getKey().lot.lotID);
                Assert.assertEquals(1, entry.getKey().slotID);
            }
            if (entry.getValue().equals(vehicle2)) {
                Assert.assertEquals(2, entry.getKey().lot.lotID);
                Assert.assertEquals(2, entry.getKey().slotID);
            }
            if (entry.getValue().equals(vehicle3)) {
                Assert.assertEquals(3, entry.getKey().lot.lotID);
                Assert.assertEquals(3, entry.getKey().slotID);
            }
            if (entry.getValue().equals(vehicle4)) {
                Assert.assertEquals(4, entry.getKey().lot.lotID);
                Assert.assertEquals(4, entry.getKey().slotID);
            }
        }
    }
}
