package pageobjectmodal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup(ITestContext context) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        context.setAttribute("driver", driver); // Listener will use this
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
}
