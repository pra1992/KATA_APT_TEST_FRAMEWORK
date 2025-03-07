package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/feature", 
                  glue = { "steps","hooks" },
		    	  plugin = { "pretty","html:target/cucumber.html",
		        		    "json:target/cucumber.json" }, 
		          monochrome = true)

public class RunTest extends AbstractTestNGCucumberTests {

	
}
