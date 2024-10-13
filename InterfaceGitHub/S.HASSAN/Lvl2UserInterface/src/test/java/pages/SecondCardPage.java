package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;

import org.openqa.selenium.By;

import java.util.*;
import java.util.List;

public class SecondCardPage extends Form {

    public static final String PRECISE_TEXT_XPATH = "//*[text()='%s']";
    private final By NEXT = By.xpath(String.format(PRECISE_TEXT_XPATH,"Next"));
    private final ILink nextLink = AqualityServices.getElementFactory().getLink(NEXT,"next link");



    private final By CHECKBOXES = By.xpath("//span[contains(@class,'icon icon-check checkbox__check')]");



    public SecondCardPage() {
        super(By.xpath(String.format(PRECISE_TEXT_XPATH, "Choose 3 interests")), "Second Container");
    }


    private ILink getLinkByText(String linkText) {
        By linkLocator = By.xpath(String.format(PRECISE_TEXT_XPATH, linkText));
        return AqualityServices.getElementFactory().getLink(linkLocator, "click link");
    }

    public void clickLink(String linkText) {
        ILink clickLink = getLinkByText(linkText);
        clickLink.click();
    }






    public void uncheckAll() {
        List<ICheckBox> allCheckboxes = getAllCheckboxes();

        for (ICheckBox checkBox : allCheckboxes) {
            String checkBoxText = checkBox.getText();
            System.out.println("Checkbox text: [" + checkBoxText + "]");

            if (checkBoxText.contains("Unselect all")) {
                ICheckBox checkboxElement = checkBox.findChildElement(By.xpath(".//span[@class='icon icon-check checkbox__check']"),ElementType.CHECKBOX);
                if (!checkboxElement.isChecked()) {
                    checkboxElement.toggle(); // uncheck the checkbox
                    System.out.println("Unchecked the checkbox with text: " + checkBoxText);

                }
            }
        }


    }

    public void randomSelection(int num){

        List<ICheckBox> boxList = filterList();

        Collections.shuffle(boxList);

        int count = 0;

        for (ICheckBox checkBox : boxList) {
            if (count >= num) {
                break;
            }
            ICheckBox boxElement = checkBox.findChildElement(By.xpath(".//span[contains(@class,'checkbox__box')]"), ElementType.CHECKBOX);
            boxElement.toggle();
            count++;
        }




    }



    private List<ICheckBox> filterList() {
        List<ICheckBox> newCheckboxesList = getAllCheckboxes();
        List<ICheckBox> filteredCheckboxes = new ArrayList<>();

        for(ICheckBox item:newCheckboxesList){
            String text = item.getText();
            if(!text.contains("Select all") && !text.contains("Unselect all")){
                filteredCheckboxes.add(item);
            }
        }

        return filteredCheckboxes;

    }





    private List<ICheckBox> getAllCheckboxes(){
        return AqualityServices.getElementFactory().findElements(
                new By.ByXPath("//div[@class='avatar-and-interests__interests-list']//div[@class='avatar-and-interests__interests-list__item']"),
                "checkbox", ElementType.CHECKBOX);
    }










}
