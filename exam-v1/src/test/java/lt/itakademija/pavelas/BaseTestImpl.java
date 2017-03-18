package lt.itakademija.pavelas;

import lt.itakademija.exam.ProjectManager;
import lt.itakademija.exam.test.BaseTest;

public class BaseTestImpl extends BaseTest{

	@Override
	protected ProjectManager createProjectTracker() {		
		return new ProjectManagerImplementation();
	}

}
