package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.*;
import utilis.TestSettings;

public class CredentialsAndInterestsSteps {

    private HomePage homePage = new HomePage();

    private FirstCardPage firstCardPage = new FirstCardPage();
    private SecondCardPage secondCardPage = new SecondCardPage();

    private ThirdCardPage thirdCardPage = new ThirdCardPage();

    private HelpForm helpForm = new HelpForm();



    private CookiesForm cookiesForm = new CookiesForm();




    @When("I click the link in the text {string}")
    public void clickLink(String text){
        homePage.clickLink(text);
    }



    @Then("First card is open")
    public void firstCardDisplayed(){
        Assert.assertTrue(firstCardPage.state().isDisplayed(),"First Card not displayed");

    }

    @When("I input password {string}, email {string}, domain {string}, {string}")
    public void enterCredentials(String password,String email, String domain, String text){
        firstCardPage.insertPassword(password);
        firstCardPage.insertEmail(email);
        firstCardPage.insertDomain(domain);
        firstCardPage.clickDropDownBar(text);

    }

    @And("Accept the terms of use, click the {string} button")
    public void acceptTermsAndClickNextLink(String text){
        firstCardPage.toggleCheckBox();
        firstCardPage.clickLink(text);

    }

    @Then("Second card is open")
    public void secondCardDisplayed(){
        Assert.assertTrue(secondCardPage.state().isDisplayed(),"Second Card not displayed");
    }

    @When("I choose {int} random interests, upload an image, and click the {string} button")
    public void chooseRandomInterestsAndUploadImage(int num,String text){
        TestSettings.uploadFile();
        secondCardPage.uncheckAll();
        secondCardPage.randomSelection(num);
        secondCardPage.clickLink(text);

    }

    @Then("Third card is open")
    public void thirdCardDisplayed(){
        Assert.assertTrue(thirdCardPage.state().isDisplayed(),"Third Card not displayed");

    }




}
