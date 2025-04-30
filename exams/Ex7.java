import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.time.Duration;

public class Ex7 {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), getCapabilities());
        ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
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
        try {
            if (driver != null) {
                ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
            }
        } catch (Exception e) {
            System.out.println("Failed to set portrait orientation: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        try {
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
                    By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Java (programming language)\"]"),
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
                    10
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
        } finally {
            ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
        }
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private String waitForElementAnGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}