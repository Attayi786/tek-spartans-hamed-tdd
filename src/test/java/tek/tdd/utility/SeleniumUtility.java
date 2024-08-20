package tek.tdd.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tek.tdd.base.BaseSetup;

import java.time.Duration;

public class SeleniumUtility extends BaseSetup {
    private static final Logger LOGGER = LogManager.getLogger(SeleniumUtility.class);

    private WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME_IN_SECOND));
    }

    public String getElementText(By locator) {
        LOGGER.debug("Returning element Text {}", locator);
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator))
                .getText();
    }

    public String getElementText(WebElement element) {
        LOGGER.debug("Returning element Text {}", element);
        return getWait().until(ExpectedConditions.visibilityOf(element))
                .getText();
    }

    public boolean isElementEnabled(By locator) {
        LOGGER.debug("Checking element enable status {}", locator);
        boolean isEnabled = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator))
                .isEnabled();
        LOGGER.debug("element is enabled status {}", isEnabled);
        return isEnabled;
    }

    public boolean isElementEnabled(WebElement element) {
        LOGGER.debug("Checking element enable status {}", element);
        boolean isEnabled = getWait().until(ExpectedConditions.visibilityOf(element))
                .isEnabled();
        LOGGER.debug("element is enabled status {}", isEnabled);
        return isEnabled;
    }

    public void sendText(WebElement element, String text) {
        LOGGER.debug("Sending text {} to Element {}", text, element);
        WebElement targetElement = getWait().until(ExpectedConditions.visibilityOf(element));
        targetElement.clear();
        targetElement.sendKeys(text);
    }

    public void clickOnElement(WebElement element) {
        LOGGER.debug("Clicking on Element {}", element);
        getWait().until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }

    public boolean isElementDisplayed(WebElement element) {
        LOGGER.debug("Checking element for isDisplayed {}", element);
        return getWait().until(ExpectedConditions.visibilityOf(element))
                .isDisplayed();
    }

}