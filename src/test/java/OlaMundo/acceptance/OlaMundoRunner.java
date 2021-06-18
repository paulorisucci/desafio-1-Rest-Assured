package olaMundo.acceptance;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "olaMundo.acceptance.steps")
public class OlaMundoRunner extends AbstractTestNGCucumberTests {
}
