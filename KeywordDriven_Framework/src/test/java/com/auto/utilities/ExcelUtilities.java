package com.auto.utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtilities {

    public static FileInputStream file;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static String locatorType, locatorValue, actionKeyword, data;
    public static int physicalNoOfRows, repeat;
    public static String[] split;

    public static void readExcelValue(String location) throws Exception {
        file = new FileInputStream(location);
        workbook = new XSSFWorkbook(file);
    }

    public void getValuesFromExcel(int row, int locatorTypeColumn, int locatorValueColumn, int actionKeywordColumn, int testDataColumn, int repeatColumn) {
        locatorType = sheet.getRow(row).getCell(locatorTypeColumn).toString().trim();
        if (!locatorType.trim().equalsIgnoreCase("NA") && !locatorType.trim().equalsIgnoreCase("")) {
            locatorValue = sheet.getRow(row).getCell(locatorValueColumn).toString().trim();
        }
        actionKeyword = sheet.getRow(row).getCell(actionKeywordColumn).toString().trim();
        data = sheet.getRow(row).getCell(testDataColumn).toString().trim();
        repeat = 0;
        if (!sheet.getRow(row).getCell(repeatColumn).toString().trim().equalsIgnoreCase("")) {
            repeat = Integer.parseInt(sheet.getRow(row).getCell(repeatColumn).getRawValue());
        }
    }
}