package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject
{

    private static final String
            LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name=\"Learn more about Wikipedia\"]",
            NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            ADD_OR_EDIT_PREFERRED_LANG_TEXT = "xpath://XCUIElementTypeStaticText[@name=\"Add or edit preferred languages\"]",
            LEARN_MORE_ABOUT_PRIVACY_POLICY_AND_TERMS_OF_USE_TEXT = "xpath://XCUIElementTypeStaticText[@name=\"Learn more about our privacy policy and terms of use\"]",
            NEXT_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Next\"]",
            GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Get started\"]";

    public WelcomePageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        waitForElementPresent(
                LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link",
                10
        );
    }

    public void waitForNewWayToExploreText()
    {
        waitForElementPresent(NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find 'New ways to explore' link",
                10
        );
    }

    public void waitForAddOrEditPreferredLangText()
    {
        waitForElementPresent(
                ADD_OR_EDIT_PREFERRED_LANG_TEXT,
                "Cannot find 'Add or edit preferred languages' link",
                10
        );
    }

    public void waitForLearnMoreAboutOurPrivacyPolicyAndTermsOfUse()
    {
        waitForElementPresent(LEARN_MORE_ABOUT_PRIVACY_POLICY_AND_TERMS_OF_USE_TEXT,
                "Cannot find 'Learn more about our privacy policy and terms of use' link",
                10
        );
    }

    public void clickNextButton()
    {
        waitForElementAndClick(NEXT_BUTTON,
                "Cannot find and click 'Next' button",
                10
        );
    }

    public void clickGetStartedButton()
    {
        waitForElementAndClick(GET_STARTED_BUTTON,
                "Cannot find and click 'Get started' button",
                10
        );
    }
}
