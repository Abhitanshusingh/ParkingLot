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
        parkingLotSystem = new ParkingLotSystem();
        vehicle = new Object();
    }

    @Test
    public void givenAVehicle_whenParked_shouldReturnTrue() throws ParkingLotException {
        boolean isParked = parkingLotSystem.park(vehicle);
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_whenAlreadyParked_shouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SPACE, e.type);
        }
    }

    @Test
    public void givenAVehicle_whenUnParked_shouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.Unpark(vehicle);
        Assert.assertTrue(isUnParked);
    }

    @Test
    public void givenNoVehicle_whenUnParked_shouldReturnFalse() {
        try {
            parkingLotSystem.Unpark(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, e.type);
        }
    }

    @Test
    public void givenAVehicle_whenUnParkedDifferentVehicle_shouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park(new Object());
        boolean isUnParked = parkingLotSystem.Unpark(vehicle);
        Assert.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_whenParkingLotFull_shouldReturnMessage() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals("full", ParkingLotSystem.parkingLotFull);
        }
    }
}
