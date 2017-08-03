package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    private static final String WEB_DRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_PATH = "drivers/chromedriver.exe";

    private WebDriverFactory() {
    }

    public static WebDriver getWebDriver(WebDriverType webDriverType) {
        WebDriver webDriver = null;
        switch (webDriverType) {
            case CHROME:
                System.setProperty(WEB_DRIVER_CHROME_DRIVER, CHROME_DRIVER_PATH);
                webDriver = new ChromeDriver();
                break;
            case FIREFOX:
                webDriver = new FirefoxDriver();
                break;
        }
        return webDriver;
    }

}
