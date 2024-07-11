package framework;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class BattleForm extends Form{
    private static final String rivalFieldXpath = "//div[contains(@class,'battlefield__rival')]";

    private final ILabel rivalFieldWaitingCondition = AqualityServices.getElementFactory().getLabel(
            By.xpath("//div[contains(@class,'battlefield battlefield__rival') and contains(@class,'battlefield__wait')]"), "rivalFieldWaitingCondition");

    private volatile boolean interrupted = false;  // Volatile to ensure visibility across threads

    private IButton getRivalFieldCell(int x, int y) {
        String formattedXpath = String.format(rivalFieldXpath + "//div[contains(@class,'battlefield-cell-content') and @data-y='%d' and @data-x='%d']", y, x);
        return AqualityServices.getElementFactory().getButton(By.xpath(formattedXpath), "rivalFieldCell", ElementState.EXISTS_IN_ANY_STATE);
    }

    public boolean waitForRivalFieldToBeAvailable() {
        if (interrupted) {
            return false;
        }
        return rivalFieldWaitingCondition.state().waitForNotExist(Duration.ofSeconds(10));
    }

    private IButton emptyCell(int x, int y) {
        String formattedEmptyXpath = String.format(rivalFieldXpath + "//div[contains(@class,'battlefield-cell-content') and @data-y='%d' and @data-x='%d']//ancestor::*[contains(@class,'empty')]", y, x);
        return AqualityServices.getElementFactory().getButton(By.xpath(formattedEmptyXpath), String.format("emptyCell(%d,%d)", x, y));
    }

    private ILabel hitCell(int x, int y) {
        String formattedHitXpath = String.format(rivalFieldXpath + "//div[contains(@class,'battlefield-cell-content') and @data-y='%d' and @data-x='%d']//ancestor::*[contains(@class,'hit')]", y, x);
        return AqualityServices.getElementFactory().getLabel(By.xpath(formattedHitXpath), String.format("hitCell(%d,%d)", x, y));
    }

    private ILabel doneShip = AqualityServices.getElementFactory()
            .getLabel(By.xpath(rivalFieldXpath + "//*[contains(@class,'battlefield-cell__hit') and contains(@class,'done')]"), "shipDone");

    private final ILabel rivalLeave = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//div[contains(@class,'notifications')]//div[@class='notification notification__rival-leave']"), "rivalLeave");

    private final ILabel gameContinue = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//div[contains(@class,'notifications')]//div[contains(@class,'notification__move')]"), "gameContinue");

    private final ILabel gameOverWin = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//div[contains(@class,'notifications')]//div[contains(@class,'notification__game-over-win')]"), "gameOverWin");

    private final ILabel gameOverLose = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//div[contains(@class,'notifications')]//div[@class='notification notification__game-over-lose']"), "gameOverLose");

    public BattleForm() {
        super(By.xpath(rivalFieldXpath + "//div[contains(@class,'battlefield-cell-content' ) and @data-y='0' and @data-x='0']"), "BattleForm");
    }

    private boolean isCellEmpty(int x, int y) {
        return emptyCell(x, y).state().isExist();
    }

    private boolean isCellHit(int x, int y) {
        if (interrupted) {
            return false;
        }
        return hitCell(x, y).state().waitForExist(Duration.ofSeconds(1));
    }

    private boolean isShipDone() {
        if (interrupted) {
            return false;
        }
        return doneShip.state().waitForExist(Duration.ofSeconds(5));
    }

    public boolean hasRivalLeft() {
        return rivalLeave.state().isExist();
    }

    public boolean isGameOverWin() {
        return gameOverWin.state().isDisplayed();
    }

    public boolean isGameOverLose() {
        return gameOverLose.state().isExist();
    }

    private boolean isGameContinue() {
        return gameContinue.state().isExist();
    }

    private void shoot(int x, int y) throws Exception {
        if (interrupted) {
            return;
        }
        checkGameStatus();
        if (clickCell(x, y)) {
            finishAllShips(x, y);
        }
    }

    private boolean clickCell(int x, int y) {
        if (interrupted) {
            return false;
        }
        if (waitForRivalFieldToBeAvailable() && isCellEmpty(x, y)) {
            waitForRivalFieldToBeAvailable();
            getRivalFieldCell(x, y).clickAndWait();
        }
        return isCellHit(x, y);
    }

    private void finishAllShips(int cellX, int cellY) {
        if (interrupted) {
            return;
        }
        hitRightRecursive(cellX, cellY);
        hitLeftRecursive(cellX, cellY);
        hitDownRecursive(cellX, cellY);
        hitUpRecursive(cellX, cellY);
    }

    private void hitRightRecursive(int cellX, int cellY) {
        if (interrupted) {
            return;
        }
        if (cellX + 1 < getFieldSize() && isCellEmpty(cellX + 1, cellY) && !isShipDone()) {
            if (clickCell(cellX + 1, cellY)) {
                hitRightRecursive(cellX + 1, cellY);
            }
        }
    }

    private void hitLeftRecursive(int cellX, int cellY) {
        if (interrupted) {
            return;
        }
        if (cellX - 1 >= 0 && isCellEmpty(cellX - 1, cellY) && !isShipDone()) {
            if (clickCell(cellX - 1, cellY)) {
                hitLeftRecursive(cellX - 1, cellY);
            }
        }
    }

    private void hitDownRecursive(int cellX, int cellY) {
        if (interrupted) {
            return;
        }
        if (cellY + 1 < getFieldSize() && isCellEmpty(cellX, cellY + 1) && !isShipDone()) {
            if (clickCell(cellX, cellY + 1)) {
                hitDownRecursive(cellX, cellY + 1);
            }
        }
    }

    private void hitUpRecursive(int cellX, int cellY) {
        if (interrupted) {
            return;
        }
        if (cellY - 1 >= 0 && isCellEmpty(cellX, cellY - 1) && !isShipDone()) {
            if (clickCell(cellX, cellY - 1)) {
                hitUpRecursive(cellX, cellY - 1);
            }
        }
    }

    public int getFieldSize() {
        return getBattleFieldTableRows().size();
    }

    private String getGameStatus() {
        if (isGameOverLose()) {
            System.out.println("Game over. You lose.");
            interrupt();
            return "Game over. You lose.";
        } else if (isGameOverWin()) {
            System.out.println("Game over. Congratulations, you won!");
            interrupt();
            return "Game over. Congratulations, you won!";
        } else if (hasRivalLeft()) {
            System.out.println("Your opponent has left the game.");
            interrupt();
            return "Your opponent has left the game.";
        } else {
            System.out.println("Game Over. Reason unidentified");
            interrupt();
            return "Game Over. Reason unidentified";
        }
    }

    private void checkGameStatus() throws Exception {
        if (hasRivalLeft() || isGameOverLose() || isGameOverWin()) {
            interrupt();
            throw new Exception("Game is Over");
        }
    }

    private void fillRandomRows(int rowIndex) throws Exception {
        for (int x = 0, y = rowIndex; x < getFieldSize(); x++) {
            if (interrupted) {
                return;
            }
            shoot(x, y);
        }
    }

    public String play() {
        try {
            for (int x = 0, y = 0; y < getFieldSize(); x++, y++) {
                if (hasRivalLeft() || isGameOverLose() || isGameOverWin()) {
                    interrupt();
                    return getGameStatus();
                }
                shoot(x, y);
            }

            for (int x = getFieldSize() - 1, y = 0; y < getFieldSize(); x--, y++) {
                if (hasRivalLeft() || isGameOverLose() || isGameOverWin()) {
                    interrupt();
                    return getGameStatus();
                }
                shoot(x, y);
            }

            List<Integer> uniqueNumbers = generateUniqueRandomNumbers(getFieldSize());
            for (int num : uniqueNumbers) {
                if (hasRivalLeft() || isGameOverLose() || isGameOverWin()) {
                    interrupt();
                    return getGameStatus();
                }
                fillRandomRows(num);
            }
        } catch (Exception e) {
            return getGameStatus();
        }

        return "Game Over. Unknown reason.";
    }

    private List<Integer> generateUniqueRandomNumbers(int size) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    private List<ILabel> getBattleFieldTableRows() {
        return AqualityServices.getElementFactory().findElements(
                By.xpath("//div[contains(@class,'battlefield__rival')]//table[contains(@class,'battlefield-table')]//tr[contains(@class,'battlefield-row')]"), ElementType.LABEL);
    }

    public void interrupt() {
        interrupted = true;
    }
}