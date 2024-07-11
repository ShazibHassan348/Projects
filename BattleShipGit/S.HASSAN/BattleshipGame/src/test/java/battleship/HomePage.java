package battleship;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import framework.LocatorConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.function.BooleanSupplier;

public class HomePage extends Form {

    private final By RANDOM_LINK = By.xpath("//div[contains(@class,'battlefield-start-choose_rival')]//a[contains(@class,'choose_rival') and not(contains(@class,'connect'))]");

    private final ILink getRandomLink = AqualityServices.getElementFactory().getLink(RANDOM_LINK,"Random Link");
    private final By BOXES = By.xpath("//div[contains(@class,'battlefield battlefield__rival')]//table[contains(@class,'battlefield-table')]//td[contains(@class,'battlefield-cell battlefield-cell__empty')]");

    private final By CHAT_BOX = By.xpath("//input[contains(@class,'input input__textarea chat-teletype')]");

    private final By RANDOMISE = By.xpath(String.format(LocatorConstant.PRECISE_TEXT_XPATH,"Randomise"));

    private final ILink randomiseLink = AqualityServices.getElementFactory().getLink(RANDOMISE,"randomise link");


    private final By PLAY = By.xpath("//div[contains(@class,'battlefield-start-button')]");

    private final IButton playBtn = AqualityServices.getElementFactory().getButton(PLAY,"play btn");

    private final ILabel rivalNotification = AqualityServices.getElementFactory().getLabel(
            By.xpath("//div[contains(@class,'notification notification__move-on') and not(contains(@class,'none'))]"),"Rival notification loading");




    public HomePage(){
        super(By.xpath(String.format(LocatorConstant.PRECISE_TEXT_XPATH,"Battleship ")),"Battleship ");

    }

    public void chooseRandomLink(){
        //getRandomLink.state().waitForExist(Duration.ofSeconds(10));
        getRandomLink.click();
    }

    public void clickRandomise(){
        for(int i=0;  i<=15; i++){
            randomiseLink.click();
        }
    }

    public void clickPlayBtn(){
        playBtn.click();
    }


    public void waitForOpponent(){
         AqualityServices.getConditionalWait().waitFor(ExpectedConditions.elementToBeClickable(CHAT_BOX),Duration.ofSeconds(20));

    }






}
