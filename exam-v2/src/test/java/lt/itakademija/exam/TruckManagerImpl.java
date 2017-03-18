package lt.itakademija.exam;

import java.util.*;

public final class TruckManagerImpl implements TruckManager {

    private final Map<String, Truck> trucksMap = new HashMap<>();

    @Override
    public Truck registerTruck(String id, int capacity) {
        Objects.requireNonNull(id);
        if (id.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }

        Truck truck = new Truck(id, capacity);
        trucksMap.put(id, truck);
        return truck;
    }

    @Override
    public List<Truck> getTrucks() {
        return new ArrayList<>(trucksMap.values());
    }

    @Override
    public Truck getTruckById(String id) {
        return trucksMap.get(id);
    }

    @Override
    public Packet registerPacket(String name, int volume) {
        Objects.requireNonNull(name);
        if (name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (volume <= 0) {
            throw new IllegalArgumentException();
        }

        Packet aPackage = new Packet(name, volume);
        return aPackage;
    }

    @Override
    public void assignTruck(Truck truck, Packet aPackage) {
        aPackage.assignTruck(truck);
    }

    @Override
    public List<Packet> getPackets(String truckId) {
        return trucksMap.get(truckId).getPackets();
    }

    @Override
    public int getTotalTruckCapacity() {
        int totalCapacity = 0;
        for (Truck truck : trucksMap.values()) {
            totalCapacity += truck.getCapacity();
        }
        return totalCapacity;
    }

}
