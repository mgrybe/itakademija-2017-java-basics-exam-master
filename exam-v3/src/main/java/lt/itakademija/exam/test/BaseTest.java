package lt.itakademija.exam.test;

import lt.itakademija.exam.FlightManager;
import lt.itakademija.exam.Passenger;
import lt.itakademija.exam.Plane;
import lt.itakademija.exam.SeatIsOccupiedException;
import lt.itakademija.exam.grader.ExamTask;
import lt.itakademija.exam.grader.GradeEvaluatingRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(GradeEvaluatingRunner.class)
public abstract class BaseTest {

    private FlightManager sut;

    protected abstract FlightManager createFlightManager();

    @Before
    public void setUp() {
        this.sut = createFlightManager();
    }

    @Test
    @ExamTask(grade = 0.3)
    public final void flightManagerStartsCorrectly() {
        assertThat(this.sut, notNullValue());
    }

    @Test
    @ExamTask(grade = 0.1)
    public final void flightManagerRespondsWithNullPointerExceptionWhenCreatingPlaneWithNullArgs() {
        try {
            this.sut.createPlane(null, 1);
            fail("NullPointerException was expected");
        } catch (NullPointerException ignore) {
        }
    }

    @Test
    @ExamTask(grade = 0.1)
    public final void flightManagerRespondsWithIllegalArgumentExceptionWhenCreatingPlaneWithEmptyArgs() {
        try {
            this.sut.createPlane("", 1);
            fail("IllegalArgumentException was expected");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    @ExamTask(grade = 0.1)
    public final void flightManagerRespondsWithIllegalArgumentExceptionWhenCreatingPlaneWithZeroOrNegativeSeatCount() {
        try {
            this.sut.createPlane("PLANE-1", 0);
            fail("IllegalArgumentException was expected");
        } catch (IllegalArgumentException ignore) {
        }

        try {
            this.sut.createPlane("PLANE-1", -1);
            fail("IllegalArgumentException was expected");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    @ExamTask(grade = 0.1)
    public final void flightManagerRespondsWithIllegalArgumentExceptionWhenGettingPlaneByIdWithNullArg() {
        try {
            this.sut.getPlaneById(null);
            fail("NullPointerException was expected");
        } catch (NullPointerException ignore) {
        }
    }

    @Test
    @ExamTask(grade = 0.1)
    public final void flightManagerResponseWithNullPointerExceptionWhenCreatingPassengerWithNullArgs() {
        try {
            this.sut.createPassenger(null, "Test", 17);
            fail("NullPointerException was expected");
        } catch (NullPointerException ignore) {
        }

        try {
            this.sut.createPassenger("Test", null, 17);
            fail("NullPointerException was expected");
        } catch (NullPointerException ignore) {
        }
    }

    @Test
    @ExamTask(grade = 0.1)
    public final void flightManagerResponseWithIllegalArgumentExceptionWhenCreatingPassengerWithEmptyArgs() {
        try {
            this.sut.createPassenger("", "Test", 17);
            fail("IllegalArgumentException was expected");
        } catch (IllegalArgumentException ignore) {
        }

        try {
            this.sut.createPassenger("Test", "", 17);
            fail("IllegalArgumentException was expected");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test(expected = SeatIsOccupiedException.class)
    @ExamTask(grade = 0.3)
    public final void flightManagerRespondsWithSeatIsOccupiedExceptionIfOccupiedSeatIsAssignedToPassenger() throws Exception {
        // Setup
        Plane registeredPlane = this.sut.createPlane("PLANE-1", 1);
        Passenger registeredPassenger = this.sut.createPassenger("Testas", "Testaitis", 17);

        this.sut.registerPassenger(registeredPlane, 1, registeredPassenger);

        // Exercise & Verify
        this.sut.registerPassenger(registeredPlane, 1, registeredPassenger);
    }

    @Test
    @ExamTask(grade = 0.6)
    public final void flightManagerCanRegisterPlanes() {
        // Setup
        String id = "PLANE-1";
        int seats = 1;
        Plane plane = new Plane(id, seats);

        // Exercise
        Plane registeredPlane = this.sut.createPlane(id, seats);

        // Verify
        assertThat(registeredPlane, is(plane));
    }


    @Test
    @ExamTask(grade = 0.6)
    public final void flightManagerCanReturnPlaneById() {
        // Setup
        String id = "PLANE-1";
        int seats = 1;
        Plane plane = new Plane(id, seats);

        // Exercise
        this.sut.createPlane(id, seats);
        Plane foundPlane = this.sut.getPlaneById(id);

        // Verify
        assertThat(foundPlane, is(plane));
    }

    @Test
    @ExamTask(grade = 0.3)
    public final void flightManagerCanReturnAllRegisteredPlanes() {
        // Setup
        List<Plane> planes = new LinkedList<>();
        for (int i = 0; i < random(100); i++) {
            String planeId = "PLANE-" + i;
            planes.add(new Plane(planeId, 1));
            this.sut.createPlane(planeId, 1);
        }

        // Exercise
        Collection<Plane> createdPlanes = this.sut.getCreatedPlanes();

        // Verify
        assertThat(createdPlanes, hasItems(planes.toArray(new Plane[0])));
    }

    @Test
    @ExamTask(grade = 0.6)
    public final void flightManagerCanCreatePassengerObjects() {
        // Setup
        String name = "Testas";
        String surname = "Testaitis";
        int age = 17;
        Passenger passenger = new Passenger(name, surname, age);

        // Exercise
        Passenger registeredPassenger = this.sut.createPassenger(name, surname, age);

        // Verify
        assertThat(registeredPassenger, is(passenger));
    }

    @Test
    @ExamTask(grade = 0.7)
    public final void flightManagerCanReturnAllPassengersRegisteredToParticularPlane() throws Exception {
        // Setup
        int seats = random(100);

        this.sut.createPlane("PLANE-1", seats);
        String planeId = "PLANE-2";
        Plane registeredPlane = this.sut.createPlane(planeId, seats);

        List<Passenger> passengers = new ArrayList<>(seats);
        for (int i = 1; i <= seats; i++) {
            String name = "Name-" + i;
            String surname = "Surname-" + i;
            passengers.add(new Passenger(name, surname, 17));
            Passenger registeredPassenger = this.sut.createPassenger(name, surname, 17);
            this.sut.registerPassenger(registeredPlane, i, registeredPassenger);
        }

        // Exercise
        List<Passenger> planePassengers = this.sut.getPassengers(planeId);

        // Verify
        assertThat(planePassengers, is(passengers));
    }

    @Test
    @ExamTask(grade = 0.5)
    public final void flightManagerCanReturnTheOldestPassengerOnThePlane() throws Exception {
        // Setup
        int passengersCount = random(100);

        String planeId = "PLANE-1";
        Plane createdPlane = this.sut.createPlane(planeId, passengersCount);

        int oldestAge = passengersCount;
        for (int i = 1; i <= passengersCount; i++) {
            Passenger createdPassenger = this.sut.createPassenger("Name-" + i, "Surname-" + i, i);
            this.sut.registerPassenger(createdPlane, i, createdPassenger);
        }

        // Exercise
        Passenger oldestPassenger = this.sut.getOldestPassenger(planeId);

        // Verify
        assertThat(oldestPassenger.getAge(), is(oldestAge));
    }

    @Test
    @ExamTask(grade = 0.5)
    public final void flightManagerCanReturnTheAveragePassengersAgeOnThePlane() throws Exception {
        // Setup
        int passengersCount = random(100);

        String planeId = "PLANE-1";
        Plane createdPlane = this.sut.createPlane(planeId, passengersCount);

        int ageAccumulator = 0;
        for (int i = 1; i <= passengersCount; i++) {
            Passenger createdPassenger = this.sut.createPassenger("Name-" + i, "Surname-" + i, i);
            this.sut.registerPassenger(createdPlane, i, createdPassenger);
            ageAccumulator += i;
        }

        // Exercise
        double averageAge = this.sut.getAveragePassengerAge(planeId);

        // Verify
        assertThat(averageAge, is(ageAccumulator / (double) passengersCount));
    }

    private int random(int maxValue) {
        return 1 + (int) (Math.random() * maxValue);
    }

}
