package lt.itakademija.vilbertas;

import lt.itakademija.exam.Packet;
import lt.itakademija.exam.Truck;
import lt.itakademija.exam.TruckManager;
//import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 
 */

/**
 * @author vilbertas
 *
 */
public class TruckManagerImplement implements TruckManager {

	private Truck truck;
	private Packet packet;
	private List<Truck> truckList = new ArrayList<>();
	private List<Packet> packetList = new ArrayList<>();

	@Override
	/**
	 * Assigns Package to the Truck
	 */
	public void assignTruck(Truck truck, Packet packet) {
		// this.packet=packet;
		// this.truck=truck;
		// packet.getAssignedTruck().equals(truck);
		// for (Packet packet : packetList) {
		packet.assignTruck(truck);
		// }

	}
/**
 * Gets all package list
 */
	@Override
	public List<Packet> getPackets(String truckId) {
		// List<Packet> listById = new ArrayList<>();
		//
		// for (Packet packet : packetList) {
		// if (truckId.equals(packet.getId())) {
		// listById.add(packet);
		// }
		//
		// }

		return packetList;
	}

	/**
	 * Calculates all capacity of trucks
	 */
	@Override
	public int getTotalTruckCapacity() {
		int totalTruckCapacity = 0;
		for (Truck truck : truckList) {
			totalTruckCapacity += truck.getCapacity();
		}

		return totalTruckCapacity;
	}
/**
 * Returns all trucks by its id
 */
	@Override
	public Truck getTruckById(String id) {
		Truck lookForTruck = null;
		if (id.equals(truck.getId())) {
			lookForTruck = truck;
		}
		return lookForTruck;
	}
	/**
	 * Gets all truck list
	 */
	@Override
	public List<Truck> getTrucks() {

		return truckList;
	}
/**
 * Registers new Packet
 */
	@Override
	public Packet registerPacket(String packetId, int volume) {
		if (volume <= 0 || packetId.isEmpty()) {
			throw new IllegalArgumentException();
		}

		packet = new Packet(packetId, volume);
		packetList.add(packet);
		return packet;
	}
	/**
	 * Register new Packet
	 */
	@Override
	public Truck registerTruck(String truckId, int capacity) {

		if (capacity <= 0 || truckId.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (truckId.isEmpty()) {
			throw new NullPointerException();
		}

		//PropertyConfigurator.configure(getClass().getResource("/log4j.properties"));

		Logger logger = Logger.getAnonymousLogger(); // Logger.getRootLogger
														// must be used instead
														// of
														// .getAnonymousLogger,
														// but it cannot be
														// found

		truck = new Truck(truckId, capacity);
		truckList.add(truck);

		logger.info(String.format("New truck was added with capacity of %s", truck.getCapacity()));
		return truck;

	}
}
