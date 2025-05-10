package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.Map;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_IOS = "ios";
    private String appiumURL = "http://127.0.0.1:4723";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities caps = getCapabilitiesForPlatform(getPlatform());

        if (getPlatform().equals(PLATFORM_ANDROID)) {
            driver = new AndroidDriver(new URL(appiumURL), caps);
        } else if (getPlatform().equals(PLATFORM_IOS)) {
            driver = new IOSDriver(new URL(appiumURL), caps);
        } else {
            throw new Exception("Cannot determine platform: " + getPlatform());
        }
    }

    private DesiredCapabilities getCapabilitiesForPlatform(String platform) {
        DesiredCapabilities caps = new DesiredCapabilities();
        
        if (platform.equals(PLATFORM_ANDROID)) {
            caps.setCapability("platformName", "Android");
            caps.setCapability("deviceName", "AndroidTestDevice");
            caps.setCapability("platformVersion", "12.0");
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("appPackage", "org.wikipedia");
            caps.setCapability("appActivity", ".main.MainActivity");
            caps.setCapability("app", "/Users/rafomanukyan/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
            caps.setCapability("platformName", "iOS");
            caps.setCapability("deviceName", "iPhone 16 Plus");
            caps.setCapability("platformVersion", "18.4");
            caps.setCapability("automationName", "XCUITest");
            caps.setCapability("app", "/Users/rafomanukyan/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        } else {
            System.out.println("Cannot get capabilities for platform " + platform);
        }
        
        return caps;
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        if (isLandscapeOrientation()) {
            System.out.println("Please rotate device to portrait orientation");
            sleep(3);
        }
    }

    protected void rotateScreenLandscape() {
        if (!isLandscapeOrientation()) {
            System.out.println("Please rotate device to landscape orientation");
            sleep(3);
        }
    }

    protected boolean isLandscapeOrientation() {
        return driver.manage().window().getSize().getWidth() >
               driver.manage().window().getSize().getHeight();
    }

    protected void backgroundApp(int seconds) {
        driver.executeScript("mobile: backgroundApp", Map.of("seconds", seconds));
    }

    protected void backgroundApp() {
        backgroundApp(2);
    }

    protected String getPlatform() {
        return System.getenv("PLATFORM") != null ? System.getenv("PLATFORM") : PLATFORM_ANDROID;
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}