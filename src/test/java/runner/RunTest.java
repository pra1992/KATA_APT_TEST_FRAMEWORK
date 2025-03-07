package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/feature", 
                  glue = { "steps","hooks" },
		          tags = "@NegativeTest7", 
		          plugin = { "pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				    "rerun:src/test/resources/retry.txt" }, 
		          monochrome = true)

public class RunTest extends AbstractTestNGCucumberTests {

	
}
