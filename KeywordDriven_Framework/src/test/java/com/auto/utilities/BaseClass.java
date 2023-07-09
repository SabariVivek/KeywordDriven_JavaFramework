package com.auto.utilities;

import com.auto.reporting.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.HashMap;

import static com.auto.constants.Constants.EXCEL_LOCATION;
import static com.auto.utilities.ExcelUtilities.*;
import static com.auto.utilities.ExcelUtilities.sheet;

public class BaseClass {

    public static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> parentTestThreadLocal = new ThreadLocal<>();
    public static final HashMap<String, ExtentTest> extentMap = new HashMap<>();
    public static ThreadLocal<ExtentTest> child = new ThreadLocal<>();
    protected ThreadLocal<String> testNameParameter = new ThreadLocal<>();

    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentManager.getInstance();
    }

    @BeforeClass
    public void beforeClass() {
        parentTestThreadLocal.set(extent.createTest(getClass().getSimpleName()));
        extentMap.put(getClass().getSimpleName(), parentTestThreadLocal.get());
    }

    @BeforeMethod
    public void beforeMethod(final Method method) throws Exception {
        System.out.println("<<<<< STARTING TEST : " + method.getName() + " >>>>>");
    }

    @AfterMethod
    public void afterMethod(Method method, ITestResult iTestResult) {
        info("<b><<<< Execution Ended >>>>></b>");
    }

    @AfterSuite
    public void afterSuite() {
        addInformationToExtentReport();
        extent.flush();
    }

    private void addInformationToExtentReport() {
        try {
            extent.setSystemInfo("OS : ", System.getProperty("os.name"));
            extent.setSystemInfo("OS Architecture : ", System.getProperty("os.arch"));
            extent.setSystemInfo("User Name : ", System.getProperty("user.name"));
            extent.setSystemInfo("Machine Name : ", System.getProperty("machine.name"));
            extent.setSystemInfo("IP Address : ", System.getProperty("machine.address"));
            extent.setSystemInfo("Java Version : ", System.getProperty("java.version"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void info(String description) {
        BaseClass.child.get().info(description);
    }

    public void fail(String Description) {
        BaseClass.child.get().fail(Description);
    }

    public void warning(String Description) {
        BaseClass.child.get().warning(Description);
    }

    public void pass(String description) {
        BaseClass.child.get().pass(description);
    }

    private String dataProviderString() {
        if (testNameParameter.get() != null) {
            return " (" + testNameParameter.get() + ")";
        }
        return "";
    }
}