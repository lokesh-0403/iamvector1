package baseTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import brokenLinkTest.LinkStatusCheck;
import io.github.bonigarcia.wdm.WebDriverManager;
import reports.ExtentManager;
import utils.ChromeOptionsConfig;



public class BaseTest {
	

	protected Path downloadDir;

	public Path getDownloadDir() {
	    return downloadDir;
	} 

    protected WebDriver driver;
   

    @BeforeMethod
    public void setup(ITestContext context) throws IOException {

        downloadDir = Files.createTempDirectory("test_downloads");

        ChromeOptions options = ChromeOptionsConfig.getChromeOptions(
                downloadDir.toFile().getAbsolutePath());
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        context.setAttribute("driver", driver);
        context.setAttribute("downloadDir", downloadDir);
    }
    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Quit only once
        }
    }
    
    @AfterSuite(alwaysRun = true)
    public void cleanup() {

        // Close executor if used in HttpClient
        if (LinkStatusCheck.executor != null && !LinkStatusCheck.executor.isShutdown()) {
            LinkStatusCheck.executor.shutdownNow();
            System.out.println("HttpClient Executor Shutdown.");
        }

        // Quit WebDriver if parallel or multiple tests
        if (driver != null) {
            driver.quit();
            System.out.println("WebDriver Closed.");
        }

        // Flush Extent Reports
        if (ExtentManager.extent != null) {
        	ExtentManager.extent.flush();
            System.out.println("Extent Report Flushed.");
        }

        System.out.println("===== Test Execution Completed Successfully =====");
    }

}
