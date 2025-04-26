import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.ScreenOrientation;
import com.google.common.collect.ImmutableMap;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), getCapabilities());
    }

    private DesiredCapabilities getCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("platformVersion", "12.0");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "org.wikipedia");
        caps.setCapability("appActivity", ".main.MainActivity");
        caps.setCapability("app", "C:\\Users\\User\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
        return caps;
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup[2]//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'java'",
                5
        );
    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClear(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Navigate up' to cancel search",
                5
        );

        waitForElementNotPresent(
                By.xpath("//android.widget.ImageView[@content-desc=\"Clear query\"]"),
                "'X' is still present on the page",
                10
        );
    }

    @Test
    public void testCompareArticleTitle(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
                "Cannot find 'Object-oriented programming language' topic searching by 'java'",
                10
        );

        WebElement title_element = waitForElementPresent(
                By.xpath("//android.view.View[@text=\"Java (programming language)\"]"),
                "Cannot find article title",
                10
        );

        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }


    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\" and @text=\"Automation for Apps\"]"),
                "Cannot find 'Automation for Apps' topic searching by 'Appium'",
                10
        );

        waitForElementPresent(
                By.xpath("//android.view.View[@text=\"Appium\"]"),
                "Cannot find article title",
                15
        );

        swipeUpToFindElement(
                By.xpath("//android.widget.TextView[@text=\"View article in browser\"]"),
                "Cannot find the end of article",
                20
        );
    }

    @Test
    public void saveFirstArticleToMyList() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
                "Cannot find 'Object-oriented programming language' topic searching by 'java'",
                10
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cannot find article title 'Java (programming language)'",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save' Button",
                10
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/snackbar_text\"]"),
                "Cannot find text 'Saved Java (programming language). Do you want to add it to a list?' on Snackbar",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/snackbar_action\"]"),
                "Cannot find 'Add to list' Button",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text=\"Create new\"]"),
                "Cannot find 'Create new' Button",
                10
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]"),
                "Test12",
                "Cannot find text input",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]"),
                "Cannot find 'OK' Button",
                15
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/snackbar_text\"]"),
                "Cannot find text 'Moved Java (programming language) to Test6.' on the pop-up",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/snackbar_action\"]"),
                "Cannot find 'View list' button on the pop-up",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/buttonView\"]"),
                "Cannot find 'Got it' Button",
                15
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "Cannot find text 'Java (programming language)' in the Saved list",
                15
        );

        swipeElementToLeft(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "Cannot find 'Java (programming language)' in the Saved list"
        );

        waitForElementNotPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\"]"),
                "Cannot delete Saved article from the list",
                15
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find 'Linkin Park Discography' topic searching by '" + search_line + "'",
                15
        );

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup[2]//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15
        );

        String title_before_rotation = waitForElementAnGetAttribute(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "text",
                "Cannot find article title",
                15
        );

        ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAnGetAttribute(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "text",
                "Cannot find article title",
                15
        );

        Assert.assertEquals(
                "Article title has been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAnGetAttribute(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "text",
                "Cannot find article title",
                15
        );

        Assert.assertEquals(
                "Article title has been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
                "Cannot find 'Object-oriented programming language' topic searching by 'java'",
                10
        );

        driver.executeScript("mobile: backgroundApp", ImmutableMap.of("seconds", 2));

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
                "Cannot find article title after returning to the app",
                10
        );
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeUp(int timeOfSwipe) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 540, 1536))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 540, 384))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    public void swipeUpQuick()
    {
        swipeUp(300);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0)
        {
            swipeUpQuick();
            ++already_swiped;
            if (already_swiped > max_swipes)
            {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
        }
    }

    public void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), right_x, middle_y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), left_x, middle_y))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private String waitForElementAnGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}