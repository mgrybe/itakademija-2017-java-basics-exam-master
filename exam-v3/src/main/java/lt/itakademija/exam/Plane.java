package lt.itakademija.exam;

import java.util.*;

public final class Plane {

    private final String id;

    private final int seats;

    private final Map<Integer, Passenger> passengersMap;

    public Plane(String id, int seats) {
        this.id = id;
        this.seats = seats;
        this.passengersMap = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public int getSeats() {
        return seats;
    }

    public List<Passenger> getPassengers() {
        return new ArrayList<>(passengersMap.values());
    }

    public boolean isSeatOccupied(int seatNo) {
        return passengersMap.containsKey(seatNo);
    }

    public void registerPassenger(int seatNo, Passenger passenger) {
        passengersMap.put(seatNo, passenger);
        passenger.setSeatNo(seatNo);
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
        Plane other = (Plane) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Plane [id=" + id + ", seats=" + seats + ", passengersMap=" + passengersMap + "]";
    }

}
