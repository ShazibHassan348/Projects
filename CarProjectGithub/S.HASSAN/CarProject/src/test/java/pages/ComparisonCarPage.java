package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import framework.LocatorConstant;
import org.openqa.selenium.By;

import java.util.List;

public class ComparisonCarPage extends Form {

    public static String PRECISE_TEXT_XPATH = "//*[text()='%s']";

    public ComparisonCarPage(){
        super(By.xpath(String.format(PRECISE_TEXT_XPATH,"Compare")),"Compare");

    }

    private String getCarDetail(List<ILabel> details, CarPosition carPosition) {
        switch (carPosition) {
            case FIRST:
                return details.get(0).getText();
            case SECOND:
                return details.get(1).getText();
            default:
                throw new IllegalArgumentException("Invalid car position: " + carPosition);
        }
    }

    public String getEngineDetails(CarPosition carPosition) {
        List<ILabel> engineSpec = getSpecsByType("engine_type");
        return getCarDetail(engineSpec, carPosition);
    }

    public String getSeatingCapacity(CarPosition carPosition) {
        List<ILabel> seatingCapacity = getSpecsByType("seating_capacity");
        return getCarDetail(seatingCapacity, carPosition);
    }



    private List<ILabel> getSpecsByType(String specData) {
        return AqualityServices.getElementFactory()
                .findElements(By.xpath("//div[contains(@data-qa,'" + specData + "_data')]//div[contains(@class,'data-point')]"), ElementType.LABEL);
    }










    
}
