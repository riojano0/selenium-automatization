package localPage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class SearchHomePage extends LocalPage {

    private static final String RELATIVE_PATH = "local-webs/search/index.html";
    private static final String LOCAL_PAGE = new File(RELATIVE_PATH).getAbsolutePath();

    @FindBy(xpath = "//img[@class='mic']")
    private WebElement searchButton;
    @FindBy(id = "searchbox")
    private WebElement searchBox;

    public SearchHomePage(WebDriver webDriver) {
        super(webDriver);
        getWebDriver().get(LOCAL_PAGE);
    }

    public SearchResultPage searchWord(String word) {
        searchBox.sendKeys(word);
        searchButton.click();
        return new SearchResultPage(getWebDriver());

    }

}
