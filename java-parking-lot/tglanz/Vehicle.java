package tglanz;

public class Vehicle {
    public final String licensePlate;
    public final String type;
    private final ParkingRule parkingRule;

    public Vehicle(String type, String licensePlate, ParkingRule parkingRule) {
        assert parkingRule != null;
        this.type = type;
        this.licensePlate = licensePlate;
        this.parkingRule = parkingRule;
    }

    public boolean canPark(ParkingFloor floor, int spotIndex) {
        assert floor != null;
        return parkingRule.canPark(floor, spotIndex);
    }

    public void park(ParkingSpot spot) {
        assert spot != null;

        // We can have just returned false here;
        // It is reasonable IMO to throw in this case because our system should validate before
        // trying to park and thus this is an invalid state.
        if (spot.isOccupied()) {
            throw new RuntimeException("Attempted to park in an occupied spot");
        }

        spot.occupy(this);
    }
}