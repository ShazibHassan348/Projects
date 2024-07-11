package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;

import org.openqa.selenium.By;

import java.util.List;

public class DescriptionSelectedCarPage extends Form {


    public DescriptionSelectedCarPage(){
        super(By.xpath("//header[contains(@class,'global-header')]//a[contains(@class,'header-logo')]"),
                "Trim Page");
    }




}
