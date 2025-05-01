package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
        TITLE = "(//android.widget.TextView[@text=\"Appium\"])[1]",
        FOOTER_ELEMENT = "//android.widget.TextView[@text=\"View article in browser\"]",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//android.widget.ImageView[@content-desc=\"Add to reading list\"]",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]",
        CREATE_NEW_LIST_BUTTON = "//android.widget.Button[@text=\"CREATE NEW LIST\"]",
        LIST_NAME_INPUT = "//android.widget.EditText[@text=\"My first list\"]",
        ADD_TO_MY_LIST_OVERLAY = "//android.widget.FrameLayout[@content-desc=\"My lists\"]",
        MY_LIST_NAME_TPL = "//android.widget.TextView[@text=\"{MY_LIST_NAME}\"]",
        ARTICLE_IN_MY_LIST_BUTTON = "//android.widget.TextView[@text=\"{ARTICLE_NAME}\"]";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page!", 10);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find the end of article", 20);
    }
}
