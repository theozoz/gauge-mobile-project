package com.enuygun.driver;

import com.enuygun.utilities.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class LocalDriver {


    private static final DesiredCapabilities capabilities = new DesiredCapabilities();
    static AppiumDriver driver;

    public static AppiumDriver getDriver(String platformName) {
         driver = switch (platformName.toLowerCase()) {
            case "android" -> getAndroidDriver();
            case "ios" -> getIOSDriver();
            default -> null;
        };

        return driver;
    }

    private static IOSDriver getIOSDriver() {

        capabilities.setCapability("appium:platformName", "ios");
        capabilities.setCapability("appium:platformVersion", ConfigReader.getProperty("iosPlatformVersion"));
        capabilities.setCapability("appium:deviceName", ConfigReader.getProperty("iosDeviceName"));
        capabilities.setCapability("appium:app", ConfigReader.getProperty("iosAppPath"));
        capabilities.setCapability("appium:automationName", "XCUITest");
        try {
            return new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static AndroidDriver getAndroidDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "android");
        capabilities.setCapability("appium:deviceName", ConfigReader.getProperty("androidDeviceName"));
        capabilities.setCapability("appium:appPackage", ConfigReader.getProperty("appPackage"));
        capabilities.setCapability("appium:appActivity", ConfigReader.getProperty("appActivity"));
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:app", ConfigReader.getProperty("androidApkPath"));

        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
