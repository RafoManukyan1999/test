package lib;

import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected AndroidDriver driver;

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

        String osName = System.getProperty("os.name").toLowerCase();
        String appPath;
        
        if (osName.contains("win")) {
            // Путь для Windows
            appPath = "C:\\Users\\User\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk";
        } else {
            // Путь для macOS
            appPath = System.getProperty("user.home") + "/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk";
        }
        
        caps.setCapability("app", appPath);

        String appiumURL = "http://127.0.0.1:4723";
        driver = new AndroidDriver(new URL(appiumURL), caps);
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }
}