package pages;

import aquality.selenium.forms.Form;

import org.openqa.selenium.By;

public class ThirdCardPage extends Form {

    public static final String PRECISE_TEXT_XPATH = "//*[text()='%s']";

    public ThirdCardPage(){
        super(By.xpath(String.format(PRECISE_TEXT_XPATH,"Personal details")),"Personal details");

    }



}
