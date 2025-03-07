package hooks;

import config.ConfigManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class SetUp {

	@Before
	public void setUp() {
		RestAssured.baseURI = ConfigManager.getProperty("protocol") + "://" + ConfigManager.getProperty("hostname")
				+ ConfigManager.getProperty("basepath");
	}

	@After
	public void tearDown() {
		RestAssured.reset();
	}

}
