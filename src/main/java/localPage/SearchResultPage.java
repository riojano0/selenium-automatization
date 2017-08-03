package localPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResultPage extends LocalPage {

    @FindAll(@FindBy(className = "searchResult"))
    private List<WebElement> searchResults;

    public SearchResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToTheResultThatConstainsOnUrl(String word) {
        Pattern p = Pattern.compile("webs\\/" + "(" + word + "+?)" + "\\/index");
        for (WebElement searchResult : searchResults) {
            Matcher m = p.matcher(searchResult.getText());
            if (m.find()) {
                System.out.println("Match!!!");
                waitUntilClick(searchResult.findElement(By.cssSelector("a:first-child")));
                break;
            }
        }
    }

    public String getTitle() {
        return getWebDriver().getTitle();
    }

    public void clickWhereHrefContains(String text) {
        By link = By.xpath("//*[contains(@href, '" + text + "')]");
        waitUntilClick(link);
    }

    public void clickWhereClassContains(String text) {
        By link = By.xpath("//*[contains(@class, '" + text + "')]");
        waitUntilClick(link);
    }

    public String getUrl() {
        return getWebDriver().getCurrentUrl();
    }

    public void clickWhereAltContains(String text) {
        By link = By.xpath("//*[contains(@alt, '" + text + "')]");
        waitUntilClick(link);
    }

    public void clickWhereTitleContains(String text) {
        By link = By.xpath("//*[contains(@title, '" + text + "')]");
        waitUntilClick(link);
    }
}
