package test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.NuportPage;
import utilis.SettingsData;

import java.time.Duration;

public class BaseTest {

    Browser browser = AqualityServices.getBrowser();

    protected LoginPage loginPage = new LoginPage();
    protected NuportPage nuportPage = new NuportPage();


    @BeforeMethod
    public void setup(){
        browser.goTo(SettingsData.getEnvData().getHost());
        browser.maximize();
        browser.setImplicitWaitTimeout(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void teardown(){
        browser.quit();
    }





}
