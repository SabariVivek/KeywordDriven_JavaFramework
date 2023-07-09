package com.auto.executionengine;

import com.auto.keyword.ActionKeywords;
import com.auto.utilities.BaseClass;
import com.auto.utilities.ExcelUtilities;
import com.auto.utilities.Locators;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.auto.constants.Constants.*;
import static com.auto.utilities.ExcelUtilities.*;

public class Engine extends BaseClass {
    ActionKeywords actionKeywords;
    Method[] methods;
    public static By locator;
    static ExcelUtilities excelUtilities;
    static Engine engine;

    public Engine() {
        actionKeywords = new ActionKeywords();
        methods = actionKeywords.getClass().getMethods();
    }

    public void executeKeywords() throws InvocationTargetException, IllegalAccessException {
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(ExcelUtilities.actionKeyword)) {
                repeat++;
                for (int i = 1; i <= repeat; i++) {
                    method.invoke(actionKeywords);
                }
                break;
            }
        }
    }

    public void findWebElementToBeUsed() {
        switch (ExcelUtilities.locatorType.toLowerCase()) {
            case ID:
                locator = Locators.getID(ExcelUtilities.locatorValue);
                break;
            case NAME:
                locator = Locators.getName(ExcelUtilities.locatorValue);
                break;
            case XPATH:
                locator = Locators.getXpath(ExcelUtilities.locatorValue);
                break;
            case CLASS_NAME:
                locator = Locators.getClassName(ExcelUtilities.locatorValue);
                break;
            case CSS_SELECTOR:
                locator = Locators.getCSS(ExcelUtilities.locatorValue);
                break;
            case TAG_NAME:
                locator = Locators.getTagName(ExcelUtilities.locatorValue);
                break;
            case LINK_TEXT:
                locator = Locators.getLinkText(ExcelUtilities.locatorValue);
                break;
            case PARTIAL_LINK_TEXT:
                locator = Locators.getPartialLinkText(ExcelUtilities.locatorValue);
                break;
        }
    }

    @Test
    public void engineFile() throws Exception {
        excelUtilities = new ExcelUtilities();
        readExcelValue(EXCEL_LOCATION);

        engine = new Engine();
        sheet = workbook.getSheet("Main");
        String sheetName = sheet.getRow(0).getCell(1).toString().trim();
        if (sheetName.contains(",")) {
            split = sheetName.split(",");
            for (String s : split) {
                sheet = workbook.getSheet(s.trim());
                physicalNoOfRows = sheet.getPhysicalNumberOfRows();
                child.set(extentMap.get(getClass().getSimpleName()).createNode(sheet.getSheetName()));
                info("<b><<<< Execution Started >>>>></b>");
                executeSheet();
            }
        } else {
            sheet = workbook.getSheet(sheet.getRow(0).getCell(1).toString().trim());
            physicalNoOfRows = sheet.getPhysicalNumberOfRows();
            child.set(extentMap.get(getClass().getSimpleName()).createNode(sheet.getSheetName()));
            info("<b><<<< Execution Started >>>>></b>");
            executeSheet();
        }
    }

    public void executeSheet() throws Exception {
        for (int row = 1; row < physicalNoOfRows; row++) {
            excelUtilities.getValuesFromExcel(row, LOCATOR_TYPE_COLUMN, LOCATOR_VALUE_COLUMN, ACTION_KEYWORD_COLUMN, TEST_DATA_COLUMN, REPEAT_COLUMN);
            engine.findWebElementToBeUsed();
            engine.executeKeywords();
        }
    }
}