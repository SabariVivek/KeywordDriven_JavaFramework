package com.auto.utilities;

import org.openqa.selenium.By;

public class Locators {

    public static By getID(String id) {
        return By.id(id);
    }

    public static By getName(String name) {
        return By.name(name);
    }

    public static By getXpath(String xpath) {
        return By.xpath(xpath);
    }

    public static By getClassName(String className) {
        return By.className(className);
    }

    public static By getCSS(String css) {
        return By.cssSelector(css);
    }

    public static By getTagName(String tagName) {
        return By.tagName(tagName);
    }

    public static By getLinkText(String linkText) {
        return By.linkText(linkText);
    }

    public static By getPartialLinkText(String partialLinkText) {
        return By.partialLinkText(partialLinkText);
    }
}