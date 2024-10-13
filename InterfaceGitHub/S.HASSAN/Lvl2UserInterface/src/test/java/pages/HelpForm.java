package pages;

import aquality.selenium.browser.AqualityServices;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class HelpForm extends Form {

    private final By BUTTON = By.xpath("//button[contains(@class,'button button--solid button--blue help-form__send-to-bottom-button')]");


    private final IButton closeBtn = AqualityServices.getElementFactory().getButton(BUTTON,"close button");

    public HelpForm(){
        super(By.xpath("//div[@class='help-form']"),"Help Form");
    }



    public void clickCloseBtn(){
        AqualityServices.getConditionalWait().waitFor(ExpectedConditions.visibilityOfElementLocated(BUTTON));
        closeBtn.click();
    }








}
