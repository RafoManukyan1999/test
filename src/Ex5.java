import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
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

public class Ex5 {

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
        caps.setCapability("newCommandTimeout", 300);
        return caps;
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void saveFirstArticleToMyList2() {
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

        String search_element = "Test131";
        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]"),
                search_element,
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
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                10
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot find search input",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\" and @text=\"Automation for Apps\"]"),
                "Cannot find 'Automation for Apps' topic searching by 'Appium'",
                5
        );

        waitForElementPresent(
                By.xpath("(//android.widget.TextView[@text=\"Appium\"])[1]"),
                "Cannot find article title 'Appium'",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"),
                "Cannot find 'Save' Button",
                10
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/snackbar_text\"]"),
                "Cannot find text 'Saved Appium. Do you want to add it to a list?' on Snackbar",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/snackbar_action\"]"),
                "Cannot find 'Add to list' Button",
                10
        );

        swipeUpToFindElement(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text=\"Test131\"]"),
                "Cannot find" + search_element + "list by scrolling",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text=\"Test131\"]"),
                "Cannot find" + search_element + "list",
                10
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
                By.xpath("(//android.view.ViewGroup[@resource-id=\"org.wikipedia:id/page_list_item_container\"])[2]"),
                "Cannot find 'Java (programming language)' in the Saved list"
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/snackbar_text\"]"),
                "Cannot find text 'Removed Java (programming language) from saved lists.' on Snackbar",
                15
        );

        waitForElementNotPresent(
                By.xpath("(//android.view.ViewGroup[@resource-id=\"org.wikipedia:id/page_list_item_container\"])[2]"),
                "Cannot delete Saved article from the list",
                15
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Appium\"]"),
                "Cannot find 'Appium' article searching by 'Appium'",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Appium\"]"),
                "Cannot find 'Appium' article searching by 'Appium'",
                10
        );

        waitForElementPresent(
                By.xpath("(//android.widget.TextView[@text=\"Appium\"])[1]"),
                "Cannot find article title 'Appium'",
                15
        );
    }


    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
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

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);

        if (element.getSize().getWidth() <= 0 || element.getSize().getHeight() <= 0) {
            throw new IllegalStateException("Элемент имеет нулевой размер: ширина=" +
                    element.getSize().getWidth() + ", высота=" + element.getSize().getHeight());
        }

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        int start_x = right_x - 10;
        int end_x = left_x + 10;

        try {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start_x, middle_y));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), end_x, middle_y));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Arrays.asList(swipe));

            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Ошибка при свайпе: " + e.getMessage());
            e.printStackTrace();
        }
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
        swipeUp(900);
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

}