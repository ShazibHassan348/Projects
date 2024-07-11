package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import framework.LocatorConstant;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ResearchAndTrimPage extends Form {
    public static String PRECISE_TEXT_XPATH = "//*[text()='%s']";
    private final ILink compareLink = AqualityServices.getElementFactory()
            .getLink(By.xpath("//div[contains(@class,'tools-section')]//a[@data-linkname='research-compare']"), "compare link");

    private final IButton researchBtn = AqualityServices.getElementFactory()
            .getButton(By.xpath("//div[contains(@class,'sds-field field4')]//hubcap-button"), "Research Btn");

    private final IComboBox makeComboBox = AqualityServices.getElementFactory().getComboBox(By.id("make-select"), "Make Combo Box");
    private final IComboBox modelComboBox = AqualityServices.getElementFactory().getComboBox(By.id("model-select"), "Model Combo Box");
    private final IComboBox yearComboBox = AqualityServices.getElementFactory().getComboBox(By.id("year-select"), "Year Combo Box");

    private final ILabel trimSection = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//div[contains(@class,'page-section__content')]//ul[contains(@class,'trim-compare-list')]"), "trim section");

    public String makeText;
    public String modelText;
    public String yearText;

    public String secondMakeText;
    public String secondModelText;
    public String secondYearText;

    private int makeIndex;
    private int modelIndex;
    private int yearIndex;

    private int secondMakeIndex;
    private int secondModelIndex;
    private int secondYearIndex;

    public ResearchAndTrimPage() {
        super(By.xpath(String.format(PRECISE_TEXT_XPATH, "Research & reviews")), "Research & reviews");
    }

    private void scrollToElement(IComboBox comboBox) {
        comboBox.getJsActions().scrollIntoView();
    }

    private void scrollToElement(ILabel label) {
        label.getJsActions().scrollIntoView();
    }

    private void waitForElementToBeClickable(IComboBox comboBox) {
        AqualityServices.getConditionalWait().waitFor(driver ->
                        comboBox.state().waitForDisplayed(Duration.ofSeconds(5)) && comboBox.state().isClickable(),
                "Waiting for element to be clickable: " + comboBox.getName());
    }

    private void selectRandomOption(IComboBox comboBox, List<String> options, String optionType) {
        scrollToElement(comboBox);
        waitForElementToBeClickable(comboBox);
        Random random = new Random();

        if (options.size() <= 1) {
            throw new IllegalStateException("The " + optionType + " list has insufficient elements to exclude the first one.");
        }

        int index = random.nextInt(options.size() - 1) + 1; // Generate a random index from 1 to size-1
        System.out.println("Random " + optionType + " number: " + index);

        comboBox.selectByIndex(index);
        String selectedText = comboBox.getSelectedText();
        System.out.println("Selected " + optionType + ": " + selectedText);

        // Assign selected text to appropriate variable based on comboBox type
        switch (optionType) {
            case "Make":
                makeText = selectedText;
                makeIndex = index;
                break;
            case "Model":
                modelText = selectedText;
                modelIndex = index;
                break;
            case "Year":
                yearText = selectedText;
                yearIndex = index;
                break;
            case "SecondMake":
                secondMakeText = selectedText;
                secondMakeIndex = index;
                break;
            case "SecondModel":
                secondModelText = selectedText;
                secondModelIndex = index;
                break;
            case "SecondYear":
                secondYearText = selectedText;
                secondYearIndex = index;
                break;
            default:
                break;
        }
    }

    public void selectRandomOptions() {
        selectRandomOption(makeComboBox, makeList(), "Make");
        selectRandomOption(modelComboBox, modelList(), "Model");
        selectRandomOption(yearComboBox, yearList(), "Year");
        researchBtn.click();
    }

    public void selectSecondRandomOptions() {
        selectRandomOption(makeComboBox, makeList(), "SecondMake");
        selectRandomOption(modelComboBox, modelList(), "SecondModel");
        selectRandomOption(yearComboBox, yearList(), "SecondYear");
        researchBtn.click();
    }

    public void clickCompareLink() {
        Browser browser = AqualityServices.getBrowser();
        browser.executeScript("arguments[0].scrollIntoView(true);", compareLink.getElement());
        compareLink.click();
    }

    public void navigateToTrimSection() {
        if (trimSection.state().isExist()) {
            scrollToElement(trimSection);

            List<ILink> trimLinks = getTrimLinks();
            boolean linkSelected = selectTrimLink(trimLinks, "Base");

            if (!linkSelected) {
                linkSelected = selectFirstValidTrimLink(trimLinks);
            }

            if (!linkSelected) {
                retryNavigation();
            }
        } else {
            retryNavigation();
        }
    }

    private boolean selectTrimLink(List<ILink> trimLinks, String trimType) {
        for (ILink link : trimLinks) {
            if (isTrimLinkSelectable(link, trimType)) {
                System.out.println(trimType + " trim selection: " + link.getText());
                link.click();
                return true;
            }
        }
        return false;
    }

    private boolean isTrimLinkSelectable(ILink link, String trimType) {
        return link.state().isClickable() && link.getText().trim().equalsIgnoreCase(trimType) && !link.getHref().isEmpty();
    }

    private boolean selectFirstValidTrimLink(List<ILink> trimLinks) {
        for (ILink link : trimLinks) {
            if (link.state().isClickable() && !link.getHref().isEmpty()) {
                System.out.println("First valid trim selection: " + link.getText());
                link.click();
                return true;
            }
        }
        return false;
    }

    private void retryNavigation() {
        Browser browser = AqualityServices.getBrowser();
        browser.goBack();
        selectRandomOptions();
        navigateToTrimSection();
    }

    public int getMakeIndex() {
        return makeIndex;
    }

    public int getModelIndex() {
        return modelIndex;
    }

    public int getYearIndex() {
        return yearIndex;
    }

    public int getSecondMakeIndex() {
        return secondMakeIndex;
    }

    public int getSecondModelIndex() {
        return secondModelIndex;
    }

    public int getSecondYearIndex() {
        return secondYearIndex;
    }

    private List<String> makeList() {
        return makeComboBox.getTexts();
    }

    private List<String> modelList() {
        return modelComboBox.getTexts();
    }

    private List<String> yearList() {
        return yearComboBox.getTexts();
    }

    private List<ILink> getTrimLinks() {
        return AqualityServices.getElementFactory()
                .findElements(By.xpath("//*[contains(@class,'trim-compare-list')]//a[contains(@data-linkname,'research-mmyt')]"), ElementType.LINK);
    }

}

