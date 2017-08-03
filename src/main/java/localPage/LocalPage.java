package localPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocalPage {

    private WebDriver webDriverInstance;
    private WebDriverWait webDriverWait;

    public LocalPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        webDriverInstance = webDriver;
        this.webDriverWait = new WebDriverWait(this.webDriverInstance, 10);
    }

    protected WebDriver getWebDriver() {
        return webDriverInstance;
    }

    protected void waitUntilClick(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void waitUntilClick(By element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void waitUntilPresent(By element) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void dispose() {
        if (webDriverInstance != null) {
            webDriverInstance.quit();
        }
    }

}
