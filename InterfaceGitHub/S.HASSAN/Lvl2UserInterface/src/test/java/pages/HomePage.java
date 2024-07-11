package pages;

import aquality.selenium.browser.AqualityServices;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;

import org.openqa.selenium.By;

public class HomePage extends Form {

    public static final String PRECISE_TEXT_XPATH = "//*[text()='%s']";
    private final By CLICK = By.xpath(String.format(PRECISE_TEXT_XPATH,"HERE"));


    private final ILink clickLink = AqualityServices.getElementFactory().getLink(CLICK,"click link");



    public HomePage(){
        super(By.xpath("//div[contains(@class,'logo__icon')]"),"User Interface");
    }

    private ILink getLinkByText(String linkText) {
        By linkLocator = By.xpath(String.format(PRECISE_TEXT_XPATH, linkText));
        return AqualityServices.getElementFactory().getLink(linkLocator, "click link");
    }

    public void clickLink(String linkText) {
        ILink clickLink = getLinkByText(linkText);
        clickLink.click();
    }




}