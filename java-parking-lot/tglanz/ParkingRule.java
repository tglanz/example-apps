package tglanz;

import java.util.Arrays;
import java.util.List;

import tglanz.ParkingSpot.ParkingSpotSize;

public interface ParkingRule {
    public boolean canPark(ParkingFloor floor, int spotIndex);

    public static class AnywhereParkingRule implements ParkingRule {
        public boolean canPark(ParkingFloor floor, int spotIndex) {
            return floor.getParkingSpot(spotIndex)
                .map(spot -> !spot.isOccupied())
                .orElse(false);
        }
    } 

    public static class SubsequentOfSizeParkingRule implements ParkingRule {
        private final ParkingSpotSize size;
        private int subsequentCount;

        public SubsequentOfSizeParkingRule(ParkingSpotSize size, int subsequentCount) {
            this.size = size;
            this.subsequentCount = subsequentCount;
        }

        public boolean canPark(ParkingFloor floor, int spotIndex) {
            if (spotIndex + subsequentCount > floor.getNumberOfParkingSpots()) {
                return false;
            }

            for (int i = spotIndex; i < spotIndex + subsequentCount; ++i) {
                if (!floor.getParkingSpot(i).map(spot -> !spot.isOccupied() && spot.size == size).orElse(false)) {
                    return false;
                }
            }

            return true;
        }
    } 

    public static class CompositeParkingRule implements ParkingRule {
        private final List<ParkingRule> components;

        public CompositeParkingRule(ParkingRule... components) {
            this.components = Arrays.asList(components);
        }

        public boolean canPark(ParkingFloor floor, int spotIndex) {
            for (ParkingRule rule : components) {
                if (rule.canPark(floor, spotIndex)) {
                    return true;
                }
            }

            return false;
        }
    }
}