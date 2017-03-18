package lt.itakademija.exam;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FlightManagerImpl implements FlightManager {

    private final List<Plane> createdPlanes = new LinkedList<>();

    @Override
    public Plane createPlane(String id, int seats) {
        Objects.requireNonNull(id);
        if (id.isEmpty() || seats <= 0) {
            throw new IllegalArgumentException();
        }

        Plane plane = new Plane(id, seats);
        createdPlanes.add(plane);
        return plane;
    }

    @Override
    public List<Plane> getCreatedPlanes() {
        return createdPlanes;
    }

    @Override
    public Plane getPlaneById(String id) {
        Objects.requireNonNull(id);

        for (Plane plane : createdPlanes) {
            if (plane.getId().equals(id)) {
                return plane;
            }
        }
        return null;
    }

    @Override
    public Passenger createPassenger(String name, String surname, int age) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(surname);

        if (name.isEmpty() || surname.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return new Passenger(name, surname, age);
    }

    @Override
    public void registerPassenger(Plane plane, int seatNo, Passenger passenger) throws SeatIsOccupiedException {
        if (plane.isSeatOccupied(seatNo)) {
            throw new SeatIsOccupiedException();
        }
        plane.registerPassenger(seatNo, passenger);
    }

    @Override
    public List<Passenger> getPassengers(String planeId) {
        Plane planeById = getPlaneById(planeId);
        if (planeById != null) {
            return planeById.getPassengers();
        }
        return Collections.emptyList();
    }

    @Override
    public Passenger getOldestPassenger(String planeId) {
        return getPassengers(planeId).stream().max((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())).orElse(null);
    }

    @Override
    public double getAveragePassengerAge(String planeId) {
        return getPassengers(planeId).stream().mapToDouble((p1) -> p1.getAge()).average().orElse(0.0d);
    }

}
