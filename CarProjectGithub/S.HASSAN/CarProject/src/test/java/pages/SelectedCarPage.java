package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectedCarPage extends Form {

    private final IButton engineAccordion = AqualityServices.getElementFactory()
            .getButton(By.xpath("//button[contains(@id,'specifications-trigger4')and contains(@aria-controls,'specifications-panel4')]"),"Engine");

    private final IButton engineAccordion2 = AqualityServices.getElementFactory()
            .getButton(By.xpath("//button[contains(@id,'specifications-trigger2')and contains(@aria-controls,'specifications-panel2')]"),"Engine");

    private final IButton engineAccordion3 = AqualityServices.getElementFactory()
            .getButton(By.xpath("//button[contains(@id,'specifications-trigger3')and contains(@aria-controls,'specifications-panel3')]"),"Engine");


    private ILink homeLink = AqualityServices.getElementFactory()
            .getLink(By.xpath("//ul[contains(@class,'sds-breadcrumb sds-breadcrumb--mobile-custom')]//a[contains(@data-linkname,'home')]"),"home link");

    private final ILabel specContainer = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//div[@class='key-spec']"),"Spec container");

    private final ILabel engineSpecContainer = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//div[contains(@id,'specifications-panel3')]"),"Engine spec container");


    public SelectedCarPage(){
        super(By.xpath("//div[contains(@class,'research-trim-details')]"),"Selected car page");
    }

    private Map<String,String> firstCarSpec = new HashMap<>();

    private Map<String,String> secondCarSpec = new HashMap<>();

    public String firstCarEngineSpec;

    public String secondCarEngineSpec;

    public String firstCarSeatingSpec;

    public String secondCarSeatingSpec;

    private void scrollToElement(IButton button) {
        button.getJsActions().scrollIntoView();
    }

    public boolean isEngineAccordionPresent() {
        return engineAccordion.state().isDisplayed();
    }

    public void reselectCarIfNoEngineAccordion() {
        while (!isEngineAccordionPresent()) {
            goBackToMainPage();

            ResearchAndTrimPage researchAndTrimPage = new ResearchAndTrimPage();
            researchAndTrimPage.selectRandomOptions();
            researchAndTrimPage.navigateToTrimSection();
        }
    }



    public void getEngineSpecsOfFirstCar(){
        /// Engine

        reselectCarIfNoEngineAccordion();

        scrollToElement(engineAccordion);

        engineAccordion.click();
        engineAccordion.click();

        engineSpecContainer.state().isDisplayed();

        List<ILabel> specs = getEngineSpecList("4");

        System.out.println("Size of spec list: " + specs.size());

        boolean engineTypeFound = false;

        for (ILabel item : specs) {

            if (item.getText().equals("Engine Type")) {

                ILabel valueLabel = item.findChildElement(By.xpath("//following-sibling::td[contains(@class,'row-content')]"), ElementType.LABEL);

                firstCarSpec.put("Engine type",valueLabel.getText());
                engineTypeFound = true;
                break;


            }
        }

        if (!engineTypeFound) {
            scrollToElement(engineAccordion3);
            engineAccordion3.click();
            engineAccordion3.click();
            specs = getEngineSpecList("3");
            for (ILabel item : specs) {
                if (item.getText().equals("Engine Type")) {
                    ILabel valueLabel = item.findChildElement(By.xpath("//following-sibling::td[contains(@class,'row-content')]"), ElementType.LABEL);
                    firstCarSpec.put("Engine type", valueLabel.getText());
                    break;
                }
            }
        }


        if (!engineTypeFound) {
            engineAccordion2.click();
            engineAccordion2.click();
            specs = getEngineSpecList("2");
            for (ILabel item : specs) {
                if (item.getText().equals("Engine Type")) {
                    ILabel valueLabel = item.findChildElement(By.xpath("//following-sibling::td[contains(@class,'row-content')]"), ElementType.LABEL);
                    firstCarSpec.put("Engine type", valueLabel.getText());
                    break;
                }
            }
        }



        firstCarEngineSpec = firstCarSpec.get("Engine type");



        ///// Door & Seat

        specContainer.state().waitForDisplayed();

        List<ILabel> getSpecs = specList();
        System.out.println("Size of door spec list: "+getSpecs.size());

        firstCarSpec.put("Door count",getSpecs.get(0).getText());
        firstCarSpec.put("Seat count",getSpecs.get(1).getText());

        firstCarSeatingSpec = firstCarSpec.get("Seat count");



        System.out.println(firstCarSpec.get("Engine type"));

        System.out.println(firstCarSpec.get("Seat count"));


    }




    public void getKeySpecsOfSecondCar(){
        reselectCarIfNoEngineAccordion();
        // Engine
        scrollToElement(engineAccordion);

        engineAccordion.click();
        engineAccordion.click();

        engineSpecContainer.state().isDisplayed();

        List<ILabel> specs = getEngineSpecList("4");

        System.out.println("Size of spec list: " + specs.size());

        boolean engineTypeFound = false;
        for (ILabel item : specs) {

            if (item.getText().equals("Engine Type")) {

                ILabel valueLabel = item.findChildElement(By.xpath("//following-sibling::td[contains(@class,'row-content')]"), ElementType.LABEL);

                secondCarSpec.put("Engine type",valueLabel.getText());
                engineTypeFound = true;
                break;

            }

        }

        if (!engineTypeFound) {
            specs = getEngineSpecList("3");
            for (ILabel item : specs) {
                if (item.getText().equals("Engine Type")) {
                    ILabel valueLabel = item.findChildElement(By.xpath("//following-sibling::td[contains(@class,'row-content')]"), ElementType.LABEL);
                    secondCarSpec.put("Engine type", valueLabel.getText());
                    break;
                }
            }
        }

        secondCarEngineSpec = secondCarSpec.get("Engine type");


        /// Door & Seat


        specContainer.state().waitForDisplayed();
        List<ILabel> getSpecsSecondCar = specList();
        System.out.println("Size of spec list: "+getSpecsSecondCar.size());



        secondCarSpec.put("Door count",getSpecsSecondCar.get(0).getText());
        secondCarSpec.put("Seat count",getSpecsSecondCar.get(1).getText());



        secondCarSeatingSpec = secondCarSpec.get("Seat count");

        System.out.println(secondCarSpec.get("Engine type"));
        System.out.println(secondCarSpec.get("Seat count"));


    }

    public void goBackToMainPage(){
        homeLink.click();
    }


    private List<ILabel> getEngineSpecList(String number) {
        return AqualityServices.getElementFactory().findElements(By.xpath("//div[contains(@id,'specifications-panel" + number + "')]//table[@class='specs-table']//td[@class='row-header']"), ElementType.LABEL);
    }




    private List<ILabel> specList(){
        return AqualityServices.getElementFactory()
                .findElements(By.xpath("//div[@class='key-spec']//label"), ElementType.LABEL);

    }




}
