package localWebs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertEquals;

public class TestClass {

    private static final String WEB_DRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String DISABLE_INFO_BARS = "disable-infobars";
    private static final String CHROME_DRIVER_PATH = "drivers/chromedriver.exe";
    private static final String RELATIVE_PATH = "local-webs/search/index.html";
    private static final String LOCAL_PAGE = new File(RELATIVE_PATH).getAbsolutePath();
    private static final String LOCAL_PAGE_SEARCH_BOX = "searchbox";
    private static final String RANDOM = "random";
    private static final By LOCAL_PAGE_SEARCH_BUTTON = By.xpath("//img[@class='mic']");

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeClass
    public void testSetUp() {
        System.setProperty(WEB_DRIVER_CHROME_DRIVER, CHROME_DRIVER_PATH);
    }

    @BeforeMethod
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(DISABLE_INFO_BARS);
        driver = new ChromeDriver(options);
        driver.get(LOCAL_PAGE);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void searchCatsOnResultClickCatPageAndFindTheDerpCat() {
        String cats = "cats";
        String derpCat = "derp.jpg";
        By catLink = By.xpath("//div/a[contains(text(), '" + cats + "')]");
        By funnyCatLink = By.cssSelector("a[href='" + derpCat + "']");

        driver.findElement(By.id(LOCAL_PAGE_SEARCH_BOX)).sendKeys(cats);
        driver.findElement(LOCAL_PAGE_SEARCH_BUTTON).click();
        wait.until(ExpectedConditions.elementToBeClickable(catLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(funnyCatLink)).click();

        String title = driver.getTitle();
        assertTrue(title.contains(derpCat));
    }

    @Test
    public void searchRandomOnResultClickWikipediaPage() {
        String wiki = "Wiki";
        By wikiLink = By.xpath("//div/a[contains(string(), '" + wiki + "')]");

        wait.until(ExpectedConditions.elementToBeClickable(By.id(LOCAL_PAGE_SEARCH_BOX))).sendKeys(RANDOM);
        driver.findElement(LOCAL_PAGE_SEARCH_BUTTON).click();
        wait.until(ExpectedConditions.elementToBeClickable(wikiLink)).click();

        String title = driver.getTitle();
        assertTrue(title.contains(wiki));
    }

    @Test
    public void searchFoodOnResultClickFoodPageAndFindTheFoodNews() {
        String food = "food";
        By newsLink = By.xpath("//div/a[contains(@href, '" + food + "') and contains(@href, 'news')]");
        By imgLink = By.xpath("//a/div[@class='news4']");

        wait.until(ExpectedConditions.elementToBeClickable(By.id(LOCAL_PAGE_SEARCH_BOX))).sendKeys(food);
        driver.findElement(LOCAL_PAGE_SEARCH_BUTTON).click();
        wait.until(ExpectedConditions.elementToBeClickable(newsLink)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(imgLink).click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("food.html"));
    }

    @Test
    public void searchRandomOnResultClickMoviesPageAndFindMovieAboutFerdinand() {
        String movies = "movies!";
        By movieLink = By.xpath("//div/a[contains(text(),'" + movies + "')]");
        By movieImage = By.xpath("//img[@alt=\"John Cena in Ferdinand (2017)\"]");

        wait.until(ExpectedConditions.elementToBeClickable(By.id(LOCAL_PAGE_SEARCH_BOX))).sendKeys(RANDOM);
        driver.findElement(LOCAL_PAGE_SEARCH_BUTTON).click();
        wait.until(ExpectedConditions.elementToBeClickable(movieLink)).click();
        driver.findElement(movieImage).click();

        String title = driver.getTitle();
        assertEquals(title, "Google");
    }

    @Test
    public void searchRandomOnResultClickSoftwarePageAndFindFlashGetSoftwarePageWithoutSoftonicTitle() {
        String softwareTitle = "full 100% gratis";
        By softwareFreeLink = By.xpath("//div/a[contains(text(), '" + softwareTitle + "')]");
        By flashGetLink = By.xpath("//a[@title='FlashGet']");

        wait.until(ExpectedConditions.elementToBeClickable(By.id(LOCAL_PAGE_SEARCH_BOX))).sendKeys(RANDOM);
        driver.findElement(LOCAL_PAGE_SEARCH_BUTTON).click();
        wait.until(ExpectedConditions.elementToBeClickable(softwareFreeLink)).click();
        driver.findElement(flashGetLink).click();

        String currentUrl = driver.getCurrentUrl();
        assertFalse(currentUrl.contains("softonic"));
    }

    @Test
    public void searchNotebookOnResultClickShopPageAndFindNotebook() {
        String notebook = "notebook";
        String shop = "/shop/";
        By shopLink = By.xpath("//div/a[contains(@href, '" + shop + "')]");
        By notebookImage = By.xpath("//img[@alt='hp-m6.jpg']");

        wait.until(ExpectedConditions.elementToBeClickable(By.id(LOCAL_PAGE_SEARCH_BOX))).sendKeys(notebook);
        driver.findElement(LOCAL_PAGE_SEARCH_BUTTON).click();
        wait.until(ExpectedConditions.elementToBeClickable(shopLink)).click();
        driver.findElement(notebookImage).click();

        String title = driver.getTitle();
        assertNotEquals(title, LOCAL_PAGE);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}