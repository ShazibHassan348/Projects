package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {

    private final ILink researchLink = AqualityServices.getElementFactory()
            .getLink(By.xpath("//ul[contains(@class,'header-links')]//*[contains(@data-linkname,'header-research')]"),"Research Link");


    private final IButton acceptBtn = AqualityServices.getElementFactory()
            .getButton(By.id("onetrust-accept-btn-handler"),"Cookies accept button");


    private final IButton closeBtn = AqualityServices.getElementFactory()
            .getButton(By.xpath("//div[contains(@id,'onetrust-close-btn-container')]//button"),"Close");
    public MainPage(){
        super(By.xpath("//div[contains(@class,'global-header-container')]"),"MainPage");

    }

    public void clickResearchLink(){
        researchLink.click();
    }


    public void clickCookiesBtn(){
        if(closeBtn.state().isDisplayed()){
            closeBtn.click();
        }
    }


}
