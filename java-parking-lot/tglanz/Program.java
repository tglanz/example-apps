package tglanz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import tglanz.VehicleFactory.VehicleType;

class Program {
    // All code here is a mock and is not meant to be used in production.
    // It is just a demonstration of how the classes interact with each other.
    // Therefore, code which is found here should not be extracted or modularized.
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory();
        ParkingLot parkingLot = initializeDefaultParkingLot();

        List<Vehicle> vehicles = new ArrayList<>() {{
            add(vehicleFactory.create(VehicleType.Car, "some car"));
            add(vehicleFactory.create(VehicleType.Bus, "some-bus"));
            add(vehicleFactory.create(VehicleType.Bus, "some-bus-2"));
            add(vehicleFactory.create(VehicleType.Bus, "some-bus-3"));
            add(vehicleFactory.create(VehicleType.Motorcycle, "some-motorcycle"));
            add(vehicleFactory.create(VehicleType.Motorcycle, "some-motorcycle-1"));
            add(vehicleFactory.create(VehicleType.Motorcycle, "some-motorcycle-2"));
            add(vehicleFactory.create(VehicleType.Motorcycle, "some-motorcycle-3"));
            add(vehicleFactory.create(VehicleType.Motorcycle, "some-motorcycle-4"));
        }};

        for (Vehicle vehicle : vehicles) {
            findAndPark(parkingLot, vehicle);
        }
    }

    static ParkingLot initializeDefaultParkingLot() {
        ParkingSpotsLayout layout = new ParkingSpotsLayout.Uniform();
        List<ParkingFloor> floors = IntStream.range(0, 3).boxed()
            .map(i -> ParkingFloor.fromLayout(
                String.format("Floor %s", i),
                layout,
                10))
            .collect(Collectors.toList());
        return new ParkingLot(floors, new ParkingSpotFinder());
    }

    private static void findAndPark(ParkingLot parkingLot, Vehicle vehicle) {
        System.out.printf("Vehicle: %s, %s\n", vehicle.type, vehicle.licensePlate);
        Optional<Pair<ParkingFloor, ParkingSpot>> maybeFound = parkingLot.findParkingSpot(vehicle);
        maybeFound.ifPresentOrElse(
            found -> {
                System.out.printf("  - Found spot in floor %s, spot %s\n", 
                    found.first.name, found.second.name);
                vehicle.park(found.second);
            }, 
            () -> System.out.println("  - No available spots :("));
    }
}