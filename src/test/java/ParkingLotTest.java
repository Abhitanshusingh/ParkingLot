import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @Before
    public void SetUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(3);
        vehicle = new Object();
    }

    @Test
    public void givenAVehicle_whenParked_shouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park("Mustang GT500");
        boolean isParked = parkingLotSystem.isVehicleParked();
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_whenUnParked_shouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park("Mustang Dodge Demon");
        parkingLotSystem.Unpark("Mustang Dodge Demon");
        boolean isVehicle = parkingLotSystem.isVehicleParked();
        Assert.assertFalse(isVehicle);
    }

    @Test
    public void givenNoVehicle_whenUnParked_shouldThrowException() {
        try {
            parkingLotSystem.Unpark("Mahindra Thar");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, e.type);
        }
    }

    @Test
    public void givenAVehicle_whenUnParkedDifferentVehicle_shouldThrowException() throws ParkingLotException {
        try {
            parkingLotSystem.park("Mustang GT500");
            parkingLotSystem.Unpark("Mahindra thar");
        } catch (ParkingLotException e) {
            Assert.assertEquals("Vehicle is not in parking lot", e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_whenParkingLotFull_shouldReturnMessage() {
        try {
            parkingLotSystem.park("Dorge Challenger");
            parkingLotSystem.park("Chevrolet camaro LT");
            parkingLotSystem.park("Ford Mustang Ecoboost");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SPACE, "Parking lot is full");
        }
    }
}
