package tests.getrequest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/tests/getrequest/getrequest.feature"},
        glue = {"commonsteps", "tests"},
        plugin = {"pretty","html:target/cucumber-reports"}
)
public class GetRequestRunner extends AbstractTestNGCucumberTests {
}
