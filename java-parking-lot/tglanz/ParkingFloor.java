package tglanz;

import java.util.List;
import java.util.Optional;

public class ParkingFloor {

    public final String name;
    private final List<ParkingSpot> parkingSpots;

    private ParkingFloor(String name, List<ParkingSpot> parkingSpots) {
        this.name = name;
        this.parkingSpots = parkingSpots;
    }

    public static ParkingFloor fromLayout(String name, ParkingSpotsLayout layout, int numParkingSpots) {
        return new ParkingFloor(
            name, 
            layout.createParkingSpots(numParkingSpots));
    }

    public int getNumberOfParkingSpots() {
        return parkingSpots.size();
    }

    public Optional<ParkingSpot> getParkingSpot(int index) {
        assert index >= 0;
        if (index < getNumberOfParkingSpots()) {
            return Optional.of(parkingSpots.get(index));
        }

        return Optional.empty();
    }
}
