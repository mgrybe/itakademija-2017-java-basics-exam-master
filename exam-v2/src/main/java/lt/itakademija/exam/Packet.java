package lt.itakademija.exam;

import java.util.Objects;

public final class Packet {

    private Truck assignedTruck;

    private final String id;

    private final int volume;

    public Packet(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public void assignTruck(Truck truck) {
        if (assignedTruck != null) {
            assignedTruck.removePackage(this);
        }

        truck.addPackage(this);
        this.assignedTruck = truck;
    }

    public Truck getAssignedTruck() {
        return assignedTruck;
    }

    public String getId() {
        return id;
    }

    public int getVolume() {
        return volume;
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
        Packet other = (Packet) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Package [assignedTruck=" + assignedTruck + ", id=" + id + ", volume=" + volume + "]";
    }

}
