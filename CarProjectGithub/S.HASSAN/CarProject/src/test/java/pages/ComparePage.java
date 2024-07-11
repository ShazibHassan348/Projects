package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.forms.Form;
import framework.LocatorConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class ComparePage extends Form {

    public static String PRECISE_TEXT_XPATH = "//*[text()='%s']";


    private final IButton comparisonBtn = AqualityServices.getElementFactory()
            .getButton(By.xpath("//div[contains(@class,'comparison-submit-container')]//button[contains(@data-linkname,'see-comparison')]"),"see comparison btn");


    public ComparePage(){
        super(By.xpath(String.format(PRECISE_TEXT_XPATH,"Compare cars")),"Compare cars");
    }


    private void scrollToElement(IComboBox comboBox) {
        comboBox.getJsActions().scrollIntoView();
    }

    private void waitForElementToBeClickable(IComboBox comboBox) {
       comboBox.state().waitForClickable();
    }

    private void waitForOptionToBePresent(IComboBox comboBox, String optionText) {
        AqualityServices.getConditionalWait().waitFor(driver -> {
            // Find all the option elements within the comboBox
            List<IComboBox> options = comboBox.findChildElements(By.tagName("option"), ElementType.COMBOBOX);
            // Iterate over the options and check their text
            for (IComboBox option : options) {
                if (option.getText().equals(optionText)) {
                    return true;
                }
            }
            return false;
        }, "Waiting for option to be present: " + optionText);


    }

    public void chooseFirstCarMakeModelAndYear(String make,String model,String year){
        List<IComboBox> VehicleMakeComboBox = getVehicleMakeComboBox();
        List<IComboBox> VehicleModelComboBox = getVehicleModelComboBox();
        List<IComboBox> VehicleYearComboBox = getVehicleYearComboBox();


        scrollToElement(VehicleMakeComboBox.get(0));

        waitForElementToBeClickable(VehicleMakeComboBox.get(0));

        waitForOptionToBePresent(VehicleMakeComboBox.get(0),make);

        VehicleMakeComboBox.get(0).selectByText(make);


        waitForElementToBeClickable(VehicleModelComboBox.get(0));

        waitForOptionToBePresent(VehicleModelComboBox.get(0),model);

        VehicleModelComboBox.get(0).selectByText(model);



        waitForElementToBeClickable(VehicleYearComboBox.get(0));

        waitForOptionToBePresent(VehicleYearComboBox.get(0),year);

        VehicleYearComboBox.get(0).selectByText(year);



    }




    public void chooseSecondCarMakeModelYear(String make,String model,String year){

        List<IComboBox> VehicleMakeComboBox = getVehicleMakeComboBox();
        scrollToElement(VehicleMakeComboBox.get(1));

        waitForElementToBeClickable(VehicleMakeComboBox.get(1));

        waitForOptionToBePresent(VehicleMakeComboBox.get(1),make);

        VehicleMakeComboBox.get(1).selectByText(make);


        List<IComboBox> VehicleModelComboBox = getVehicleModelComboBox();
        waitForElementToBeClickable(VehicleModelComboBox.get(1));

        waitForOptionToBePresent(VehicleModelComboBox.get(1),model);

        VehicleModelComboBox.get(1).selectByText(model);


        List<IComboBox> VehicleYearComboBox = getVehicleYearComboBox();
        waitForElementToBeClickable(VehicleYearComboBox.get(1));

        waitForOptionToBePresent(VehicleYearComboBox.get(1),year);

        VehicleYearComboBox.get(1).selectByText(year);


    }



    public void clickComparisonBtn(){
        comparisonBtn.state().waitForClickable();

        comparisonBtn.click();


    }


    private List<IComboBox> getVehicleMakeComboBox(){
        return AqualityServices.getElementFactory().findElements(By.id("vehicle_picker_make"), ElementType.COMBOBOX);

    }

    private List<IComboBox> getVehicleModelComboBox(){
        return AqualityServices.getElementFactory().findElements(By.id("vehicle_picker_model"),ElementType.COMBOBOX);

    }

    private List<IComboBox> getVehicleYearComboBox(){
        return AqualityServices.getElementFactory().findElements(By.id("vehicle_picker_year"),ElementType.COMBOBOX);

    }





}
