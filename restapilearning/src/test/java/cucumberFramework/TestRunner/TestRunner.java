package cucumberFramework.TestRunner;



import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/cucumberfeatures/", glue = {
		"cucumberFramework.stepDefinition" })
public class TestRunner extends AbstractTestNGCucumberTests {
}
