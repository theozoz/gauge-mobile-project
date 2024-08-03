package com.enuygun.steps;


import com.enuygun.methods.AppiumMethods;
import com.thoughtworks.gauge.Step;

public class StepImplementation {

    AppiumMethods appiumMethods;

    public StepImplementation() {
        appiumMethods = new AppiumMethods();
    }

    @Step("<second> saniye bekle")
    public void waitTime(Long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Alerti kabul et")
    public void acceptAlertIfPresent() {
        appiumMethods.ifAlertPresentAccept(3);
    }

    @Step("Alerti reddet")
    public void dismissAlertIfPresent() {
        appiumMethods.ifAlertPresentDismiss(3);
    }

    @Step("Elemente <key> tıkla")
    public void clickElement(String key) {
        appiumMethods.clickElement(key);
    }

    @Step("Elemente <key> basılır")
    public void longPressElement(String key) {
        appiumMethods.pressElement(key);
    }

    @Step("Elemente <key> text <text> değerini yaz")
    public void findElement(String key, String text) {
        appiumMethods.sendKeys(key, text);
    }

    @Step("Text <text> değerini içeren element <key> görünene kadar <scrollTime> kaydır")
    public void setCheckInCheckOut(String text, String key, int scrollTime) {
        appiumMethods.scrollUntilElementVisible(appiumMethods.replaceText(appiumMethods.getBy(key).toString(), text), scrollTime);
    }

    @Step("Text <text> değerini içeren elemente <key> tıkla")
    public void clickElementWithText(String text, String key) {
        appiumMethods.clickElementWithText(key, text);
    }

    @Step("<scrollDownTime> defa aşağı kaydır")
    public void scrollDown(int scrollDownTime) {
        for (int i = 0; i < scrollDownTime; i++) {
            appiumMethods.scrollDown();
        }

    }

    @Step("Element <key> görünür olana kadar bekle")
    public void implementation1(String key) {
        appiumMethods.waitUnTilElementVisible(key);

    }
}
