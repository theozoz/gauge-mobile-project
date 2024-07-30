package com.enuygun.utilities;

import com.enuygun.model.Element;
import org.openqa.selenium.By;

public class ElementType {

    ElementType() {
    }

    public static By elementType(Element element) {
        return switch (element.getType()) {
            case "css" -> By.cssSelector(element.getValue());
            case "xpath" -> By.xpath(element.getValue());
            case "id" -> By.id(element.getValue());
            case "name" -> By.name(element.getValue());
            case "class" -> By.className(element.getValue());
            case "link" -> By.linkText(element.getValue());
            case "partialLink" -> By.partialLinkText(element.getValue());
            case "tagName" -> By.tagName(element.getValue());
            case "data-testid" -> By.cssSelector("[data-testid='" + element.getValue() + "']");
            default -> throw new IllegalArgumentException("Invalid type: " + element.getType());
        };
    }

}
