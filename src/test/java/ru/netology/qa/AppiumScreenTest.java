package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.AppiumScreen;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class AppiumScreenTest {

    private AndroidDriver driver;

    @BeforeAll
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Smartphone");
        desiredCapabilities.setCapability("appium:app", "D:\\mqa\\2.2 UI Automator\\sample\\app\\build\\intermediates\\apk\\debug\\app-debug.apk");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testEmptyString() {
        AppiumScreen screen = new AppiumScreen(driver);
        String beginText = screen.textToBeChanged.getText();
        screen.userInput.sendKeys(" ");
        screen.buttonChange.click();
        String endText = screen.textToBeChanged.getText();
        assertEquals(beginText, endText);
    }

    @Test
    public void testOpenTextInNewActivity() {
        AppiumScreen screen = new AppiumScreen(driver);
        String newText = "New Activity";
        screen.userInput.sendKeys(newText);
        screen.buttonActivity.click();
        assertEquals(newText, screen.text.getText());
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}