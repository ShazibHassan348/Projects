package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class MainTest extends BaseTest{
    public static final String RESOURCES = "src/test/resources/";

    @Test(dataProvider = "dataprovider")
    public void test(String username, String password){
        Assert.assertTrue(loginPage.state().isDisplayed(),"Nuport Login Page is not displayed");

        loginPage.enterCredentialsInEmailField(username);
        loginPage.enterCredentialsInPasswordField(password);

        loginPage.clickLogInButton();


        Assert.assertTrue(nuportPage.state().waitForDisplayed(),"Successfully not logged in");
    }


    @DataProvider(name="dataprovider")
    public Object[][] getCredentials() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Credentials[] credentials = objectMapper.readValue(new File(RESOURCES+"credentials.json"), Credentials[].class);

        Object[][] data = new Object[credentials.length][2];
        for(int i=0; i<credentials.length;i++){
            data[i][0] = credentials[i].getEmail();
            data[i][1] = credentials[i].getPassword();
        }

        return data;

    }


}
