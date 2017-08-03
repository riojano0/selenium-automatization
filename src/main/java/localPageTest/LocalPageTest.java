package localPageTest;

import driver.WebDriverFactory;
import driver.WebDriverType;
import localPage.SearchHomePage;
import localPage.SearchResultPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LocalPageTest {

    private SearchHomePage searchHomePage;

    @BeforeMethod
    public void beforeMethod() {
        WebDriver webDriver = WebDriverFactory.getWebDriver(WebDriverType.CHROME);
        searchHomePage = new SearchHomePage(webDriver);
    }

    @AfterMethod
    public void afterMethod() {
        searchHomePage.dispose();
    }

    @Test
    public void searchCatsOnResultClickCatPageAndFindTheDerpCat() {
        SearchResultPage searchResultPage = searchHomePage.searchWord("cats");
        searchResultPage.navigateToTheResultThatConstainsOnUrl("cats");
        searchResultPage.clickWhereHrefContains("derp.jpg");

        assertTrue(searchResultPage.getTitle().contains("derp.jpg"));
    }

    @Test
    public void searchRandomOnResultClickWikipediaPage() {
        SearchResultPage searchResultPage = searchHomePage.searchWord("random");
        searchResultPage.navigateToTheResultThatConstainsOnUrl("historical");

        assertTrue(searchResultPage.getTitle().contains("Wiki"));
    }

    @Test
    public void searchFoodOnResultClickFoodPageAndFindTheFoodNews() {
        SearchResultPage searchResultPage = searchHomePage.searchWord("food");
        searchResultPage.navigateToTheResultThatConstainsOnUrl("news");
        searchResultPage.clickWhereClassContains("news4");

        assertTrue(searchResultPage.getUrl().contains("food.html"));
    }

    @Test
    public void searchRandomOnResultClickMoviesPageAndFindMovieAboutFerdinand() {
        SearchResultPage searchResultPage = searchHomePage.searchWord("movies!");
        searchResultPage.navigateToTheResultThatConstainsOnUrl("movies");
        searchResultPage.clickWhereAltContains("John Cena in Ferdinand (2017)");

        assertTrue(searchResultPage.getTitle().contains("Google"));
    }

    @Test
    public void searchRandomOnResultClickSoftwarePageAndFindFlashGetSoftwarePageWithoutSoftonicTitle() {
        SearchResultPage searchResultPage = searchHomePage.searchWord("random");
        searchResultPage.navigateToTheResultThatConstainsOnUrl("software");
        searchResultPage.clickWhereTitleContains("FlashGet");

        assertFalse(searchResultPage.getUrl().contains("softonic"));
    }

    @Test
    public void searchNotebookOnResultClickShopPageAndFindNotebook() {
        SearchResultPage searchResultPage = searchHomePage.searchWord("notebook");
        searchResultPage.navigateToTheResultThatConstainsOnUrl("shop");
        searchResultPage.clickWhereAltContains("hp-m6.jpg");

        assertFalse(searchResultPage.getUrl().contains("softonic"));
    }

}