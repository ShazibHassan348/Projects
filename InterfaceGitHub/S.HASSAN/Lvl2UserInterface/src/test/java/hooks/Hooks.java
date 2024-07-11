package hooks;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utilis.SettingsData;

import java.time.Duration;

import static io.qameta.allure.Allure.step;

public class Hooks {



    protected Browser browser = AqualityServices.getBrowser();

    protected HomePage homePage = new HomePage();



    @Before
    public void setup(){
        browser = AqualityServices.getBrowser();
        browser.maximize();


    }

    @Given("Navigate to start URL")
    public void navigate(){
        browser.goTo(SettingsData.getEnvData().getHost());
        browser.setImplicitWaitTimeout(Duration.ofSeconds(10));

    }

    @Then("Main Page is displayed")
    public void mainPageDisplayed() {
        Assert.assertTrue(homePage.state().isDisplayed(), "Welcome page is not open");


    }



    @After
    public void teardown(){
        browser.quit();
    }
}
