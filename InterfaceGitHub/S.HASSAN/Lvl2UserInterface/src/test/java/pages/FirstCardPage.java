package pages;

import aquality.selenium.browser.AqualityServices;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

public class FirstCardPage extends Form {

    public static final String PRECISE_TEXT_XPATH = "//*[text()='%s']";

    private final By TIMER = By.xpath("//*[contains(@class,'timer timer--white timer--center')]");

    private final ILabel timerLbl = AqualityServices.getElementFactory().getLabel(TIMER,"timer lbl");

    private final By NEXT = By.xpath(String.format(PRECISE_TEXT_XPATH,"Next"));
    private final By CHECKBOX = By.xpath("//span[contains(@class,'checkbox__box')]");

    private final By PASSWORD = By.xpath("//input[@placeholder='Choose Password']");

    private final By EMAIL = By.xpath("//input[@placeholder='Your email']");
    private final By DOMAIN = By.xpath("//input[@placeholder='Domain']");

    private static final By DROPDOWN_HEADER_LOCATOR = By.xpath("//*[@class='dropdown__field']");
    private static final By DROPDOWN_LIST_LOCATOR = By.xpath("//*[@class='dropdown__list']");
    private static final By DROPDOWN_ITEM_LOCATOR = By.xpath("//*[@class='dropdown__list-item']");

    private final IComboBox dropDownHeader = AqualityServices.getElementFactory().getComboBox(DROPDOWN_HEADER_LOCATOR,"dropdown header");

    private final IComboBox dropDownList = AqualityServices.getElementFactory().getComboBox(DROPDOWN_LIST_LOCATOR, "dropdown list");

    private final ITextBox passwordField = AqualityServices.getElementFactory().getTextBox(PASSWORD,"password field");

    private final ITextBox emailField = AqualityServices.getElementFactory().getTextBox(EMAIL,"email field");

    private final ITextBox domainField = AqualityServices.getElementFactory().getTextBox(DOMAIN,"domain field");

    private final ILink nextLink = AqualityServices.getElementFactory().getLink(NEXT,"next link");


    private final ICheckBox checkBox = AqualityServices.getElementFactory().getCheckBox(CHECKBOX,"checkbox");







    public FirstCardPage(){
        super(By.xpath("//*[@class='login-form__container']"),"First Container");
    }

    private ILink getLinkByText(String linkText) {
        By linkLocator = By.xpath(String.format(PRECISE_TEXT_XPATH, linkText));
        return AqualityServices.getElementFactory().getLink(linkLocator, "click link");
    }

    public void clickLink(String linkText) {
        ILink clickLink = getLinkByText(linkText);
        clickLink.click();
    }

    public void insertPassword(String text){
        passwordField.clearAndType(text);
    }

    public void insertEmail(String text){
        emailField.clearAndType(text);
    }

    public void insertDomain(String text){
        domainField.clearAndType(text);
    }

    public void clickDropDownBar(String text){

        dropDownHeader.click();

        dropDownList.state().waitForClickable();

        List<IElement> listItems = AqualityServices.getElementFactory().findElements(DROPDOWN_ITEM_LOCATOR,ElementType.LABEL);

        for(IElement item:listItems){
            if(item.getText().equals(text)){
                item.click();
                break;
            }
        }



    }



    public void toggleCheckBox(){
        checkBox.toggle();
    }


    public String getTimerlbl(){
        return timerLbl.getText();
    }




}
