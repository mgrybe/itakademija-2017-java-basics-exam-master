package lt.itakademija.vilbertas;

import lt.itakademija.exam.TruckManager;
import lt.itakademija.exam.test.BaseTest;

/**
 * 
 */

/**
 * @author vilbertas
 *
 */
public class BaseTestImplementation extends BaseTest {

	@Override
	protected TruckManager createTransportManager() {
		// TODO Auto-generated method stub
		return new TruckManagerImplement();
	}

}
