package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
        // TITLE = "(//android.widget.TextView[@text=\"Appium\"])[1]",
    
        FOOTER_ELEMENT = "//android.widget.TextView[@text=\"View article in browser\"]",
        SAVED_BUTTON = "org.wikipedia:id/page_save",
        OPTIONS_SNACKBAR_TEXT = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/snackbar_text\"]",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//android.widget.Button[@resource-id=\"org.wikipedia:id/snackbar_action\"]",
        OPTIONS_CREATE_NEW_BUTTON = "org.wikipedia:id/create_button",
        MY_LIST_NAME_INPUT = "//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]",
        ADD_TO_MY_LIST_OK_BUTTON = "//android.widget.Button[@resource-id=\"android:id/button1\"]";


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

//    public WebElement waitForTitleElement()
//    {
//        return waitForElementPresent(By.xpath(TITLE),
//                "Cannot find article title on page!",
//                10
//        );
//    }

    public WebElement waitForTitleElement(String searchQuery)
    {
        String dynamicTitleXpath = String.format("//android.widget.TextView[@text=\"%s\"]", searchQuery);
        return waitForElementPresent(By.xpath(dynamicTitleXpath),
                "Cannot find article title for '" + searchQuery + "'",
                10
        );
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement("Java (programming language)");
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        waitForElementAndClick(
                By.id(SAVED_BUTTON),
                "Cannot find 'Save' Button",
                10
        );

        waitForElementPresent(
                By.xpath(OPTIONS_SNACKBAR_TEXT),
                "Cannot find text 'Saved Java (programming language). Do you want to add it to a list?' on Snackbar",
                15
        );

        waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find 'Add to list' Button",
                10
        );

        waitForElementAndClick(
                By.id(OPTIONS_CREATE_NEW_BUTTON),
                "Cannot find 'Create new' Button",
                10
        );

        waitForElementAndSendKeys(
                By.xpath(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot find text input",
                15
        );

        waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_OK_BUTTON),
                "Cannot find 'OK' Button",
                15
        );

        waitForElementPresent(
                By.xpath(OPTIONS_SNACKBAR_TEXT),
                "Cannot find text 'Moved Java (programming language) to '" + name_of_folder + " on the pop-up",
                15
        );
    }
}
