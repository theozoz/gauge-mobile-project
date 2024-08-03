package com.enuygun.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriver {

    public static DesiredCapabilities capabilities = new DesiredCapabilities();


    public static AppiumDriver getDriver(String platformName) {
        return null;

    }

    public static WebDriver getAndroidDriver() {
        return null;
    }

  /*  public static WebDriver getIosDriver() {

//        capabilities.setCapability("bundleId", "com.mobilatolye.EnUygun");
//        capabilities.setCapability("build", "Enuygun Mobil Otomasyon - IOS Smoke");
//        capabilities.setCapability("platformName", "ios");
//        capabilities.setCapability("deviceName", "iPhone 15 Pro");
//        capabilities.setCapability("isRealMobile", true);
//        capabilities.setCapability("platformVersion", "17.5");
//        capabilities.setCapability("deviceOrientation", "PORTRAIT");
//        capabilities.setCapability("console", true);
//        capabilities.setCapability("network", false);
//        capabilities.setCapability("visual", true);
//        capabilities.setCapability("devicelog", true);
//        capabilities.setCapability("network", true);
        try {
            return new IOSDriver(new URL("appiumServerUrl"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

   */

}
