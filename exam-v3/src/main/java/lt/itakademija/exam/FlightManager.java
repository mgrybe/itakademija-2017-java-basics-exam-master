package lt.itakademija.exam;

import java.util.List;

public interface FlightManager {

    /**
     * Creates a new plane.
     *
     * @param id    plane identifier
     * @param seats number of seats in the plane
     * @return registered plane instance
     */
    Plane createPlane(String id, int seats);

    /**
     * Returns all registered planes.
     *
     * @return list of planes
     */
    List<Plane> getCreatedPlanes();

    /**
     * Returns a plane by its id.
     *
     * @param id plane identifier
     * @return registered plane
     */
    Plane getPlaneById(String id);

    /**
     * Creates a passenger.
     *
     * @param name    passenger's name
     * @param surname passenger's surname
     * @param age     passenger's age
     * @return passenger object
     */
    Passenger createPassenger(String name, String surname, int age);

    /**
     * Assigns a passenger to a plane.
     *
     * @param plane     a plane
     * @param seatNo    a seat number (starting from 1)
     * @param passenger a passenger
     */
    void registerPassenger(Plane plane, int seatNo, Passenger passenger) throws SeatIsOccupiedException;

    /**
     * Returns all passengers assigned to the plane.
     *
     * @param planeId a plane identifier
     * @return a list of passengers
     */
    List<Passenger> getPassengers(String planeId);

    /**
     * Returns the oldest passenger on the plane or null if no passengers exist.
     *
     * @param planeId plane identifier
     * @return the oldest passenger
     */
    Passenger getOldestPassenger(String planeId);

    /**
     * Returns the average passengers' age on the plane.
     *
     * @param planeId plane identifier
     * @return the average passengers' age
     */
    double getAveragePassengerAge(String planeId);

}
