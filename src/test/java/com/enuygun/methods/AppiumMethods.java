package com.enuygun.methods;

import com.enuygun.driver.BaseDriver;
import com.enuygun.utilities.ElementJsonReader;
import com.thoughtworks.gauge.Logger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


import java.time.Duration;
import java.util.*;

public class AppiumMethods {

    long waitElementTimeout = 30;
    long pollingEveryValue = 1000;
    AppiumDriver driver;
    FluentWait<WebDriver> wait;

    public AppiumMethods() {
        driver = BaseDriver.driver;
        wait = setFluentWait(waitElementTimeout);
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

    public By getBy(String locator) {
        return ElementJsonReader.findElementFromJsonFiles(locator);
    }

    public void ifAlertPresentAccept(long second) {
        try {
            wait.withTimeout(Duration.ofSeconds(second)).until(ExpectedConditions.alertIsPresent()).accept();
        } catch (TimeoutException e) {
            Logger.info("Alert bulunamadı");
        }
    }

    public void ifAlertPresentDismiss(long second) {
        try {
            wait.withTimeout(Duration.ofSeconds(second)).until(ExpectedConditions.alertIsPresent()).dismiss();
        } catch (TimeoutException e) {
            Logger.info("Alert bulunamadı");
        }
    }

    public void clickElement(String locator) {
        findElement(getBy(locator)).click();
    }

    public void sendKeys(String locator, String text) {
        findElement(getBy(locator)).sendKeys(text);
    }

    public void scrollDown() {
        Dimension size = driver.manage().window().getSize();
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        int centerX = size.width / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1);

        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, startY));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), centerX, endY));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    public void scrollUntilElementVisible(By locator, int maxScrollCount) {
        boolean elementFound = false;

        for (int i = 0; i < maxScrollCount; i++) {
            if (isElementVisible(locator, 1)) {
                elementFound = true;
                break;
            } else {
                scrollDown();
            }
        }
        if (!elementFound) {
            throw new RuntimeException("Element bulunamadı: " + locator.toString());
        }
    }

    private boolean isElementVisible(By locator, long second) {
        try {
            WebElement element = wait.withTimeout(Duration.ofSeconds(second)).until(ExpectedConditions.presenceOfElementLocated(locator));
            return element != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void swipe(String direction) {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", direction);
        driver.executeScript("mobile: swipe", params);
    }

    public By replaceText(String locator, String... args) {
        return By.xpath(String.format(locator, args).replaceAll("By.xpath: ", ""));
    }

    public void clickElementWithText(String locator, String text) {
        findElement(replaceText(getBy(locator).toString(), text)).click();

    }

    public void pressElement(String key) {
        WebElement element = findElement(getBy(key));

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), element.getLocation().getX(), element.getLocation().getY()));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(200)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        (driver).perform(Arrays.asList(tap));
    }

    public void waitUnTilElementVisible(String locator) {
        wait.withTimeout(Duration.ofSeconds(waitElementTimeout)).until(ExpectedConditions.presenceOfElementLocated(getBy(locator))) ;
    }
}
