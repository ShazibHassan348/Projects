package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HelpForm;
import pages.HomePage;

public class HelpFormSteps {
    HomePage homePage = new HomePage();
    HelpForm helpForm = new HelpForm();


    @When("I hide the help form")
    public void hideHelpForm(){

        helpForm.clickCloseBtn();
    }

    @Then("Form content is hidden")
    public void helpFormisHidden(){
        Assert.assertTrue(helpForm.state().waitForNotDisplayed(),"Help form content is not hidden");

    }
}
