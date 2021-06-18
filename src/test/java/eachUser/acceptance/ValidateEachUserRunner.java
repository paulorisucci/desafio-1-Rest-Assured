package eachUser.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
        glue = "eachUser.acceptance.steps")
public class ValidateEachUserRunner extends AbstractTestNGCucumberTests {
}
