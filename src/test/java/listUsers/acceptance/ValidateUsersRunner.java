package listUsers.acceptance;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
                    glue = "listUsers.acceptance.steps")
public class ValidateUsersRunner extends AbstractTestNGCucumberTests {
}