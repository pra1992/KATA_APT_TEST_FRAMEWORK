package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/feature", 
                  glue = { "steps","hooks" },
		          tags = "@BookingProcess", 
		          plugin = { "pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				    "rerun:src/test/resources/retry.txt" }, 
		          monochrome = true)

public class RunTest extends AbstractTestNGCucumberTests {

	// Enable parallel execution
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		// Returning the DataProvider from the parent class
		// (AbstractTestNGCucumberTests)
		return super.scenarios();
	}
}
