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
    public void givenAVehicle_whenParked_ShouldReturnTrue() throws ParkingLotException {
        boolean isParked = parkingLotSystem.park(vehicle);
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_whenAlreadyParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SPACE,e.type);
        }
    }
}
