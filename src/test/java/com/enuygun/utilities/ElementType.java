package com.enuygun.utilities;

import com.enuygun.model.Element;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class ElementType {
    public static By getBy(Element element, String platform) {
        String type = element.getType(platform);
        String value = element.getValue(platform);

        return switch (type) {
            case "css" -> By.cssSelector(value);
            case "xpath" -> By.xpath(value);
            case "id" -> By.id(value);
            case "name" -> By.name(value);
            case "class" -> By.className(value);
            case "link" -> By.linkText(value);
            case "partialLink" -> By.partialLinkText(value);
            case "tagName" -> By.tagName(value);
            case "classChain" -> AppiumBy.iOSClassChain(value);
            case "predicateString" -> AppiumBy.iOSNsPredicateString(value);
            case "accessibilityId" -> By.xpath(platform.equalsIgnoreCase("android")
                    ? "//*[@content-desc='" + value + "']"
                    : "//*[@name='" + value + "']");
            default -> throw new IllegalArgumentException("Invalid type: " + type);
        };
    }
}