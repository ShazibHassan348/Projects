package battleship;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import com.beust.ah.A;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilis.SettingsData;

import java.time.Duration;

public class BaseTest {

    protected Browser browser = AqualityServices.getBrowser();



    @BeforeMethod
    public void setup(){
        browser = AqualityServices.getBrowser();
        browser.goTo(SettingsData.getEnvData().getHost());
        //browser.maximize();
        browser.setImplicitWaitTimeout(Duration.ofSeconds(10));

    }



    @AfterMethod
    public void teardown(){
        browser.quit();

    }
}