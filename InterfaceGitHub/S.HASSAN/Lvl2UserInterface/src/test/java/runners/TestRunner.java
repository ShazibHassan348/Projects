package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {"pretty"}, // prints the Gherkin steps into human-redable format in the console output
        glue = {"stepdefinitions","hooks"},// it defines the directories where Cucumber should look for stepdefinitions and hooks
        features = {"src/test/java/features"} // contains location of the Gherkin feature file
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }


}
