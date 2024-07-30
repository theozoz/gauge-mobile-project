package com.enuygun.methods;

import com.enuygun.driver.BaseDriver;
import com.enuygun.utilities.ElementJsonReader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


import java.time.Duration;
import java.util.List;

public class AppiumMethods {

    ElementJsonReader elementJsonReader;
    long waitElementTimeout = 30;
    long pollingEveryValue = 1000;
    WebDriver driver;
    FluentWait<WebDriver> wait;

    public AppiumMethods() {
        driver = BaseDriver.driver;
        wait = setFluentWait(waitElementTimeout);
        elementJsonReader = new ElementJsonReader();
    }

    public FluentWait<WebDriver> setFluentWait(long timeout) {

        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEveryValue))
                .ignoring(NoSuchElementException.class);
        return fluentWait;
    }

    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> findElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public By getBy(String locator) {
        return elementJsonReader.findElementFromJsonFiles(locator);
    }
}
