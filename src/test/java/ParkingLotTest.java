import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.service.ParkingLotSystem;
import com.bridgelabz.utility.AirportSecurity;
import com.bridgelabz.utility.ParkingLotOwner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    ParkingLotOwner parkingLotOwner = null;
    AirportSecurity airportSecurity = null;

    @Before
    public void SetUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(3);
        vehicle = new Object();
        parkingLotOwner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
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
        parkingLotSystem.unPark("Mustang Dodge Demon");
        boolean isVehicle = parkingLotSystem.isVehicleParked();
        Assert.assertFalse(isVehicle);
    }

    @Test
    public void givenNoVehicle_whenUnParked_shouldThrowException() {
        try {
            parkingLotSystem.unPark("Mahindra Thar");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, e.type);
        }
    }

    @Test
    public void givenAVehicle_whenUnParkedDifferentVehicle_shouldThrowException() throws ParkingLotException {
        try {
            parkingLotSystem.park("Mustang GT500");
            parkingLotSystem.unPark("Mahindra thar");
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

    @Test
    public void givenParkringLot_whenParkingLotFull_shouldShowFullSign() throws ParkingLotException {
        parkingLotSystem.register(parkingLotOwner);
        parkingLotSystem.park("Mustang Dodge Demon");
        parkingLotSystem.park("Mustang GT500");
        parkingLotSystem.park("Mustang Ecoboost");
        Assert.assertEquals(parkingLotOwner.getSign(), ParkingLotOwner.Sign.PARKING_IS_FULL);
    }

    @Test
    public void givenParkintLot_whenParkingLotVacant_shouldShowVacantSign() throws ParkingLotException {
        parkingLotSystem.register(parkingLotOwner);
        parkingLotSystem.park("Classic Jeep");
        parkingLotSystem.park("Mahindra thar");
        Assert.assertEquals(parkingLotOwner.getSign(), ParkingLotOwner.Sign.PARKING_IS_VACANT);
    }

    @Test
    public void givenParkingLot_whenParkingLotFull_securityStaffShouldBeUpdate() throws ParkingLotException {
        parkingLotSystem.register(airportSecurity);
        parkingLotSystem.park("Dorge Challenger");
        parkingLotSystem.park("Chevrolet camaro LT");
        parkingLotSystem.park("Ford Mustang Ecoboost");
        Assert.assertTrue(airportSecurity.isParkingFull());
    }

    @Test
    public void givenParkingLot_whenSpace_securityStaffShouldBeUpdate() throws ParkingLotException {
        parkingLotSystem.register(airportSecurity);
        parkingLotSystem.park("Mustang GT500");
        parkingLotSystem.park("Mahindra thar");
        Assert.assertFalse(airportSecurity.isParkingFull());
    }

    @Test
    public void givenParkingLot_whenParkingLotHaveSpace_ownerPutVacantSign() throws ParkingLotException {
        parkingLotSystem.register(parkingLotOwner);
        parkingLotSystem.park("Dorge Challenger");
        parkingLotSystem.park("Chevrolet camaro LT");
        parkingLotSystem.unPark("Dorge Challenger");
        parkingLotSystem.park("Mustang GT500");
    }

    @Test
    public void givenVehicle_GetInParkingLot_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park("Mustang Dodge Demon");
        parkingLotSystem.park("Mustang GT500");
        parkingLotSystem.park("Mustang Ecoboost");
        boolean isPresent = parkingLotSystem.isVehicleInParkingLot("Mustang GT500");
        Assert.assertTrue(isPresent);
    }

    @Test
    public void givenVehicle_whenNotGetInParkingLot_ShouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park("Mustang Dodge Demon");
        parkingLotSystem.park("Mustang GT500");
        parkingLotSystem.park("Mustang Ecoboost");
        boolean isPresent = parkingLotSystem.isVehicleInParkingLot("Mustang GT600");
        Assert.assertFalse(isPresent);
    }
}
