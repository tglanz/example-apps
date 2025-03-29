package tglanz;

import java.util.Optional;

// This class is responsible for finding a parking spot for a vehicle in a parking floor.
// It encapsulates the logic of searching for a spot, allowing the ParkingLot class to focus on managing the parking lot as a whole.
// It could have been modified to be an interface which would allow for different strategies to be used for finding parking spots in a floor.
// But it is not necessary for this example, so it is kept as a concrete class.
// If one day we need to change the way we find parking spots, we can do it here without modifying any class.
public class ParkingSpotFinder {
    public Optional<ParkingSpot> findSpotInFloor(Vehicle vehicle, ParkingFloor floor) {
        for (int i = 0; i < floor.getNumberOfParkingSpots(); i++) {
            Optional<ParkingSpot> spot = floor.getParkingSpot(i);
            if (spot.isPresent() && vehicle.canPark(floor, i)) {
                return spot;
            }
        }

        return Optional.empty();
    }
}
