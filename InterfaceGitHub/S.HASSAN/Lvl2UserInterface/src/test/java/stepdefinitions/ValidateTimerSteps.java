package stepdefinitions;

import io.cucumber.java.en.And;
import org.testng.Assert;
import pages.FirstCardPage;

public class ValidateTimerSteps {
    FirstCardPage firstCardPage = new FirstCardPage();


    @And("The timer starts from {string}")
    public void validateTimer(String text){
        Assert.assertEquals(firstCardPage.getTimerlbl(),text,"Timer has not started from: "+text);

    }
}
