package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("platformVersion", "12.0");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "org.wikipedia");
        caps.setCapability("appActivity", ".main.MainActivity");
        caps.setCapability("app", "C:\\Users\\User\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        String appiumURL = "http://127.0.0.1:4723";
        driver = new AndroidDriver(new URL(appiumURL), caps);
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
}