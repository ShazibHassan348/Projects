package pages;

import aquality.selenium.forms.Form;
import framework.LocatorConstant;
import org.openqa.selenium.By;

public class NuportPage extends Form {


    public NuportPage(){
        super(By.xpath("//div[contains(@class,'Page_navStart__ufB_D')]"),"Nuport Mainpage");
    }
}
