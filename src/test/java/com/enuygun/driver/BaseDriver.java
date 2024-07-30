package com.enuygun.driver;

import com.enuygun.utilities.ConfigReader;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeSuite;
import org.openqa.selenium.WebDriver;

public class BaseDriver {
    public static WebDriver driver;
    public static ConfigReader configReader = new ConfigReader();
    public static boolean isRemoteDriver = Boolean.parseBoolean(configReader.getProperty("isRemoteDriver"));
    public static String platformName = configReader.getProperty("platformName");

    @BeforeSuite
    public void setUp() {
        // Set up the driver
    }

    @BeforeScenario
    public void beforeScenario() {
        // Set up appium driver
        createDriver();
        System.out.println("----------------Driver is created-------------");
    }

    @AfterScenario
    public void afterScenario() {
        // Quit the driver
        driver.quit();
    }


    public static void createDriver() {
        if (isRemoteDriver)
            driver = RemoteDriver.getDriver(platformName);
        else {
            driver = LocalDriver.getDriver(platformName);
        }
    }

}