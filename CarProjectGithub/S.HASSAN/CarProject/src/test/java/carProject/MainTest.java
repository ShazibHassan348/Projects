package carProject;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import pages.ResearchAndTrimPage;




public class MainTest extends BaseTest{
    MainPage mainPage = new MainPage();


    DescriptionSelectedCarPage descriptionSelectedCarPage = new DescriptionSelectedCarPage();

    ResearchAndTrimPage researchAndTrimPage = new ResearchAndTrimPage();


    SelectedCarPage selectedCarPage = new SelectedCarPage();

    ComparisonCarPage comparisonCarPage = new ComparisonCarPage();

    ComparePage comparePage = new ComparePage();

    SoftAssert softAssert  = new SoftAssert();



    @Test
    public void test(){

        logger.info("First Car");

        Assert.assertTrue(mainPage.state().isDisplayed(),"Main Page is not displayed.");

        mainPage.clickCookiesBtn();

        mainPage.clickResearchLink();

        Assert.assertTrue(researchAndTrimPage.state().isDisplayed(),"Research & Review Page is not displayed.");



        researchAndTrimPage.selectRandomOptions();

        Assert.assertTrue(descriptionSelectedCarPage.state().isDisplayed(),"The page with the description of the selected car has not opened.");


        researchAndTrimPage.navigateToTrimSection();

        Assert.assertTrue(selectedCarPage.state().isDisplayed(),"The page with the specifications of the selected " +
                "trim has not opened.");


        selectedCarPage.getEngineSpecsOfFirstCar();

        selectedCarPage.goBackToMainPage();

        Assert.assertTrue(mainPage.state().isDisplayed(),"Main Page is not displayed.");


        logger.info("Second Car");

        mainPage.clickResearchLink();

        Assert.assertTrue(researchAndTrimPage.state().isDisplayed(),"Research & Review Page is not displayed.");


        researchAndTrimPage.selectSecondRandomOptions();

        Assert.assertTrue(descriptionSelectedCarPage.state().isDisplayed(),"The page with the description of the selected car " +
                "has not opened.");

        researchAndTrimPage.navigateToTrimSection();

        Assert.assertTrue(selectedCarPage.state().isDisplayed(),"The page with the specifications of the selected " +
                "trim has not opened.");

        selectedCarPage.getKeySpecsOfSecondCar();

        mainPage.clickResearchLink();

        Assert.assertTrue(researchAndTrimPage.state().isDisplayed(),"Research & Review Page is not displayed.");

        researchAndTrimPage.clickCompareLink();

        Assert.assertTrue(comparePage.state().isDisplayed(),"Compare page is not displayed");

        comparePage.chooseFirstCarMakeModelAndYear(researchAndTrimPage.makeText, researchAndTrimPage.modelText, researchAndTrimPage.yearText);


        comparePage.chooseSecondCarMakeModelYear(researchAndTrimPage.secondMakeText,researchAndTrimPage.secondModelText,researchAndTrimPage.secondYearText);


        comparePage.clickComparisonBtn();

        Assert.assertTrue(comparisonCarPage.state().waitForDisplayed(),"Comparison page is not displayed");

        softAssert.assertTrue(selectedCarPage.firstCarEngineSpec.contains(comparisonCarPage.getEngineDetails(CarPosition.FIRST)),
                "First car engine spec does not match");

        softAssert.assertTrue(selectedCarPage.secondCarEngineSpec.contains(comparisonCarPage.getEngineDetails(CarPosition.SECOND)),
                "Second car engine spec does not match");

        softAssert.assertTrue(selectedCarPage.firstCarSeatingSpec.contains(comparisonCarPage.getSeatingCapacity(CarPosition.FIRST)),
                "First car seating capacity does not match");

        softAssert.assertTrue(selectedCarPage.secondCarSeatingSpec.contains(comparisonCarPage.getSeatingCapacity(CarPosition.SECOND)),
                "Second car seating capacity does not match");


        softAssert.assertAll();




    }

}
