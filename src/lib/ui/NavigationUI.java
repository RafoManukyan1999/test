package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    private static final String
            OPTIONS_ViEW_LIST_BUTTON = "//android.widget.Button[@resource-id=\"org.wikipedia:id/snackbar_action\"]";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickViewArticle()
    {
        waitForElementAndClick(
                By.xpath(OPTIONS_ViEW_LIST_BUTTON),
                "Cannot find 'View list' button on the pop-up",
                15
        );
    }
}
