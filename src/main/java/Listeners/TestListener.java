package Listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reports.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getReporter();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(
            result.getTestClass().getName() + " :: " + result.getMethod().getMethodName()
        );
        extentTest.set(test);
        System.out.println("Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
        extentTest.remove(); // Clean up ThreadLocal
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed");
        extentTest.get().fail(result.getThrowable());

        WebDriver driver = null;
        try {
            // Try to get driver from the test instance
            Object testInstance = result.getInstance();
            driver = (WebDriver) testInstance.getClass().getField("driver").get(testInstance);
            
            if (driver != null) {
                String path = ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
                extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
            }
        } catch (Exception e) {
            extentTest.get().log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
            System.err.println("Screenshot capture failed: " + e.getMessage());
        }
        
        extentTest.remove(); // Clean up ThreadLocal
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped");
        extentTest.get().skip(result.getThrowable());
        extentTest.remove(); // Clean up ThreadLocal
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
        if (extent != null) {
            extent.flush();
        }
    }
}