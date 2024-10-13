package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CookiesForm;

public class AcceptCookiesSteps {

    CookiesForm cookiesForm = new CookiesForm();

    @When("I accept the cookies")
    public void acceptCookies(){
        cookiesForm.acceptCookies();
    }

    @Then("Cookies form is closed")
    public void cookiesFormIsClosed(){
        Assert.assertTrue(cookiesForm.state().waitForNotDisplayed(),"Form is not closed.");
    }


}
