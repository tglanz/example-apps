package tglanz;

public class ParkingSpot {
    public enum ParkingSpotSize {
        Small,
        Medium,
        Large,
    }

    public final String name;
    public final ParkingSpotSize size;
    private Vehicle occupier;

    public ParkingSpot(String name, ParkingSpotSize size) {
        this.name = name;
        this.size = size;
        this.occupier = null;
    }

    public boolean isOccupied() {
        return occupier != null;
    }

    public void occupy(Vehicle vehicle) {
        // In a distributed setting, we need to lock this;

        if (isOccupied()) {
            throw new RuntimeException(String.format(
                "A vehicle with license plate \"%s\" already occupying the spot",
                occupier.licensePlate));
        }
        
        occupier = vehicle;
    }

    public void unoccupy() {
        // We might think about implementing an observer pattern here where other
        // vehicles can listen on.
        occupier = null;
    }
}
