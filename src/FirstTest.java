import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ScreenOrientation;
import com.google.common.collect.ImmutableMap;

public class FirstTest extends CoreTestCase {

    private MainPageObject mainPageObject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClear(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Navigate up' to cancel search",
                5
        );

        mainPageObject.waitForElementNotPresent(
                By.xpath("//android.widget.ImageView[@content-desc=\"Clear query\"]"),
                "'X' is still present on the page",
                10
        );
    }

    @Test
    public void testCompareArticleTitle() {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
                "Cannot find 'Object-oriented programming language' topic searching by 'java'",
                10
        );

        WebElement titleElement = mainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cannot find article title",
                10
        );

        String articleTitle = titleElement.getAttribute("text");
        
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                articleTitle
        );
    }

    @Test
    public void testSwipeArticle() {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\" and @text=\"Automation for Apps\"]"),
                "Cannot find 'Automation for Apps' topic searching by 'Appium'",
                10
        );

        mainPageObject.waitForElementPresent(
                By.xpath("(//android.widget.TextView[@text=\"Appium\"])[1]"),
                "Cannot find article title",
                15
        );

        mainPageObject.swipeUpToFindElement(
                By.xpath("//android.widget.TextView[@text=\"View article in browser\"]"),
                "Cannot find the end of article",
                20
        );
    }

    @Test
    public void testSaveFirstArticleToMyList() {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
                "Cannot find 'Object-oriented programming language' topic searching by 'java'",
                10
        );

        mainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cannot find article title 'Java (programming language)'",
                10
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save' Button",
                10
        );

        mainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/snackbar_text\"]"),
                "Cannot find text 'Saved Java (programming language). Do you want to add it to a list?' on Snackbar",
                15
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/snackbar_action\"]"),
                "Cannot find 'Add to list' Button",
                10
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]"),
                "Test15",
                "Cannot find text input",
                15
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]"),
                "Cannot find 'OK' Button",
                15
        );

        mainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/snackbar_text\"]"),
                "Cannot find text 'Moved Java (programming language) to Test6.' on the pop-up",
                15
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/snackbar_action\"]"),
                "Cannot find 'View list' button on the pop-up",
                15
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/buttonView\"]"),
                "Cannot find 'Got it' Button",
                15
        );

        mainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "Cannot find text 'Java (programming language)' in the Saved list",
                15
        );

        mainPageObject.swipeElementToLeft(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "Cannot find 'Java (programming language)' in the Saved list"
        );

        mainPageObject.waitForElementNotPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "Cannot delete Saved article from the list",
                15
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String searchLine = "Java";
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                searchLine,
                "Cannot find search input",
                5
        );

        String searchResultLocator = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup";
        mainPageObject.waitForElementPresent(
                By.xpath(searchResultLocator),
                "Cannot find results searching by '" + searchLine + "'",
                15
        );

        int amountOfSearchResults = mainPageObject.getAmountOfElements(
                By.xpath(searchResultLocator)
        );

        Assert.assertTrue(
                "We found too few results",
                amountOfSearchResults > 0
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                15
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                15
        );

        String searchLine = "Java";
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                searchLine,
                "Cannot find search input",
                15
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
                "Cannot find 'Object-oriented programming language' topic searching by " + searchLine,
                15
        );

        String titleBeforeRotation = mainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "text",
                "Cannot find article title",
                15
        );

        ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = mainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "text",
                "Cannot find article title",
                15
        );

        Assert.assertEquals(
                "Article title has been changed after screen rotation",
                titleBeforeRotation,
                titleAfterRotation
        );

        ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);

        String titleAfterSecondRotation = mainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "text",
                "Cannot find article title",
                15
        );

        Assert.assertEquals(
                "Article title has been changed after screen rotation",
                titleBeforeRotation,
                titleAfterSecondRotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
                "Cannot find 'Object-oriented programming language' topic searching by 'java'",
                10
        );

        driver.executeScript("mobile: backgroundApp", ImmutableMap.of("seconds", 2));

        mainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
                "Cannot find article title after returning to the app",
                10
        );
    }
}