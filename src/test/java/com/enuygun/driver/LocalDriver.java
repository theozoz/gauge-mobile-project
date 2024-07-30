package com.enuygun.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class LocalDriver {



    public static WebDriver getDriver(String platformName) {
        AppiumDriver driver = null;
        switch (platformName) {
            case "Android":
                driver = getAndroidDriver();
                break;
            case "iOS":
                driver = getIOSDriver();
                break;
        }

        return driver;
    }

    private static IOSDriver getIOSDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("platformVersion", "17.5");
        capabilities.setCapability("deviceName", "iPhone 15 Pro Max");
        capabilities.setCapability("app", "/Users/ozcan.arpaci/IdeaProjects/hotel-mobile/apps/ios/ENUYGUN.app");
        capabilities.setCapability("automationName", "XCUITest");
        //capabilities.setCapability("udid", "07DC555C-3728-4079-9C19-01E8B8954040");
        try {
            return new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static AndroidDriver getAndroidDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", MobilePlatform.ANDROID);
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("appPackage", "com.mobilatolye.android.enuygun");
        capabilities.setCapability("appActivity", " com.mobilatolye.android.enuygun.MainActivity");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", "/Users/ozcan.arpaci/IdeaProjects/gauge-mobile-project/apps/android/com.mobilatolye.android.enuygun.apk"); // Uygulamanın doğru yolunu belirtin.

        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
