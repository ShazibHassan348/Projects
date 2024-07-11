package utilis;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILink;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;


@UtilityClass
public class TestSettings {

    public static int DURATION = 5;

    private final By UPLOAD = By.xpath("//*[contains(@class,'avatar-and-interests__text')]//a[contains(@class,'avatar-and-interests')]");

    private final ILink uploadLink = AqualityServices.getElementFactory().getLink(UPLOAD, "upload link");


    public void uploadFile() {

        try {

            uploadLink.click();

            Robot robot = new Robot();

            File file = new File("src/test/resources/image.jpg");
            String filePath = file.getAbsolutePath();
            StringSelection stringSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);


            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);


        } catch (AWTException e) {
            e.printStackTrace();
        }


    }
}
