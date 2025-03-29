package tglanz;

import java.util.List;
import java.util.Optional;

public class ParkingLot {
    private final List<ParkingFloor> floors;
    private final ParkingSpotFinder parkingSpotFinder;
    
    // @param parkingSpotFinder: A strategy to find a parking spot in a floor.
    // @param floors: A list of parking floors.
    public ParkingLot(List<ParkingFloor> floors, ParkingSpotFinder parkingSpotFinder) {
        this.parkingSpotFinder = parkingSpotFinder;
        this.floors = floors;
    }

    // Find up to one parking spot at each floor.
    public Optional<Pair<ParkingFloor, ParkingSpot>> findParkingSpot(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> spot = parkingSpotFinder.findSpotInFloor(vehicle, floor);
            if (spot.isPresent()) {
                return Optional.of(new Pair<>(floor, spot.get()));
            }
        }

        return Optional.empty();

        // return floors.stream()
        //     .map(floor -> parkingSpotFinder.findSpotInFloor(vehicle, floor))
        //     .filter(Optional::isPresent)
        //     .findFirst()
        //     .orElseGet(Optional::empty);
    }
}
