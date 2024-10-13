package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CookiesForm extends Form {
    private final By COOKIES_BTN = By.xpath("//button[contains(@class,'button button--solid')]");

    private final IButton cookiesBtn = AqualityServices.getElementFactory().getButton(COOKIES_BTN,"Cookies button");

    public CookiesForm(){
        super(By.xpath("//div[contains(@class,'cookies')]"),"Cookies");
    }

    public void acceptCookies(){
        cookiesBtn.click();
    }



}
