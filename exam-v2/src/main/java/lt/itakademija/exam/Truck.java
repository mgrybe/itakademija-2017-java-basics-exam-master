package lt.itakademija.exam;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class Truck {

    private final String id;

    private final int capacity;

    private int usedCapacity;

    private final List<Packet> packets;

    public Truck(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.packets = new LinkedList<>();
        this.usedCapacity = 0;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Packet> getPackets() {
        return packets;
    }

    void addPackage(Packet aPacket) {
        int newUsedCapacity = usedCapacity + aPacket.getVolume();
        if (newUsedCapacity > capacity) {
            throw new TruckCapacityOverflowException("Capacity=" + capacity + ", used=" + newUsedCapacity);
        }

        if (packets.add(aPacket)) {
            usedCapacity = newUsedCapacity;
        }
    }

    public void removePackage(Packet aPacket) {
        if (packets.remove(aPacket)) {
            usedCapacity -= aPacket.getVolume();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Truck other = (Truck) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Truck [id=" + id + ", capacity=" + capacity + "]";
    }

}
