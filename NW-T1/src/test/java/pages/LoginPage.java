package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginPage extends Form {
    private final By EMAIL_FIELD = By.xpath("//input[contains(@class,'TextInput')]");

    private final By PASSWORD_FIELD = By.xpath("//input[contains(@class,'PasswordInput')]");

    private final By SUBMIT = By.xpath("//button[contains(@type,'submit')]");

    private final ITextBox emailContainer = AqualityServices.getElementFactory().getTextBox(EMAIL_FIELD,"Email Field");
    private final ITextBox passwordContainer = AqualityServices.getElementFactory().getTextBox(PASSWORD_FIELD,"Password Field");

    private final IButton loginButtton = AqualityServices.getElementFactory().getButton(SUBMIT,"Login Button");

    public LoginPage(){
        super(By.xpath("//div[contains(@class,'Login_inputsContainer')]"),"Login Page");
    }


    public void enterCredentialsInEmailField(String text){
        emailContainer.clearAndType(text);
    }

    public void enterCredentialsInPasswordField(String keys){
        passwordContainer.sendKeys(keys);
    }

    public void clickLogInButton(){
        loginButtton.click();
    }







}
