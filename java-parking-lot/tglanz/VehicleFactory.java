package tglanz;

import tglanz.ParkingSpot.ParkingSpotSize;

public class VehicleFactory {

    public enum VehicleType {
        Motorcycle,
        Car,
        Bus,
    }

    public Vehicle create(VehicleType type, String licensePlate) {
        switch (type) {
            case Motorcycle: return createMotorcycle(licensePlate);
            case Car: return createCar(licensePlate);
            case Bus: return createBus(licensePlate);
        }

        throw new RuntimeException(String.format(
            "Unimplemented factory for VehicleType %s", type));
    }

    private Vehicle createMotorcycle(String licensePlate) {
        return new Vehicle("Motorcycle", licensePlate, new ParkingRule.AnywhereParkingRule());
    }

    private Vehicle createCar(String licensePlate) {
        return new Vehicle("Car", licensePlate, new ParkingRule.CompositeParkingRule(
            new ParkingRule.SubsequentOfSizeParkingRule(ParkingSpotSize.Medium, 1),
            new ParkingRule.SubsequentOfSizeParkingRule(ParkingSpotSize.Large, 1)
        ));
    }

    private Vehicle createBus(String licensePlate) {
        return new Vehicle("Bus", licensePlate, new ParkingRule.CompositeParkingRule(
            new ParkingRule.SubsequentOfSizeParkingRule(ParkingSpotSize.Medium, 7),
            new ParkingRule.SubsequentOfSizeParkingRule(ParkingSpotSize.Large, 5)
        ));
    }

    // Taxi, parking types, or maybe specific indices...
    // Handicapped, closes to entrance, etc...
}
