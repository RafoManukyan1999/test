package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject {

    private static final String
            GOT_IT_BUTTON = "id:org.wikipedia:id/buttonView",
            ARTICLE_BY_TITLE_TPL = "xpath://*[@resource-id=\"org.wikipedia:id/page_list_item_title\"][@text='{TITLE}']";

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void vewArticlePage()
    {
        waitForElementAndClick(
                GOT_IT_BUTTON,
                "Cannot find 'Got it' Button",
                15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title + "'",
                15
        );
    }

    public void swipeArticleToDelete(String article_title)
    {
        waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        swipeElementToLeft(
                article_xpath,
                "Cannot find" + article_title + " in the Saved list"
        );
        waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        waitForElementNotPresent(
                article_xpath,
                "Article is still present with title '" + article_title + "'",
                15
        );
    }
}
