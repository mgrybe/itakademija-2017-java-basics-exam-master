package lt.itakademija.exam;

import java.util.List;

/**
 * Manages trucks and packets.
 * <p>
 * The main responsibility for this class is to register available truck and
 * assign packets to them for delivery.
 */
public interface TruckManager {

    /**
     * Registers a new truck with this manager.
     *
     * @param id       truck identifier
     * @param capacity truck capacity in cubic meters
     * @return registered truck instance
     */
    Truck registerTruck(String id, int capacity);

    /**
     * Returns all registered trucks.
     *
     * @return list of trucks
     */
    List<Truck> getTrucks();

    /**
     * Returns truck by its id.
     *
     * @param id truck identifier
     * @return registered truck
     */
    Truck getTruckById(String id);

    /**
     * Creates a packet.
     *
     * @param id     packet identifier
     * @param volume packet volume in cubic meters
     * @return created packet
     */
    Packet registerPacket(String id, int volume);

    /**
     * Assigns truck to packet.
     *
     * @param truck  a truck to assign
     * @param packet a packet waiting to be assigned a truck
     */
    void assignTruck(Truck truck, Packet packet);

    /**
     * Returns all packets assigned to the truck.
     *
     * @param truckId truck indentifier
     * @return a list of packets
     */
    List<Packet> getPackets(String truckId);

    /**
     * Returns a total truck capacity in cubic meters.
     * <p>
     * Counts total capacity considering all registered trucks.
     *
     * @return total capacity
     */
    int getTotalTruckCapacity();

}
