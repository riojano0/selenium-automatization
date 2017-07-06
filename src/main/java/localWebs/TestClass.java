package localWebs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class TestClass {
    private static final String CHROME_DRIVER_PATH = "drivers/chromedriver.exe";
    private WebDriver driver;
    private String RELATIVE_PATH = "local-webs/search/index.html";
    private String appURL = new File(RELATIVE_PATH).getAbsolutePath();
    private String GOOGLE_INPUT_ID = "searchbox";
    private By GOOGLE_SEARCH_BUTTTON = By.xpath("//img[@class='mic']");
    private String SEARCH_RANDOM = "random";
    private String GOOGLE_TITLE = "Google";

    @BeforeClass
    public void testSetUp() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
    }

    @Test
    public void scenario1() {
        String cats = "cats";
        String funnyCat = "derp.jpg";

        By catLink = By.xpath("//div/a[contains(text(), '" + cats + "')]");
        By funnyCatLink = By.cssSelector("a[href='" + funnyCat + "']");

        driver.get(appURL);
        driver.findElement(By.id(GOOGLE_INPUT_ID)).sendKeys(cats);
        driver.findElement(GOOGLE_SEARCH_BUTTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(catLink));
        element.click();
        WebDriverWait waiting = new WebDriverWait(driver, 10);
        WebElement elementCat = waiting.until(ExpectedConditions.elementToBeClickable(funnyCatLink));
        elementCat.click();

        String title = driver.getTitle();
        assertTrue(title.contains(funnyCat));
    }

    @Test
    public void scenario2() {
        String wiki = "Wiki";
        By wikiLink = By.xpath("//div/a[contains(string(), '" + wiki + "')]");

        driver.get(appURL);
        driver.findElement(By.id(GOOGLE_INPUT_ID)).sendKeys(SEARCH_RANDOM);
        driver.findElement(GOOGLE_SEARCH_BUTTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(wikiLink));
        element.click();

        String title = driver.getTitle();
        assertTrue(title.contains(wiki));
    }

    @Test
    public void scenario3() {
        String food = "food";
        By newsLink = By.xpath("//div/a[contains(@href, 'food') and contains(@href, 'news')]");
        By imgLink = By.xpath("//a/div[@class='news4']");

        driver.get(appURL);
        driver.findElement(By.id(GOOGLE_INPUT_ID)).sendKeys(food);
        driver.findElement(GOOGLE_SEARCH_BUTTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(newsLink));
        element.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(imgLink).click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("food.html"));
    }

    //
    @Test
    // Ejecuting Test
    public void scenario4() {
        By MOVIE_LINK = By.xpath("//div[4]/a");
        By IMG_MOVIE = By.xpath("//img[@alt=\"John Cena in Ferdinand (2017)\"]");
        //Open Page
        driver.get(appURL);
        //Fill out search input
        driver.findElement(By.id(GOOGLE_INPUT_ID)).sendKeys(SEARCH_RANDOM);
        //Press search button
        driver.findElement(GOOGLE_SEARCH_BUTTTON).click();
        //Wait till link is present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement movieLink = wait.until(ExpectedConditions.elementToBeClickable(MOVIE_LINK));
        //Press search button
        movieLink.click();
        //Search and press img
        driver.findElement(IMG_MOVIE).click();
        //Verify URL
        String getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, GOOGLE_TITLE);
    }

    @Test
    // Ejecuting Test
    public void scenario5() {
        By FREE_LINK = By.xpath("//div[5]/a");
        By PRODUCT_LINK = By.xpath("//a[@title='FlashGet']");
        By SOFTONIC_LINK = By.xpath("regexp:softonic.+$");
        //Open Page
        driver.get(appURL);
        //Fill out search input
        driver.findElement(By.id(GOOGLE_INPUT_ID)).sendKeys(SEARCH_RANDOM);
        //Press search button
        driver.findElement(GOOGLE_SEARCH_BUTTTON).click();
        //Wait till link is present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement freeLink = wait.until(ExpectedConditions.elementToBeClickable(FREE_LINK));
        //Press search button
        freeLink.click();
        //Find and click correct link
        driver.findElement(PRODUCT_LINK).click();
        //Verify URL
        String getTitle = driver.getTitle();
        Assert.assertNotEquals(getTitle, SOFTONIC_LINK);
    }

    @Test
    // Ejecuting Test
    public void scenario6() {
        String SEARCH_NOTEBOOK = "notebook";
        By BUY_LINK = By.xpath("//div[7]/a");
        By NOTEBOOK_IMG = By.xpath("//img[@alt='hp-m6.jpg']");
        //Open Page
        driver.get(appURL);
        //Fill out search input
        driver.findElement(By.id(GOOGLE_INPUT_ID)).sendKeys(SEARCH_NOTEBOOK);
        //Press search button
        driver.findElement(GOOGLE_SEARCH_BUTTTON).click();
        //Wait till link is present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement buyLink = wait.until(ExpectedConditions.elementToBeClickable(BUY_LINK));
        //Press search button
        buyLink.click();
        //Find and click correct link
        driver.findElement(NOTEBOOK_IMG).click();
        //Verify URL
        String getTitle = driver.getTitle();
        Assert.assertNotEquals(getTitle, appURL);
    }

    @AfterClass
    // Closing Browser when finish the test
    public void tearDown() {
//        driver.quit();
    }
}