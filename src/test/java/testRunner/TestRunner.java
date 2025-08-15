package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/Features",
	    glue = {"stepDefinitions", "hooks"},      
	    plugin = {"pretty", "html:target/cucumber-reports.html"},
//	    tags= "@SwagLabsLogin or @SwagLabsCart or @SwagLabCompleteAutomation",
	    monochrome = true                         
	)
public class TestRunner extends AbstractTestNGCucumberTests {

}