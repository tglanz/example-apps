package tglanz;

import java.util.ArrayList;
import java.util.List;

import tglanz.ParkingSpot.ParkingSpotSize;

public interface ParkingSpotsLayout {
    public List<ParkingSpot> createParkingSpots(int numParkingSpots);

    public static class Uniform implements ParkingSpotsLayout {
        public List<ParkingSpot> createParkingSpots(int numParkingSpots) {
            List<ParkingSpot> ans = new ArrayList<>(numParkingSpots);

            for (int i = 0; i < numParkingSpots; ++i) {
                ParkingSpotSize size = ParkingSpotSize.Medium;

                if (i < numParkingSpots / 3) {
                    size = ParkingSpotSize.Small;
                } else if (i < numParkingSpots / 3 * 2) {
                    size = ParkingSpotSize.Medium;
                }

                String name = String.format("Spot %s", i);
                ans.add(new ParkingSpot(name, size));
            }

            return ans;
        }
   }
}
