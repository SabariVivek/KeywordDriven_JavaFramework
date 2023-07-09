package com.auto.keyword;

import com.auto.executionengine.Engine;
import com.auto.utilities.BaseClass;
import com.auto.utilities.ExcelUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static com.auto.constants.Constants.*;

public class ActionKeywords {

    static WebDriver driver;
    static BaseClass baseClass = new BaseClass();

    public static void openBrowser() {
        switch (ExcelUtilities.data.toLowerCase()) {
            case CHROME:
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                baseClass.pass("Successfully launched the Chrome Driver");
                break;
            case EDGE:
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                baseClass.pass("Successfully launched the Edge Driver");
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                baseClass.pass("Successfully launched the Firefox Driver");
                break;
        }
    }

    public static void goToURL() {
        driver.get(ExcelUtilities.data);
        baseClass.pass("Successfully launched the given URL");
        baseClass.pass("URL : " + ExcelUtilities.data);
    }

    public static void sendKeys() {
        driver.findElement(Engine.locator).sendKeys(ExcelUtilities.data);
        baseClass.pass("Successfully entered the given text : \"" + ExcelUtilities.data + "\"");
    }

    public static void click() {
        driver.findElement(Engine.locator).click();
        baseClass.pass("Successfully clicked the element");
    }

    public static void dropdown() {
        WebElement dropdown = driver.findElement(Engine.locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(ExcelUtilities.data);
        baseClass.pass("Successfully selected the dropdown value : \"" + ExcelUtilities.data + "\"");
    }

    public static void verify() {
        driver.findElement(Engine.locator);
        baseClass.pass("Successfully verified the given text : \"" + ExcelUtilities.data + "\"");
    }

    public static void navigateBack() {
        driver.navigate().back();
        baseClass.pass("Successfully navigated back to the page");
    }

    public static void close() {
        driver.close();
        baseClass.pass("Successfully closed the current window");
    }

    public static void quit() {
        driver.quit();
        baseClass.pass("Successfully closed the all windows");
    }

    public static void sleep() {
        try {
            Thread.sleep(stringToIntWithoutZero(ExcelUtilities.data));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int stringToIntWithoutZero(String value) {
        String[] split = value.split("\\.");
        return Integer.parseInt(split[0]);
    }
}