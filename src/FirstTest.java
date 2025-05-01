import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import com.google.common.collect.ImmutableMap;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.ClickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_Title = ArticlePageObject.getArticleTitle();

        
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_Title
        );
    }

    @Test
    public void testSwipeArticle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.ClickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement( "Appium" );
        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.ClickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickViewArticle();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.vewArticlePage();
        MyListsPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        int amount_of_search_result = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_result > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Qwerty123123");
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                15
        );

        String searchLine = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                searchLine,
                "Cannot find search input",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
                "Cannot find 'Object-oriented programming language' topic searching by " + searchLine,
                15
        );

        String titleBeforeRotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "text",
                "Cannot find article title",
                15
        );

        ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = MainPageObject.waitForElementAndGetAttribute(
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

        String titleAfterSecondRotation = MainPageObject.waitForElementAndGetAttribute(
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
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
                "Cannot find 'Object-oriented programming language' topic searching by 'java'",
                10
        );

        driver.executeScript("mobile: backgroundApp", ImmutableMap.of("seconds", 2));

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
                "Cannot find article title after returning to the app",
                10
        );
    }
}