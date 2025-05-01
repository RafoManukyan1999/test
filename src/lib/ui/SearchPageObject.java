package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_SKIP_LINK = "//*[contains(@text,'Skip')]",
            SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_INPUT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup",
            SEARCH_EMPTY_RESULT_ELEMENT = "org.wikipedia:id/results_text";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public static void clickSkipButton()
    {
        waitForElementAndClick(
                By.xpath(SEARCH_SKIP_LINK),
                "Cannot find and click skip button",
                5
        );
    }

    public static void initSearchInput()
    {
        waitForElementAndClick(
                By.id(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                5
        );
        waitForElementPresent(
                By.id(SEARCH_INIT_ELEMENT),
                "Cannot find search init element after clicking",
                5
        );
    }

    public static void waitForCancelButtonToAppear()
    {
        waitForElementPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find search cancel button",
                5
        );
    }

    public static void waitForCancelButtonToDisappear()
    {
        waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Search cancel button is still present!",
                5
        );
    }

    public static void clickCancelSearch()
    {
        waitForElementAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find and click search cancel button",
                5
        );
    }

    public static void typeSearchLine(String search_line)
    {
        waitForElementAndSendKeys(
                By.xpath(SEARCH_INPUT),
                search_line, "Cannot find and type into search input",
                5
        );
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        waitForElementPresent(
                By.xpath(search_result_xpath),
                "Cannot find search result with substring " + substring,
                15);
    }

    public void ClickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Cannot find and click search result with substring " + substring,
                15
        );
    }

    public int getAmountOfFoundArticles()
    {
        waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find results searching by",
                15
        );
        return getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {
        waitForElementPresent(
                By.id(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element",
                15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        assertElementNotPresent(
                By.id(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results"
        );
    }
}
