package battleship;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest extends BaseTest{

    HomePage homePage = new HomePage();

    BattleForm battleForm = new BattleForm();


    @Test
    public void test()  {
        Assert.assertTrue(homePage.state().isDisplayed(),"Page not displayed");

        homePage.chooseRandomLink();

        homePage.clickRandomise();

        homePage.clickPlayBtn();

        battleForm.play();




    }
}
