package lt.itakademija.exam;

import lt.itakademija.exam.test.BaseTest;

public class BaseTestImplTest extends BaseTest {

    @Override
    protected FlightManager createFlightManager() {
        return new FlightManagerImpl();
    }

}
