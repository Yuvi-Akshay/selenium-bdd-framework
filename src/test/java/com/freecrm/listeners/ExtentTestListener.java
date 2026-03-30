package com.freecrm.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.freecrm.reports.ExtentManager;
import com.freecrm.utils.DriverFactory;
import com.freecrm.utils.ScreenshotUtil;

public class ExtentTestListener implements ITestListener {

    private ExtentReports extent = ExtentManager.getExtentReports();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final Logger log = LogManager.getLogger(ExtentTestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        log.info("Test started: {}", testName);
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test PASSED: {}", result.getMethod().getMethodName());
        extentTest.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Throwable error = result.getThrowable();

        log.error("Test FAILED: {}", testName);
        log.error("Failure reason: ", error);

        WebDriver driver = DriverFactory.getDriver();
        String screenshotPath = ScreenshotUtil.takeScreenShot(driver, testName);

        ExtentTest test = extentTest.get();
        if (test != null) {
            test.fail("Test FAILED: " + testName);
            test.fail(error);
            test.addScreenCaptureFromPath(screenshotPath);
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test SKIPPED: {}", result.getMethod().getMethodName());
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.skip("Test skipped");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
