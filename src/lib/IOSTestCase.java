package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;
import java.net.URL;

public class IOSTestCase extends TestCase {

    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("deviceName", "iPhone 16 Plus");
        caps.setCapability("platformVersion", "18.4");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("app", "/Users/rafomanukyan/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");

        driver = new IOSDriver(URI.create("http://127.0.0.1:4723").toURL(), caps);
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
}