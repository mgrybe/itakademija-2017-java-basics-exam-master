package lt.itakademija.exam.test;

import lt.itakademija.exam.Packet;
import lt.itakademija.exam.Truck;
import lt.itakademija.exam.TruckManager;
import lt.itakademija.exam.grader.ExamTask;
import lt.itakademija.exam.grader.GradeEvaluatingRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(GradeEvaluatingRunner.class)
public abstract class BaseTest {

    protected abstract TruckManager createTransportManager();

    private TruckManager sut;

    @Before
    public void setUp() {
        this.sut = createTransportManager();
    }

    @Test
    @ExamTask(grade = 0.3)
    public final void truckManagerStartsCorrectly() {
        Assert.assertThat(this.sut, notNullValue());
    }

    @Test(expected = NullPointerException.class)
    @ExamTask(grade = 0.1)
    public final void truckManagerRespondsWithNullPointerExceptionWhenRegisteringTruckWithNullId() {
        this.sut.registerTruck(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    @ExamTask(grade = 0.1)
    public final void truckManagerRespondsWithIllegalArgumentExceptionWhenRegisteringTruckWithEmptyId() {
        this.sut.registerTruck("", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    @ExamTask(grade = 0.1)
    public final void truckManagerRespondsWithIllegalArgumentExceptionWhenRegisteringTruckWithZeroCapacity() {
        this.sut.registerTruck("TRUCK-1", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    @ExamTask(grade = 0.1)
    public final void truckManagerRespondsWithIllegalArgumentExceptionWhenRegisteringTruckWithNegativeCapacity() {
        this.sut.registerTruck("TRUCK-1", -1);
    }

    @Test(expected = NullPointerException.class)
    @ExamTask(grade = 0.1)
    public final void truckManagerRespondsWithNullPointerExceptionWhenRegisteringPackageWithNullId() {
        this.sut.registerPacket(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    @ExamTask(grade = 0.1)
    public final void truckManagerRespondsWithIllegalArgumentExceptionWhenRegisteringPackageWithEmptyId() {
        this.sut.registerPacket("", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    @ExamTask(grade = 0.1)
    public final void truckManagerRespondsWithIllegalArgumentExceptionWhenRegisteringPackageWithZeroCapacity() {
        this.sut.registerPacket("TRUCK-1", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    @ExamTask(grade = 0.1)
    public final void truckManagerRespondsWithIllegalArgumentExceptionWhenRegisteringPackageWithNegativeCapacity() {
        this.sut.registerPacket("TRUCK-1", -1);
    }

    @Test
    @ExamTask(grade = 0.6)
    public final void truckManagerCanRegisterTrucks() {
        // Setup
        String truckId = "TRUCK-1";
        int capacity = 1;
        Truck expectedTruck = new Truck(truckId, capacity);

        // Exercise
        Truck registeredTruck = this.sut.registerTruck(truckId, capacity);

        // Verify
        assertThat(registeredTruck, is(expectedTruck));
    }

    @Test
    @ExamTask(grade = 0.6)
    public final void truckManagerCanReturnTruckById() {
        // Setup
        String truckId = "TRUCK-1";
        int capacity = 1;
        Truck expectedTruck = new Truck(truckId, capacity);

        // Exercise
        this.sut.registerTruck(truckId, capacity);
        Truck registeredTruck = this.sut.getTruckById(truckId);

        // Verify
        assertThat(registeredTruck, is(expectedTruck));
    }

    @Test
    @ExamTask(grade = 0.6)
    public final void truckManagerCanReturnAllRegisteredTrucks() {
        // Setup
        int capacity = 1;

        List<Truck> expectedTrucks = new LinkedList<>();
        for (int i = 0; i < 10 + random(100); i++) {
            String truckId = "TRUCK-" + i;


            expectedTrucks.add(new Truck(truckId, capacity));
            this.sut.registerTruck(truckId, capacity);
        }

        // Exercise
        List<Truck> registeredTrucks = this.sut.getTrucks();

        // Verify
        assertThat(registeredTrucks, hasItems(expectedTrucks.toArray(new Truck[0])));
    }

    @Test
    @ExamTask(grade = 0.6)
    public final void truckManageCanRegisterPackages() {
        // Setup
        String packageName = "Apples";
        int volume = 1;
        Packet expectedPackage = new Packet(packageName, volume);

        // Exercise
        Packet registeredPackage = this.sut.registerPacket(packageName, volume);

        // Verify
        assertThat(registeredPackage, is(expectedPackage));
    }

    @Test
    @ExamTask(grade = 0.6)
    public final void truckManagerCanAssignPackageToTruck() {
        // Setup
        String packageName = "Apples";
        int volume = 1;

        // Exercise
        Packet registeredPackage = this.sut.registerPacket(packageName, volume);
        Truck registeredTruck = this.sut.registerTruck("TRUCK-1", 1);
        this.sut.assignTruck(registeredTruck, registeredPackage);

        // Verify
        assertThat(registeredTruck, is(registeredPackage.getAssignedTruck()));
    }

    @Test
    @ExamTask(grade = 0.3)
    public final void truckManagerCanReturnAllPackagesAssignedToTruck() {
        // Setup
        Truck registeredTruck = this.sut.registerTruck("TRUCK-1", 100);

        List<Packet> expectedPackages = new LinkedList<>();
        int packageCount = random(100);
        for (int i = 0; i < packageCount; i++) {
            String packageName = "package-" + i;
            expectedPackages.add(new Packet(packageName, 1));
        }

        // Exercise
        for (int i = 0; i < packageCount; i++) {
            String packageName = "package-" + i;
            Packet registeredPackage = this.sut.registerPacket(packageName, 1);
            this.sut.assignTruck(registeredTruck, registeredPackage);
        }

        List<Packet> actualPackages = this.sut.getPackets(registeredTruck.getId());

        // Verify

        assertThat(actualPackages, is(expectedPackages));
    }

    @Test
    @ExamTask(grade = 0.6)
    public final void truckManagerCanReturnATotalCapacityOfAllRegisteredTrucks() {
        // Setup
        int expectedTotalCapacity = 0;
        int capacity = random(100);
        for (int i = 0; i < capacity; i++) {
            this.sut.registerTruck("TRUCK-" + i, capacity);
            expectedTotalCapacity += capacity;
        }

        // Verify
        assertThat(this.sut.getTotalTruckCapacity(), is(expectedTotalCapacity));
    }

    private int random(int maxValue) {
        return 1 + (int) (Math.random() * maxValue);
    }

}
