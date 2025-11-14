package pageobjectmodal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import imageconverter.Goto;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    
    public void navigateToApplication() {
        Goto page = new Goto(driver);
        page.goTo();
        driver.manage().window().maximize();
    }
    
    public void setupDriver() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}